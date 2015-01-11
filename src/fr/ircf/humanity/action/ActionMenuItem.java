package fr.ircf.humanity.action;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.ui.ButtonIcon;
import fr.ircf.humanity.ui.Text;
import fr.ircf.humanity.ui.TextBar;

public class ActionMenuItem extends ButtonIcon {
	
	public static int WIDTH = 64, HEIGHT = 64;
	protected Game game;
	protected Action action;
	protected Text name;
	protected TextBar people, level, progress;
	
	public ActionMenuItem(){
		super();
		width = WIDTH;
		height = HEIGHT;
	}
	
	public void render(){
		super.render();
		if (state == State.OVER){
			// TODO name, people, level, progress
		}
	}

	public void update(double delta) {
		super.update(delta);
	}
	
	public void down(){
		// TODO down : people++
	}

	public void up(){
		// TODO select action
	}
	
	public void setAction(Action action){
		this.action = action;
		super.setIcon(action.getIcon());
	}
}
