package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseChemicals extends Action {

	public static String NAME = "useChemicals";
	public static Job JOB = Job.FARMERS;
	
	public UseChemicals(Aster source){
		super(source);
		icon = "customs.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
