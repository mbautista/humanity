package fr.ircf.humanity;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.aster.ResourceType;

public class Population {

	public static double
		MIN_PEOPLE = 0.7, MAX_PEOPLE = 0.9,
		MIN_LIFESPAN = 16, MAX_LIFESPAN = 32,
		MIN_FERTILITY = 1, MAX_FERTILITY = 2;
	private Player player;
	private Aster aster;
	private double people, lifespan, fertility;
	private HashMap<Class<?>, Action> actions;
	
	public Population(Player player, Planet planet){
		this.player = player;
		this.aster = planet;
		this.aster.setPopulation(this);
		initActions();
	}

	// TODO should be private and called from constructor ?
	public void initPeople(double factor){
		people = Random.betweenWithFactor(MIN_PEOPLE, MAX_PEOPLE, factor); // TODO random centered gaussian
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
		updatePeople(delta);
		updateActions(delta);
	}
	
	/**
	 * Update people on aster according to people equation
	 * @param delta
	 */
	// TODO move this to Aster.updateResources() (?)
	private void updatePeople(double delta){
		aster.updateResource(delta, ResourceType.PEOPLE, getPeopleInYear());
	}
	
	/**
	 * People equation : Computes how many people birth/die in year
	 * @return
	 */
	private double getPeopleInYear(){
		return people * (fertility - 1) / lifespan;
	}
	
	private void updateActions(double delta){
		for(Entry<Class<?>, Action> e : actions.entrySet()){
			e.getValue().update(delta);
		}
	}
	
	public double getPeople() {
		return people;
	}

	public void setPeople(double people) {
		this.people = people;
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

	public void incrementActionPeople(Class<? extends Action> actionClass, double delta) {
		// TODO increment action people of delta
		// TODO decrement all other actions people so that total is <= 100
	}
}
