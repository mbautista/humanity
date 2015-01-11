package fr.ircf.humanity.action;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.ui.Button;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.TextBar;

public class ActionMenuItem extends Panel {
	
	protected Game game;
	protected Action action;
	protected Button button; // action button
	protected TextBar people, level, progress;
	
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
