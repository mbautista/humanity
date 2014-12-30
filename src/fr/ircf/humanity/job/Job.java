package fr.ircf.humanity.job;

import java.util.ArrayList;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.job.*;
import fr.ircf.humanity.ui.Button;

abstract public class Job implements GameElement {
	
	public static int
		FARMERS = 0,
		ASTRONOMERS = 1,
		PHYSICISTS = 2,
		BIOLOGISTS = 3,
		ARMY = 4,
		MERCHANTS = 5;
	public static String name;
	public static String icon;
	public static float[] color;
	protected ArrayList<Action> actions;
	protected Button button; // TODO use JobMenu buttons instead (?)
	protected Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		actions = new ArrayList<Action>();
		button = new Button(game.i18n(name));
	}
	@Override
	public boolean visible() {
		return true;
	}
	
	@Override
	public void render(){
		button.render();
		for (Action action: actions){
			action.render();
		}
	}
	
	@Override
	public void update(double delta){
		for (Action action: actions){
			action.update(delta);
		}
	}
	
	public void addAction(Action action){
		actions.add(action);
		action.setJob(this);
	}
}
