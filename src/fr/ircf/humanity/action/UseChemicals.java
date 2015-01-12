package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseChemicals extends Action {
	
	public UseChemicals(Aster source){
		super(source);
		name = "useChemicals";
		job = Job.FARMERS;
		icon = "customs.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO run action
	}
}
