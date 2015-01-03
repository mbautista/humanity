package fr.ircf.humanity.action;

import fr.ircf.humanity.Event;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.Humanity;
import fr.ircf.humanity.job.Job;

abstract public class Action {
	
	public static String name;
	public static String icon;
	public static enum State { START, STOP };
	protected double level, duration, time;
	protected State state = State.STOP;
	protected Job job;
	protected Game game;

	public void init(Game game) {
		this.game = game;
	}
	
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
}
