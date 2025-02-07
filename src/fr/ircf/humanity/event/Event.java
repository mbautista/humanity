package fr.ircf.humanity.event;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.component.Panel.DisplayMode;

abstract public class Event {
	
	protected int year;
	protected String type;
	
	public Event(int year, String type){
		this.year = year;
		this.type = type;
	}

	public Panel getLogPanel(Game game) {
		Panel panel = new Panel();
		panel.setDisplayMode(DisplayMode.INLINE);
		panel.add(new Text(game.i18n("event.year") + " " + year + " : "));
		return panel;
	}

	public String getType() {
		return type;
	}
}
