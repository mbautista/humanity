package fr.ircf.humanity;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.ui.Component;
import fr.ircf.humanity.ui.ScrollPane;
import fr.ircf.humanity.ui.Text;

public class Log extends ScrollPane implements GameElement {

	private static int X = 100, WIDTH = 400, HEIGHT = 60;
	private Game game;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		super.setPosition(X, Display.getHeight() - HEIGHT);
		super.setWidth(WIDTH);
		super.setHeight(HEIGHT);
		super.updateViewport();
		super.setAutoscroll(true);
	}
}