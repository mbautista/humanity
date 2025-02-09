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
	private String partialMessage, completeMessage;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		dialogues = new ArrayList<>();

		image = new Image();
		int imageSize = getHeight();
		image.setPosition(Display.getWidth() - imageSize - X, Display.getHeight() - imageSize - Y - DY);
		image.setWidth(imageSize*3/2);
		image.setHeight(imageSize*3/2);

		name = new Text();
		name.setPosition(Display.getWidth() - imageSize - X, Display.getHeight() - Y - DY);

		message = new Text();
		message.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
		message.setWidth(getWidth() - imageSize);
		message.setHeight(getHeight());
		
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
		// TODO Animate image
		// Animate message
		if (partialMessage.length() < completeMessage.length()) {
			message.setText(partialMessage);
			partialMessage = completeMessage.substring(0, partialMessage.length()+1);
		}
	}

	@Override
	public void update(Event event) {
		// Trigger dialogues by event
		for (Dialogue d : Dialogue.values()) {
		      if (d.getTrigger().apply(event)) {
		    	  dialogues.add(d);
		      }
		}
		// Set current dialogue
		if ((dialogue == null || answers.isEmpty()) && !dialogues.isEmpty()) {
			dialogue = dialogues.remove(0);
			image.setPath(dialogue.getCharacter().getPath());
			name.setText(dialogue.getCharacter().getName());
			partialMessage = "";
			completeMessage = game.i18n("dialogue." + dialogue.getId());
			// TODO set answers from dialogue answerIds
			// TODO on click answer send DialogEvent
		}
	}

	public int getWidth() {
		return Display.getWidth()/3;
	}

	public int getHeight() {
		return 150;
	}
}
