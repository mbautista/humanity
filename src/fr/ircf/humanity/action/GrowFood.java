package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class GrowFood extends Action {

	public static String NAME = "growFood";
	public static Job JOB = Job.FARMERS;
	
	public GrowFood(Aster source){
		super(source);
		icon = "animals.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
