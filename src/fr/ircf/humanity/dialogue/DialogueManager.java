package fr.ircf.humanity.dialogue;

import java.util.ArrayList;
import java.util.List;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.EventListener;
import fr.ircf.humanity.character.Character;

public class DialogueManager implements GameElement, EventListener {

	private List<Dialogue> dialogues;
	private List<Character> characters;
	private Dialogue dialogue;
	private Character character;
	
	@Override
	public void init(Game game) throws Exception {
		dialogues = new ArrayList<>();
	}

	@Override
	public boolean visible() {
		return !dialogues.isEmpty();
	}

	@Override
	public void render() {
		if (character != null) character.render();
	}

	@Override
	public void update(double delta) {
		dialogue = dialogues.get(0);
		character = dialogue.getCharacter();
	}

	@Override
	public void update(Event event) {
		// TODO Update dialogues by matching Event in dialogues
		if (event.getType() == "welcome") {
			dialogues.add(Dialogue.D1);
		}
	}
}
