package fr.ircf.humanity.dialogue;

import java.util.function.Function;

import fr.ircf.humanity.event.ActionEvent;
import fr.ircf.humanity.event.Event;

public enum Dialogue {

	// Dialog(int id, Character character, int[] answerIds, boolean repeatable, Function<Event, Boolean> trigger)
	D1000(1000, Character.COUNSELOR, new int[] { }, false, (Event e) -> e.getType() == "welcome"),
	D1001(1001, Character.COUNSELOR, new int[] { }, false, (Event e) -> 
			e.getType() == "start" &&
			e.getClass() == ActionEvent.class &&
			((ActionEvent)e).getAction().getName() == "discover" &&
			((ActionEvent)e).getAction().getLevel() == 0),
	D1002(1002, Character.COUNSELOR, new int[] { 3 }, false, (Event e) -> 
			e.getType() == "incrementPeople" &&
			e.getClass() == ActionEvent.class &&
			((ActionEvent)e).getAction().getName() == "growFood" &&
			((ActionEvent)e).getAction().getPeople() >= 20),
	D1003(1003, Character.COUNSELOR, new int[] { 3 }, false, (Event e) -> false), // TODO trigger on 1002 answer 3
	D1004(1004, Character.COUNSELOR, new int[] { }, false, (Event e) -> false), // TODO trigger on 1003 answer 3
	D1005(1005, Character.COUNSELOR, new int[] { }, false, (Event e) -> 
			e.getType() == "incrementPeople" &&
			e.getClass() == ActionEvent.class &&
			((ActionEvent)e).getAction().getName() == "study" &&
			((ActionEvent)e).getAction().getPeople() >= 20),
	D1006(1006, Character.COUNSELOR, new int[] { }, false, (Event e) -> 
			e.getType() == "incrementPeople" &&
			e.getClass() == ActionEvent.class &&
			((ActionEvent)e).getAction().getName() == "train" &&
			((ActionEvent)e).getAction().getPeople() >= 20),
	D2000(2000, Character.FARMER, new int[] { }, false, (Event e) -> false), // TODO
	D3000(3000, Character.SCIENTIST, new int[] { }, false, (Event e) -> false), // TODO
	D4000(4000, Character.SOLDIER, new int[] { }, false, (Event e) -> false); // TODO

	private final int id;
	private final Character character;
	private final int[] answerIds;
	private boolean repeatable;
	private Function<Event, Boolean> trigger;
	
	Dialogue(int id, Character character, int[] answerIds, boolean repeatble, Function<Event, Boolean> trigger) {
		this.id = id;
		this.character = character;
		this.answerIds = answerIds;
		this.repeatable = repeatble;
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
	
	public boolean isRepeatable() {
		return repeatable;
	}
}