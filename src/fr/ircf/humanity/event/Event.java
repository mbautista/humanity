package fr.ircf.humanity.event;

import fr.ircf.humanity.log.LogEntry;

public class Event {
	
	/**
	 * TODO Enum event types :
	 * MESSAGE = Event(String message)
	 * POPULATION_NEED = Event(Population population, ResourceType need)
	 * ACTION_START = Event(Action action)
	 * ACTION_DISCOVER = Event(Action action)
	 * PLAYER_HUMANITY = Event(Player player, double humanity)
	 * PLAYER_LEVEL = Event(Player player, double level)
	 */

	public Event(){
	}

	public LogEntry toLogEntry() {
		// TODO
		return null;
	}
}
