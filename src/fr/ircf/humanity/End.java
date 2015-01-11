package fr.ircf.humanity;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import fr.ircf.humanity.ui.Text;

public class End implements GameElement {

	private static Color COLOR = Color.white;
	private static float SIZE = 48f; // FIXME display bug above 48f ?!
	private static String FONT = "assets/fonts/hyperspace/Hyperspace.ttf";
	private Text end;
	private Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		end = new Text(game.i18n("game.end"), COLOR, SIZE, FONT);
		int x = (Display.getWidth() - end.getWidth())/2;
		int y = (Display.getHeight() - end.getHeight())/2;
		end.setPosition(x, y);
	}
	
	@Override
	public boolean visible(){
		return game.getState() == State.END;
	}
	
	@Override
	public void render(){
		end.render();
	}

	@Override
	public void update(double delta){
		if (Mouse.isButtonDown(0)) game.setState(State.MENU);
	}
}