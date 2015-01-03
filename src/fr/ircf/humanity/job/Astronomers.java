package fr.ircf.humanity.job;

import fr.ircf.humanity.action.*;


public class Astronomers extends Job {

	public static String name = "astronomers";
	public static String icon;
	public static float[] color;

	public Astronomers() {
		addAction(new Discover());
		addAction(new DetectResources());
		addAction(new Explore());
	}
}
