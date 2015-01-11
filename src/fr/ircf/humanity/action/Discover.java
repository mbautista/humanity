package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Discover extends Action {

	public Discover(Aster source){
		super(source);
		name = "discover";
		job = Job.HUMANITY;
		icon = "life.jpg";
		needsTarget = true;
		discovered = true;
	}
	
	public void update(double delta){
		// TODO discover growFood above level 1
		// TODO discover exploration above level 2
	}
	
	public void start(Aster target){
		if (target.discovered()) return;
		super.start(target);
		target.discover();
		super.updateLevel(1);
		super.stop();
	}
}