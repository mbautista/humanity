package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseHydroponics extends Action {

	public static String NAME = "useHydroponics";
	public static Job JOB = Job.FARMERS;
	
	public UseHydroponics(Aster source){
		super(source);
		icon = "barracks.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
