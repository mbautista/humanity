package fr.ircf.humanity.job;

import java.util.ArrayList;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.Action;

abstract public class Job {
	
	public static String name;
	public static String icon;
	public static float[] color;
	protected ArrayList<Action> actions;
	protected Game game;

	public Job() {
		actions = new ArrayList<Action>();
	}
	
	public void render(){
		for (Action action: actions){
			action.render();
		}
	}
	
	public void update(double delta){
		for (Action action: actions){
			action.update(delta);
		}
	}
	
	public void addAction(Action action){
		actions.add(action);
		action.setJob(this);
	}
	
	public String getName() {
		return name;
	}
}
