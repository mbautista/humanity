package fr.ircf.humanity.dialogue;

public enum Dialogue {

	// TODO lambda event
	D1000(1000, Character.COUNSELOR, new int[] { }),
	D1001(1001, Character.COUNSELOR, new int[] { }),
	D1002(1002, Character.COUNSELOR, new int[] { 1,2 }),
	D2000(2000, Character.FARMER, new int[] { });

	private final int id;
	private final Character character;
	private final int[] answerIds;
	
	Dialogue(int id, Character character, int[] answerIds) {
		this.id = id;
		this.character = character;
		this.answerIds = answerIds;
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