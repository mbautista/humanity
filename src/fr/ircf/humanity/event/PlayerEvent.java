package fr.ircf.humanity.event;

import fr.ircf.humanity.game.Player;
import fr.ircf.humanity.log.LogEntry;

public class PlayerEvent extends Event {
	
	private Player player;
	
	public PlayerEvent(int year, String type, Player player){
		super(year, type);
		this.player = player;
	}

	public LogEntry toLogEntry() {
		// TODO
		return null;
	}
}
