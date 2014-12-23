package fr.ircf.humanity;

import org.lwjgl.opengl.Display;

public class Menu implements GameElement {

	protected static int DY = 5;
	protected Game game;
	protected Button[] buttons; // TODO use HashMap<String, Button> instead of []
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		buttons = new Button[] {
			new Button(game.i18n("menu.new")) {
				public void click() { game.setState(State.NEW);
			}},
			new Button(game.i18n("menu.options")) {
				public void click() { game.setState(State.OPTIONS);
			}},
			new Button(game.i18n("menu.quit")) {
				public void click() { game.setState(State.QUIT);
			}},
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
		return game.getState() == State.MENU;
	}

	@Override
	public void render() {
		for (Button button: buttons){
			button.render();
		}
	}

	@Override
	public void update(double delta) {
		for (Button button: buttons){
			button.update(delta);
		}
	}
	
	public int getHeight(){
		return buttons.length * (buttons[0].getHeight() + DY) - DY;
	}
}