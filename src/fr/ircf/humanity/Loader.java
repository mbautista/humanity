package fr.ircf.humanity;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.ui.Bar;

public class Loader extends Bar implements GameElement {

	private Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		x = (Display.getWidth()-width)/2;
		y = (Display.getHeight()-height)/2;
	}

	@Override
	public boolean visible() {
		return game.getState() == State.LOAD;
	}

	@Override
	public void update(double delta) {
		if (game.getState() == State.LOAD && value>=max){
			game.setState(State.GAME);
		}
	}
}
