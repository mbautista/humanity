package fr.ircf.humanity.dialogue;

import java.util.ArrayList;
import java.util.List;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.EventListener;

public class DialogueManager implements GameElement, EventListener {

	private List<Dialogue> dialogues;
	private List<Dialogue> currentDialogues;
	
	@Override
	public void init(Game game) throws Exception {
		dialogues = new ArrayList<>();
		currentDialogues = new ArrayList<>();
	}

	@Override
	public boolean visible() {
		return !currentDialogues.isEmpty();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Event event) {
		// TODO Update currentDialogues by matching Event in dialogues
	}
}
