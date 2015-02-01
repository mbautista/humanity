package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Explore extends Action {

	public static double LEVEL_MAX = 3;
	public static double LEVEL_SPEED = 0.0001;
	
	public Explore(Aster source){
		super(source);
		name = "explore";
		job = Job.HUMANITY;
		icon = "observatory.jpg";
		//needsTarget = true;
		needsPeople = true;
		requiresLevels.put(GrowFood.class, 0.02d);
	}
	
	// TODO move this to Observe or Discover action
	/*public void start(Aster target){
		super.start(target);
		source.getCamera().show(target);
		super.incrementLevel(0.01);
		super.stop();
	}*/
	
	public void start(){
		super.start();
		if (getLevel() <= 0) incrementLevel(0.01);
	}
	
	public void update(double delta){
		super.update(delta);
		if (state == State.START){
			incrementLevel(getLevel() * people/100 * delta * LEVEL_SPEED);
		}
	}
}