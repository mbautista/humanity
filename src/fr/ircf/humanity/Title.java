package fr.ircf.humanity;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import fr.ircf.humanity.ui.Text;

public class Title implements GameElement {

	private static Color COLOR = Color.white;
	private static float SIZE = 48f; // FIXME display bug above 48f ?!
	private static String FONT = "assets/fonts/hyperspace/Hyperspace.ttf";
	private static int Y = 0;
	private Text title, version;
	private Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		title = new Text(Humanity.TITLE, COLOR, SIZE, FONT);
		version = new Text("v" + Humanity.VERSION);
		int x = (Display.getDisplayMode().getWidth() - title.getWidth())/2;
		int versionX = x + title.getWidth();
		int versionY = Y + title.getHeight() - version.getHeight() - 8;
		title.setPosition(x, Y);
		version.setPosition(versionX, versionY);
	}
	
	@Override
	public boolean visible(){
		return game.getState() != State.GAME;
	}
	
	@Override
	public void render(){
		title.render();
		version.render();
	}

	@Override
	public void update(double delta){
	}
}