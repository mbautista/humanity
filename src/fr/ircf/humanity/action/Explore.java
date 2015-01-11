package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Explore extends Action {

	public static String NAME = "explore";
	public static Job JOB = Job.HUMANITY;
	
	public Explore(Aster source){
		super(source);
		icon = "observatory.jpg";
		needsTarget = true;
	}
	
	public void update(double delta){
		// TODO discover something above certain level ?
	}
	
	public void start(Aster target){
		super.start(target);
		source.getCamera().show(target);
		super.updateLevel(1);
		super.stop();
	}
}