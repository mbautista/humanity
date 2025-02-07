package fr.ircf.humanity.character;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.action.Job;
import fr.ircf.humanity.component.Image;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.quest.Quest;

public abstract class Character implements GameElement {

	private static int X = 10, Y = 10, DY = 20;
	protected Game game;
	protected String name, path, message;
	protected Job job;
	protected Image image;
	protected Text nameText, messageText;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		nameText = new Text(name);
		nameText.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
		image = new Image(path);
		image.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y);
		messageText = new Text(message);
		messageText.setPosition(Display.getWidth() - getWidth() - X, Display.getHeight() - getHeight() - Y - DY);
	}

	@Override
	public void render() {
		image.render();
		nameText.render();
		messageText.render();
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
	}

	public void giveQuest(Quest quest) {
		// TODO
	}

	public int getWidth() {
		return Display.getWidth()/3;
	}

	public int getHeight() {
		return Display.getHeight()/4;
	}
}
