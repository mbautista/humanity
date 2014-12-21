package fr.ircf.humanity;

import org.lwjgl.opengl.Display;

public class Menu implements GameElement {

	private static int Y = 200;
	private static int DY = 40;
	private Game game;
	private Button newButton, optionsButton, quitButton;
	private Button[] buttons;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		buttons = new Button[] {
				newButton = new Button(game.i18n("menu.new")),
				optionsButton = new Button(game.i18n("menu.options")),
				quitButton = new Button(game.i18n("menu.quit")),
		};
		int i = 0;
		for (Button button: buttons){
			button.setPosition((Display.getWidth() - button.getWidth())/2, Y + DY * i);
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
		// TODO Auto-generated method stub
	}
}