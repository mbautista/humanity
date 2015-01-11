package fr.ircf.humanity.action;

import fr.ircf.humanity.ui.ButtonIcon;
import fr.ircf.humanity.ui.Text;
import fr.ircf.humanity.ui.TextBar;

public class ActionMenuItem extends ButtonIcon {
	
	public static int WIDTH = 32, HEIGHT = 32;
	protected Action action;
	protected Text name;
	protected TextBar people, level, progress;
	protected boolean selected = false;
	
	public ActionMenuItem(){
		super();
		width = WIDTH;
		height = HEIGHT;
	}
	
	public void render(){
		if (!action.visible()) return;
		if (action.selected()) super.setColor(action.getJob().getColor());
		super.render();
		if (state == State.OVER){
			// TODO name, people, level, progress
		}
	}

	public void update(double delta) {
		if (!action.visible()) return;
		super.update(delta);
	}
	
	public void down(){
		// TODO down : people++
	}

	public void up(){
		action.select();
	}
	
	public void setAction(Action action){
		this.action = action;
		super.setIcon(action.getIcon());
	}
}
