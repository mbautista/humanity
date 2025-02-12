package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Explore extends Action {

	public static double LEVEL_SPEED = 0.0001;
	
	public Explore(Aster source){
		super(source);
		name = "explore";
		job = Job.HUMANITY;
		icon = "actions/life.jpg";
		needsTarget = true;
		// TODO needsPeople = true;
		requiresLevels.put(Study.class, 0.02d);
		requiresLevels.put(Train.class, 0.02d);
	}
	
	public void start(Aster target){
		super.start(target);
		source.getCamera().show(target);
		super.incrementLevel(0.01);
		super.stop();
	}
}