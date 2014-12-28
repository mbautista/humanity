package fr.ircf.humanity.job;

import java.util.ArrayList;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.job.*;
import fr.ircf.humanity.ui.Button;

abstract public class Job implements GameElement {
	
	public static Job FARMERS = new Farmers();
	
	public static String name;
	public static String icon;
	public static float[] color;
	protected ArrayList<Action> actions;
	protected Button button;
	protected Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		
	}
	@Override
	public boolean visible() {
		return true;
	}
	
	@Override
	public void render(){	
	}
	
	@Override
	public void update(double delta){
	}
	
	public void addAction(Action action){
		actions.add(action);
	}
}
