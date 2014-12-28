package fr.ircf.humanity.action;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.job.Job;
import fr.ircf.humanity.ui.Bar;
import fr.ircf.humanity.ui.Button;

abstract public class Action implements GameElement {
	
	public static String name;
	public static String icon;
	protected double level, duration, time;
	protected boolean running;
	protected Job job;
	protected Game game;
	protected Button button; // start action button
	protected Bar bar; // progress bar if action is running
	
	public void init(Game game){
		this.game = game;
	}
	
	public void render(){	
	}
	
	public void update(double delta){
	}
	
	public boolean visible() {
		return true;
	}
	
	protected void setJob(Job job){
		this.job = job;
		this.job.addAction(this);
	}
}
