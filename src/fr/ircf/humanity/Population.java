package fr.ircf.humanity;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.ui.Text;

public class Population {

	public static double
		MIN_PEOPLE = 0.7, MAX_PEOPLE = 0.9,
		MIN_LIFESPAN = 16, MAX_LIFESPAN = 32,
		MIN_FERTILITY = 1, MAX_FERTILITY = 2;
	private Player player;
	private Aster aster;
	private double lifespan, fertility;
	private HashMap<Class<?>, Action> actions;
	private ResourceType[] needs = new ResourceType[] {
	   ResourceType.FOOD,
	   ResourceType.WATER,
	   ResourceType.ENERGY
	};
	
	public Population(Player player, Planet planet){
		this.player = player;
		this.aster = planet;
		this.aster.setPopulation(this);
		initActions();
	}

	// TODO should be private and called from constructor ?
	public void initPeople(double factor){
		double people = Random.betweenWithFactor(MIN_PEOPLE, MAX_PEOPLE, factor); // TODO random centered gaussian
		lifespan = Random.betweenWithFactor(MIN_LIFESPAN, MAX_LIFESPAN, factor); // TODO random centered gaussian
		fertility = Random.betweenWithFactor(MIN_FERTILITY, MAX_FERTILITY, factor); // TODO random centered gaussian
		aster.addResource(ResourceType.PEOPLE, people);
	}
	
	private void initActions(){
		actions = new HashMap<Class<?>, Action>();
		for (Class<?> actionClass : Action.CLASSES){
			try {
				actions.put(actionClass,
					(Action)actionClass.getDeclaredConstructor(Aster.class).newInstance(aster)
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(double delta){
		// TODO lifespan, fertility
		updateResources(delta);
		updateActions(delta);
	}
	
	/**
	 * Update resources on aster according to population and its needs/consumption
	 * @param delta
	 */
	private void updateResources(double delta){
		double peopleInYear = getPeopleInYear();
		aster.setResourceDelta(ResourceType.PEOPLE, peopleInYear);
		for (ResourceType need: needs ){
			double consumption = getDifficultyScale(need.getMinConsumption(), need.getMaxConsumption());
			aster.setResourceDelta(need, - getPeople() * consumption);
			if (aster.getResourceValue(need) < 0){
				aster.setResourceDelta(ResourceType.PEOPLE, aster.getResourceDelta(need));
				aster.getResource(need).setValue(0);
				if (aster.discovered() && player.getPlanet().isNewYear()){
					player.getGame().getLog().add(createNeedEvent(need));
				}
			}
		}
	}
	
	/**
	 * People equation : Computes how many people birth/die in year
	 * @return
	 */
	private double getPeopleInYear(){
		return getPeople() * (fertility - 1) / lifespan;
	}
	
	// TODO this helper might be needed elsewhere
	private double getDifficultyScale(double min, double max){
		return min + (max - min) * player.getGame().getOptions().getDifficulty() / 100;
	}
	
	private void updateActions(double delta){
		for(Entry<Class<?>, Action> e : actions.entrySet()){
			e.getValue().update(delta);
		}
	}
	
	public double getPeople() {
		return aster.getResourceValue(ResourceType.PEOPLE);
	}

	public double getLifespan() {
		return lifespan;
	}

	public void setLifespan(double lifespan) {
		this.lifespan = lifespan;
	}

	public HashMap<Class<?>, Action> getActions() {
		return actions;
	}

	public void setActions(HashMap<Class<?>, Action> actions) {
		this.actions = actions;
	}

	public Action getAction(Class<?> key) {
		return actions.get(key);
	}

	public void incrementActionPeople(Action action, double delta) {
		// Compute max allowed people for current action
		double maxAllowedPeople = 100;
		for (Entry<Class<?>, Action> e : actions.entrySet()){
			if (action != e.getValue()) maxAllowedPeople -= e.getValue().getPeople();
		}
		// Increment action people
		action.setPeople(Math.max(0, Math.min(maxAllowedPeople, action.getPeople() + delta)));
	}
	
	private Event createNeedEvent(ResourceType need){
		Event event = new Event(player.getGame());
		event.add(new Text(i18n("resource.people"), ResourceType.PEOPLE.getColor()));
		event.add(new Text(" " + i18n("event.from")));
		event.add(new Text(" " + aster.getName(), aster.getColor()));
		event.add(new Text(" " + i18n("event.population." + need.getName())));
		return event;
	}
	
	private String i18n(String message){
		return player.getGame().i18n(message);
	}
}
