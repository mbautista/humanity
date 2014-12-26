package fr.ircf.humanity;

import org.lwjgl.input.Mouse;

public class Zoom implements GameElement {

	public static int X = 10, Y = 10;
	private Game game;
	private Slider slider;
	private Button in;
	private Button out;
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		in = new Button("+") {
			public void down() { game.getCamera().zoomIn(); }
		};
		in.setPosition(X, Y);
		slider = new Slider() {
			public void up() { game.getCamera().setZ(getValue()); }
		};
		slider.setPosition(X, Y + in.getHeight());
		out = new Button("-") {
			public void down() { game.getCamera().zoomOut(); }
		};
		out.setPosition(X, slider.getY() + slider.getHeight());
	}

	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}
	
	@Override
	public void render() {
		in.render();
		out.render();
		slider.render();
	}

	@Override
	public void update(double delta) {
		in.update(delta);
		out.update(delta);
		slider.update(delta);
		int wheel = Mouse.getDWheel();
		if (wheel < 0) {
			game.getCamera().zoomOut();
	    }else if (wheel > 0){
	    	game.getCamera().zoomIn();
	   }
	}
}
