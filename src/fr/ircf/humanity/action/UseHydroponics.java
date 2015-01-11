package fr.ircf.humanity.action;


public class UseHydroponics extends Action {

	public UseHydroponics(){
		job = Job.FARMERS;
		name = "useHydroponics";
		icon = "barracks.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
