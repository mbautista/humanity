package fr.ircf.humanity.dialogue;

import fr.ircf.humanity.character.Counselor;
import fr.ircf.humanity.character.Farmer;

public enum Dialogue {

	// TODO lambda event
	D1000(1000, Counselor.class, new int[] { }),
	D1001(1001, Counselor.class, new int[] { }),
	D1002(1002, Counselor.class, new int[] { 1,2 }),
	D2000(2000, Farmer.class, new int[] { });

	private final int id;
	private final Class<?> characterClass;
	private final int[] answerIds;
	
	Dialogue(int id, Class<?> characterClass, int[] answerIds) {
		this.id = id;
		this.characterClass = characterClass;
		this.answerIds = answerIds;
	}

	public Class<?> getCharacterClass() {
		return characterClass;
	}

	public int[] getAnswerIds() {
		return answerIds;
	}
}