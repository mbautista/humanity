package fr.ircf.humanity.dialogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.EventListener;
import fr.ircf.humanity.character.Character;

public class DialogueManager implements GameElement, EventListener {

	private Game game;
	private List<Dialogue> dialogues;
	private HashMap<Class<?>, Character> characters;
	private Character character;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
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
		if (character == null) {
			character = getCharacterFromDialogue(dialogues.get(0));
		}
		character.update(delta);
	}

	@Override
	public void update(Event event) {
		// TODO Update dialogues by matching Event in dialogues
		if (event.getType() == "welcome") {
			dialogues.add(Dialogue.D1000);
		}
	}

	private Character getCharacterFromDialogue(Dialogue dialogue) {
		Class<?> characterClass = dialogue.getCharacterClass();
		Character result = characters.get(characterClass);
		if (result == null) {
			try {
				result = (Character)characterClass.newInstance();
				result.init(game);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result.setDialogue(dialogue);
		return result;
	}
}
