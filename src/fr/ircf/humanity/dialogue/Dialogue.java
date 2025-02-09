package fr.ircf.humanity.dialogue;

import java.util.function.Function;

import fr.ircf.humanity.event.ActionEvent;
import fr.ircf.humanity.event.Event;

public enum Dialogue {

	// Dialog(int id, Character character, int[] answerIds, Function<Event, Boolean> trigger)
	D1000(1000, Character.COUNSELOR, new int[] { }, (Event e) -> e.getType() == "welcome"),
	D1001(1001, Character.COUNSELOR, new int[] { }, (Event e) -> 
			e.getType() == "start" &&
			e.getClass() == ActionEvent.class &&
			((ActionEvent)e).getAction().getName() == "discover" &&
			((ActionEvent)e).getAction().getLevel() == 0),
	D1002(1002, Character.COUNSELOR, new int[] { }, (Event e) -> 
			e.getType() == "stop" &&
			e.getClass() == ActionEvent.class &&
			((ActionEvent)e).getAction().getName() == "growFood" &&
			((ActionEvent)e).getAction().getPeople() >= 20), // FIXME not triggered ?!
	D2000(2000, Character.FARMER, new int[] { }, (Event e) -> false);

	private final int id;
	private final Character character;
	private final int[] answerIds;
	private Function<Event, Boolean> trigger;
	
	Dialogue(int id, Character character, int[] answerIds, Function<Event, Boolean> trigger) {
		this.id = id;
		this.character = character;
		this.answerIds = answerIds;
		this.trigger = trigger;
	}

	public Function<Event, Boolean> getTrigger() {
		return trigger;
	}

	public int getId() {
		return id;
	}
	
	public Character getCharacter() {
		return character;
	}

	public int[] getAnswerIds() {
		return answerIds;
	}
}