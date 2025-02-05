package fr.ircf.humanity.log;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class LogEntry extends Panel {
	
	private Game game;
	
	public LogEntry(Game game){
		super();
		super.setDisplayMode(DisplayMode.INLINE);
		this.game = game;
		addYear();
	}
	
	public LogEntry(Game game, String message){
		this(game);
		add(new Text(message));
	}
	
	private void addYear(){
		add(new Text(game.i18n("event.year") + " " + game.getPlayer().getPlanet().getYear() + " : "));
	}
}
