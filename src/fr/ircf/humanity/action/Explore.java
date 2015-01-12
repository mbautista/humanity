package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Explore extends Action {

	public Explore(Aster source){
		super(source);
		name = "explore";
		job = Job.HUMANITY;
		icon = "observatory.jpg";
		needsTarget = true;
	}
	
	public void update(double delta){
		// TODO run action
	}
	
	public void start(Aster target){
		super.start(target);
		source.getCamera().show(target);
		super.incrementLevel(1);
		super.stop();
	}
}