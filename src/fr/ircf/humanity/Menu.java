package fr.ircf.humanity;

import java.awt.event.WindowEvent;

import org.lwjgl.opengl.Display;

public class Menu implements GameElement {

	private static int DY = 40;
	private Game game;
	private Button newButton, optionsButton, quitButton;
	private Button[] buttons;
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		buttons = new Button[] {
			newButton = new Button(game.i18n("menu.new")) {
				public void click() { game.setState(State.NEW);
			}},
			optionsButton = new Button(game.i18n("menu.options")) {
				public void click() { game.setState(State.OPTIONS);
			}},
			quitButton = new Button(game.i18n("menu.quit")) {
				public void click() { game.setState(State.QUIT);
			}},
		};
		int i = 0;
		for (Button button: buttons){
			button.setPosition(
				(Display.getWidth() - button.getWidth())/2,
				(Display.getHeight() - getHeight())/2 + i * DY
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