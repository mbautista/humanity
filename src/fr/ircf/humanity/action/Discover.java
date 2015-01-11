package fr.ircf.humanity.action;

public class Discover extends Action {

	public static String name = "discover";
	public static String icon = "life.jpg";
	
	public Discover(){
		discovered = true;
		selectable = true;
	}
	
	public void update(double delta){
		// TODO level
	}
}
