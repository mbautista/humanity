package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Discover extends Action {

	public static String NAME = "discover";
	public static Job JOB = Job.HUMANITY;
	
	public Discover(Aster source){
		super(source);
		icon = "life.jpg";
		needsTarget = true;
		discovered = true;
	}
	
	public void update(double delta){
		// TODO discover growFood above level 1
		// TODO discover exploration above level 2
	}
	
	public void start(Aster target){
		super.start(target);
		target.setDiscovered(true);
		super.updateLevel(1);
		super.stop();
	}
}