package fr.ircf.humanity.action;

public class Discover extends Action {

	public Discover(){
		job = Job.HUMANITY;
		name = "discover";
		icon = "life.jpg";
		needsTarget = true;
	}
	
	public void update(double delta){
		// TODO level
	}
	
	public boolean visible(){
		return true;
	}
}