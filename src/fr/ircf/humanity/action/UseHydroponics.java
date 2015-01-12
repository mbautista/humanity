package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseHydroponics extends Action {

	public UseHydroponics(Aster source){
		super(source);
		name = "useHydroponics";
		job = Job.FARMERS;
		icon = "barracks.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO run action
	}
}
