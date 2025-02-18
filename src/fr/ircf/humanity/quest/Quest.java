package fr.ircf.humanity.quest;

import java.util.function.Function;

import fr.ircf.humanity.event.Event;

public enum Quest {

	// Quest(int id, boolean repeatable, Function<Event, Boolean> trigger, Function<Event, Boolean> success, Function<Event, Boolean> failure)
	Q1000(1000, false, (Event e) -> false, (Event e) -> false, (Event e) -> false);

	private final int id;
	private boolean repeatable;
	private Function<Event, Boolean> trigger, success, failure;
	
	Quest(int id, boolean repeatble, Function<Event, Boolean> trigger, Function<Event, Boolean> success, Function<Event, Boolean> failure) {
		this.id = id;
		this.repeatable = repeatble;
		this.trigger = trigger;
		this.success = trigger;
		this.failure = trigger;
	}

	public Function<Event, Boolean> getTrigger() {
		return trigger;
	}

	public Function<Event, Boolean> getSuccess() {
		return success;
	}
	
	public Function<Event, Boolean> getFailure() {
		return failure;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isRepeatable() {
		return repeatable;
	}
}