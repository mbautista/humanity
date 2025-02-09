package fr.ircf.humanity.dialogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.action.Job;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.EventListener;
import fr.ircf.humanity.component.Button;
import fr.ircf.humanity.component.Image;
import fr.ircf.humanity.component.Text;

public class DialogueManager implements GameElement, EventListener {

	private Game game;
	private List<Dialogue> dialogues;
	private Dialogue dialogue;
	private static int X = 10, Y = 10, DY = 20;
	private Image image;
	private Text name, message;
	private List<Button> answers;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		dialogues = new ArrayList<>();
		name = new Text();
		name.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
		image = new Image();
		image.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y);
		message = new Text();
		message.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
		answers = new ArrayList<>();
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && dialogue != null;
	}

	@Override
	public void render() {
		if (image == null) return;
		image.render();
		name.render();
		message.render();
		for(Button answer: answers) {
			answer.render();
		}
	}

	@Override
	public void update(double delta) {
		// TODO animate image and message
	}

	@Override
	public void update(Event event) {
		System.out.println("update event");
		// TODO Update dialogues by matching Event in dialogues
		if (event.getType() == "welcome") {
			dialogues.add(Dialogue.D1000);
		}
		if (dialogue == null && !dialogues.isEmpty()) {
			dialogue = dialogues.remove(0);
			image.setPath(dialogue.getCharacter().getPath());
			name.setText(dialogue.getCharacter().getName());
			message.setText(game.i18n("dialogue." + dialogue.getId()));
			// TODO set answers from dialogue answerIds
			// TODO on click answer send DialogEvent
			System.out.println("update dialogue");
		}
	}

	public int getWidth() {
		return Display.getWidth()/3;
	}

	public int getHeight() {
		return Display.getHeight()/4;
	}
}
