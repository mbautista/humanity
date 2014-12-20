package fr.ircf.humanity;

import org.lwjgl.opengl.Display;


public class Menu implements GameElement {

	private Game game;
	private Frame frame;
	private Button newButton, optionsButton, quitButton;
	private static int WIDTH = 250, HEIGHT = 250;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		
		Panel panel = new Panel();
		panel.add(newButton = new Button(game.i18n("menu.new")));
		panel.add(optionsButton = new Button(game.i18n("menu.options")));
		panel.add(quitButton = new Button(game.i18n("menu.quit")));
		
		frame = new Frame();
	    frame.setSize(WIDTH, HEIGHT);
	    int x = Display.getX() + (Display.getDisplayMode().getWidth() - WIDTH)/2;
	    int y = Display.getY() + (Display.getDisplayMode().getHeight() - HEIGHT)/2;
	    frame.setLocation(x, y);
		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public boolean visible() {
		return game.getState() == State.MENU;
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