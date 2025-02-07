package fr.ircf.humanity.action;

import java.util.HashMap;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.event.ActionEvent;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.PopulationEvent;

abstract public class Action {

	public static Class<?>[] CLASSES = new Class[] {
		Discover.class,
		Explore.class,
		GrowFood.class,
		UseChemicals.class,
		UseHydroponics.class
		// TODO all actions
	};
	public static enum State { START, STOP };
	protected Job job;
	protected String name, icon;
	protected double people, progress;
	protected State state = State.STOP;
	protected Aster source, target;
	protected boolean selected = false,
			needsPeople = false, 
			needsTarget = false, 
			discovered = false;
	protected HashMap<Class<?>, Double> requiresLevels;
	
	public Action(Aster source){
		this.source = source;
		requiresLevels = new HashMap<Class<?>, Double>();
	}
	
	public void render(){
		// TODO render action
	}
	
	public void update(double delta){
		if (!discovered && source.getGame().getPlayer().hasLevels(requiresLevels)){
			discover();
		}
	}
	
	public double getLevel(){
		return source.getGame().getPlayer().getLevel(this.getClass());
	}
	
	protected void incrementLevel(double delta){
		source.getGame().getPlayer().incrementLevel(this.getClass(), delta);
	}
	
	public double getDifficulty(){
		return source.getGame().getOptions().getDifficulty();
	}
	
	public boolean started(){
		return state == State.START;
	}
	
	public void start(){
		state = State.START;
		source.getGame().getEventManager().notify(new ActionEvent(source.getGame().getPlayer().getPlanet().getYear(), "start", this));
	}
	
	public void start(Aster target){
		this.target = target;
		start();
	}
	
	public void stop(){
		state = State.STOP;
		target = null;
		if (needsTarget) toggle(); // TODO remove for discover action
	}

	public String getName() {
		return name;
	}
	
	public Job getJob(){
		return job;
	}
	
	public boolean discovered() {
		return discovered;
	}
	
	public void discover(){
		discovered = true;
		source.getGame().getEventManager().notify(new ActionEvent(source.getGame().getPlayer().getPlanet().getYear(), "discover", this));
		// TODO discover job
	}
	
	public void toggle(){
		if (needsTarget){
			selected = !selected;
			source.getGame().getPlayer().setAction(selected ? this : null);
		}else{
			if (started()){
				stop();
			}else{
				start();
			}
		}
	}

	public void incrementPeople(double delta){
		source.getPopulation().incrementActionPeople(this, delta);
		if (started()){
			if (people <= 0) stop();
		}else{
			if (people >= 0) start();
		}
	}
	
	public boolean selected(){
		return selected;
	}
	
	public boolean needsPeople(){
		return needsPeople;
	}
	
	public boolean needsTarget(){
		return needsTarget;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public Aster getSource(){
		return source;
	}
	
	public Aster getTarget(){
		return target;
	}

	public String i18n(String string) {
		return source.getGame().i18n(string);
	}
	
	public double getPeople() {
		return people;
	}
	
	public void setPeople(double people) {
		this.people = people;
	}

	public double getProgress() {
		return progress;
	}
}
