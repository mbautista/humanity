package fr.ircf.humanity.game;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;

public class Map implements GameElement {

	private Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		
	}

	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
		
	}
}
