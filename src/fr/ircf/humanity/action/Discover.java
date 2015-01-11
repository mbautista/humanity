package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Discover extends Action {

	public Discover(Aster source){
		super(source);
		job = Job.HUMANITY;
		name = "discover";
		icon = "life.jpg";
		needsTarget = true;
	}
	
	public void update(double delta){
		// TODO enable growFood above level 1
		// TODO enable exploration above level 2
	}
	
	public boolean visible(){
		return true;
	}
	
	public void start(Aster target){
		super.start(target);
		target.setDiscovered(true);
		// TODO level up
		super.stop();
	}
}