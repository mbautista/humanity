package fr.ircf.humanity.action;

public class Discover extends Action {

	public Discover(){
		name = "discover";
		icon = "life.jpg";
		selectable = true;
	}
	
	public void update(double delta){
		// TODO level
	}
	
	public boolean visible(){
		return true;
	}
}