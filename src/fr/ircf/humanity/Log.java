package fr.ircf.humanity;

import fr.ircf.humanity.ui.ScrollPane;

public class Log extends ScrollPane implements GameElement {

	private Game game;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void update(double delta){
		super.update(delta);
		// TODO scroll max
	}
}