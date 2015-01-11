package fr.ircf.humanity;

import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

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
	
	public Event(Game game, Action action){
		this(game);
		add(new Text(game.i18n("job." + action.getJob().getName()), action.getJob().getColor()));
		if (action.getTarget() == null || action.getSource() != action.getTarget() ){
			add(new Text(" " + game.i18n("event.from")));
			add(new Text(" " + action.getSource().getName(), action.getSource().getColor()));
		}
		add(new Text(" " + game.i18n("action." + action.getName())));
		if (action.getTarget() != null){
			add(new Text(" " + action.getTarget().getName(), action.getTarget().getColor()));
		}
	}
	
	private void addYear(){
		add(new Text(game.i18n("event.year") + " " + game.getPlayer().getPlanet().getYear() + " : "));
	}
}
