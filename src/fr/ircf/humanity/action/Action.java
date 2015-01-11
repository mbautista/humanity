package fr.ircf.humanity.action;

import fr.ircf.humanity.Event;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.aster.Aster;

abstract public class Action {

	public static Class<?>[] CLASSES = new Class[] {
		Discover.class,
		GrowFood.class,
		UseChemicals.class,
		UseHydroponics.class
		// TODO all actions
	};
	public static enum State { START, STOP };
	protected String name, icon;
	protected double people, progress;
	protected State state = State.STOP;
	protected Job job;
	protected Aster source, target;
	protected boolean selected = false, needsPeople = false, needsTarget = false;
	
	public void render(){
		// TODO render action
	}
	
	public void update(double delta){
		// TODO update action
	}
	
	public void start(){
		state = State.START;
		// TODO ((Humanity)game).getLog().add(new Event(this));
	}
	
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job){
		this.job = job;
	}

	public String getName() {
		return name;
	}
	
	public boolean visible() {
		return false;
	}
	
	public void select(){
		selected = needsTarget && !selected;
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
}
