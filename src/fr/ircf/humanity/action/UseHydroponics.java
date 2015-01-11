package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseHydroponics extends Action {

	public UseHydroponics(Aster source){
		super(source);
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
