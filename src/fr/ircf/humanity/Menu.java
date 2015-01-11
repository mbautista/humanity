package fr.ircf.humanity;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.ui.Button;

public class Menu implements GameElement {

	protected static int DY = 5;
	protected Game game;
	protected Button[] buttons; // TODO use HashMap<String, Button> instead of []
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		buttons = new Button[] {
			new Button(game.i18n("menu.new")) {
				public void up() { game.setState(fr.ircf.humanity.State.NEW); super.up(); }
			},
			new Button(game.i18n("menu.resume")) {
				public void up() { game.setState(fr.ircf.humanity.State.GAME); super.up(); }
			},
			new Button(game.i18n("menu.options")) {
				public void up() { game.setState(fr.ircf.humanity.State.OPTIONS); super.up(); }
			},
			new Button(game.i18n("menu.quit")) {
				public void up() { game.setState(fr.ircf.humanity.State.QUIT); super.up(); }
			},
		};
		int i = 0;
		for (Button button: buttons){
			button.setPosition(
				(Display.getWidth() - button.getWidth())/2,
				(Display.getHeight() - getHeight())/2 + i * (button.getHeight() + DY)
			);
			i++;
		}
	}

	@Override
	public boolean visible() {
		return game.getState() == State.MENU || game.getState() == State.PAUSE;
	}

	@Override
	public void render() {
		for (Button button: buttons){
			button.render();
		}
	}

	@Override
	public void update(double delta) {
		buttons[0].setDisabled(game.getState() == State.PAUSE);
		buttons[1].setDisabled(game.getState() == State.MENU);
		for (Button button: buttons){
			button.update(delta);
		}
	}
	
	public int getHeight(){
		return buttons.length * (buttons[0].getHeight() + DY) - DY;
	}
}