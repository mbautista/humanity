package fr.ircf.humanity;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.ui.ScrollPane;

public class Log extends ScrollPane implements GameElement {

	private static int X = 150, WIDTH = 640, HEIGHT = 90, MARGIN_BOTTOM = 10;
	private Game game;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		setPosition(X, Display.getHeight() - HEIGHT - MARGIN_BOTTOM);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		updateViewport();
		setAutoscroll(true);
	}
	
	@Override
	public boolean visible(){
		return game.getState() == State.GAME;
	}

	public void addEvent(String message) {
		add(new Event(game, message));
	}
	
	public void addEvent(Event e){
		add(e);
	}
}