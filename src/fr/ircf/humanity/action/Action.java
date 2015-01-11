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
	protected boolean selectable = false, selected = false;
	
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
	
	public void setJob(Job job){
		this.job = job;
	}

	public String getName() {
		return name;
	}
	
	public boolean visible() {
		return false;
	}
	
	public void toggleSelect(){
		selected = selectable && !selected;
	}
	
	public boolean selected(){
		return selected;
	}
	
	public String getIcon() {
		return icon;
	}
}
