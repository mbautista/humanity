package fr.ircf.humanity.game;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class Event extends Panel {
	
	private Game game;
	
	public Event(Game game){
		super();
		super.setDisplayMode(DisplayMode.INLINE);
		this.game = game;
		addYear();
	}
	
	public Event(Game game, String message){
		this(game);
		add(new Text(message));
	}
	
	private void addYear(){
		add(new Text(game.i18n("event.year") + " " + game.getPlayer().getPlanet().getYear() + " : "));
	}
}
