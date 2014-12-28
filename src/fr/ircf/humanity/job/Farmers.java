package fr.ircf.humanity.job;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.*;


public class Farmers extends Job {

	public static String name = "farmers";
	public static String icon;
	public static float[] color;
	
	public void init(Game game) throws Exception {
		super.init(game);
		addAction(new GrowFood());
	}
}
