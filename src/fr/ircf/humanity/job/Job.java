package fr.ircf.humanity.job;

import java.util.ArrayList;

import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.job.*;
import fr.ircf.humanity.ui.Button;

abstract public class Job {
	
	public static Job FARMERS = new Farmers();
	
	public static String name;
	public static String icon;
	public static float[] color;
	protected ArrayList<Action> actions;
	protected Button button;
	
	public void render(){	
	}
	
	public void update(double delta){
	}
	
	public void addAction(Action action){
		actions.add(action);
	}
}
