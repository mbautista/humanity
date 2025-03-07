package fr.ircf.humanity.game;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.component.ScrollPane;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.EventListener;

public class Log extends ScrollPane implements GameElement, EventListener {

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

	@Override
	public void update(Event event) {
		add(event.getLogPanel(game));
	}
}