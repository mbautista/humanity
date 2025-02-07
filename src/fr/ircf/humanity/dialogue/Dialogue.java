package fr.ircf.humanity.dialogue;

import fr.ircf.humanity.character.Counselor;
import fr.ircf.humanity.character.Farmer;

public enum Dialogue {

	// TODO lambda event
	// TODO List<String> answers
	D1(1, Counselor.class, new int[] { 1,2,3 }),
	D2(2, Farmer.class, new int[] { 4,5,6 });

	private final int id;
	private final Class characterClass;
	private final int[] messageIds;
	
	Dialogue(int id, Class characterClass, int[] messageIds) {
		this.id = id;
		this.characterClass = characterClass;
		this.messageIds = messageIds;
	}

	public Class getCharacterClass() {
		return characterClass;
	}

	public int[] getMessageIds() {
		return messageIds;
	}
}