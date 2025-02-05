package fr.ircf.humanity.event;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class ActionEvent extends Event{
	
	private Action action;

	public ActionEvent(int year, String type, Action action){
		super(year, type);
		this.action = action;
	}

	public Panel getLogPanel(Game game) {
		Panel panel = super.getLogPanel(game);
		// TODO panel.add(new Text(message));
		return panel;
	}
}
