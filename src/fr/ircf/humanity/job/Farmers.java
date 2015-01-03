package fr.ircf.humanity.job;

import fr.ircf.humanity.action.*;

public class Farmers extends Job {

	public static String name = "farmers";
	public static String icon;
	public static float[] color;
	
	public Farmers() {
		addAction(new GrowFood());
		addAction(new UseChemicals());
		addAction(new UseHydroponics());
	}
}
