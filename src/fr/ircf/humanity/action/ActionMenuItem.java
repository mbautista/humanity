package fr.ircf.humanity.action;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.ui.Bar;
import fr.ircf.humanity.ui.Button;

public class ActionMenuItem {
	
	protected Game game;
	protected Action action;
	protected Button button; // start action button
	protected Bar bar; // progress bar if action is running
	
	public void init(Game game, final Action action){
		this.game = game;
		this.action = action;
		button = new Button(game.i18n("action." + action.getName()));
	}
	
	public void render(){
		button.render();
	}
	
	public void update(double delta){
		// TODO update button state/events
	}
	
	public void setAction(Action action){
		this.action = action;
	}
}
