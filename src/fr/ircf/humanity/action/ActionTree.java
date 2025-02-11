package fr.ircf.humanity.action;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;

public class ActionTree implements GameElement {

	private Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
	}
	
	@Override
	public boolean visible(){
		return game.getState() == State.ACTION_TREE;
	}
	
	@Override
	public void render(){
		// TODO render action menu items
		// TODO render close button
	}

	@Override
	public void update(double delta){
		// TODO if click on action item then check for upgrade
		// TODO if click on close button then get back to GAME
	}
}