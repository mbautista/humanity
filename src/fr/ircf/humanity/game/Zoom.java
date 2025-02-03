package fr.ircf.humanity.game;

import org.lwjgl.input.Mouse;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.component.Button;
import fr.ircf.humanity.component.Slider;

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
			public void down() { super.down(); game.getCamera().setZ(Camera.Z_MAX - getValue()); }
		};
		slider.setPosition(X, Y + in.getHeight());
		out = new Button("-") {
			public void down() { game.getCamera().zoomOut(); }
		};
	}

	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}
	
	@Override
	public void render() {
		if (!game.getPlayer().canZoomOut()) return;
		in.render();
		out.render();
		slider.render();
	}

	@Override
	public void update(double delta) {
		if (!game.getPlayer().canZoomOut()) return;
		in.update(delta);
		slider.setValue(game.getCamera().getZMax() - game.getCamera().getZ());
		slider.setMax(game.getCamera().getZMax() - game.getCamera().getZMin());
		slider.update(delta);
		out.setPosition(X, slider.getY() + slider.getHeight());
		out.update(delta);
		int wheel = Mouse.getDWheel();
		if (wheel < 0) {
			game.getCamera().zoomOut();
	    }else if (wheel > 0){
	    	game.getCamera().zoomIn();
	   }
	}
}
