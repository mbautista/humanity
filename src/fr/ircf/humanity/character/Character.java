package fr.ircf.humanity.character;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.action.Job;
import fr.ircf.humanity.component.Button;
import fr.ircf.humanity.component.Image;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.dialogue.Dialogue;

public abstract class Character implements GameElement {

	private static int X = 10, Y = 10, DY = 20;
	protected Game game;
	protected String name, path, message;
	protected Job job;
	protected Image image;
	protected Text nameText, messageText;
	protected Dialogue dialogue;
	protected List<Button> answers;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		nameText = new Text(name);
		nameText.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
		image = new Image(path);
		image.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y);
		messageText = new Text(message);
		messageText.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
		answers = new ArrayList<>();
	}

	@Override
	public void render() {
		image.render();
		nameText.render();
		messageText.render();
		for(Button answer: answers) {
			answer.render();
		}
	}

	@Override
	public void update(double delta) {
		// TODO set message and messageText from dialogue message
		// TODO set answers from dialogue answerIds
		// TODO on click answer send DialogEvent
	}

	public int getWidth() {
		return Display.getWidth()/3;
	}

	public int getHeight() {
		return Display.getHeight()/4;
	}
	
	public void setDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
	}
}
