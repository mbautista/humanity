package fr.ircf.humanity.event;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class MessageEvent extends Event{
	
	private String message;
	
	public MessageEvent(int year, String type, String message){
		super(year, type);
		this.message = message;
	}
	
	public Panel getLogPanel(Game game) {
		Panel panel = super.getLogPanel(game);
		panel.add(new Text(message));
		return panel;
	}
}
