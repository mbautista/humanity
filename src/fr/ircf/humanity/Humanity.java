package fr.ircf.humanity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Humanity extends BasicGame {

	private Title title;
	private Menu menu;
	private Options options;
	private Galaxy galaxy;
	private Zoom zoom;
	private Map map;
	private PlayerInfo playerInfo;
	private Log log;
	private Actions actions;
	private Audio audio;
	private Loader loader;
	static final String NAME = "Humanity";
	static final double VERSION = 0.1;
	
	public Humanity() {
		super(NAME);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString(NAME + " " + VERSION, 10, 20);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Humanity());
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(NAME).log(Level.SEVERE, null, ex);
		}
	}

}
