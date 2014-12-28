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
		button = new Button(game.i18n("action." + name));
	}
	
	public void render(){
		button.render();
		if (running) bar.render();
	}
	
	public void update(double delta){
	}
	
	public boolean visible() {
		return true;
	}
	
	public void setJob(Job job){
		this.job = job;
	}
}
