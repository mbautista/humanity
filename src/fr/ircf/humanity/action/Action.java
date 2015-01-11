package fr.ircf.humanity.action;

import fr.ircf.humanity.Event;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.Population;
import fr.ircf.humanity.aster.Aster;

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
	protected String name, icon;
	protected double people, progress;
	protected State state = State.STOP;
	protected Job job;
	protected Aster source, target;
	protected boolean selected = false, needsPeople = false, needsTarget = false;
	
	public Action(Aster source){
		this.source = source;
	}
	
	public void render(){
		// TODO render action
	}
	
	public void update(double delta){
		// TODO update action
	}
	
	public void start(){
		state = State.START;
		source.getGame().getLog().addEvent(this);
	}
	
	public void start(Aster target){
		this.target = target;
		start();
	}
	
	public void stop(){
		state = State.STOP;
		target = null;
		select();
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
		source.getGame().getPlayer().setAction(selected ? this : null);
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
}
