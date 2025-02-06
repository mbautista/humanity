package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Discover extends Action {

	public Discover(Aster source){
		super(source);
		name = "discover";
		job = Job.HUMANITY;
		icon = "actions/observatory.jpg";
		needsTarget = true;
		discovered = true;
	}
	
	public void start(Aster target){
		if (target.discovered()) return;
		super.start(target);
		target.discover();
		super.incrementLevel(0.01);
		super.stop();
	}
}