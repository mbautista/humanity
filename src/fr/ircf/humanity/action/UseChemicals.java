package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseChemicals extends Action {

	public UseChemicals(Aster source){
		super(source);
		job = Job.FARMERS;
		name = "useChemicals";
		icon = "customs.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
