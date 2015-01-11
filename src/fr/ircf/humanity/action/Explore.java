package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class Explore extends Action {

	public Explore(Aster source){
		super(source);
		job = Job.HUMANITY;
		name = "explore";
		icon = "observatory.jpg";
		needsTarget = true;
	}
	
	public void update(double delta){
		// TODO level
	}
	
	public boolean visible(){
		return false; // TODO depends on discovery level
	}
	
	public void start(Aster target){
		super.start(target);
		source.getCamera().show(target);
		// TODO level up
		super.stop();
	}
}