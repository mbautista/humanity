package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;


public class UseChemicals extends Action {
	
	public UseChemicals(Aster source){
		super(source);
		name = "useChemicals";
		job = Job.FARMERS;
		icon = "actions/customs.jpg";
		needsPeople = true;
		requiresLevels.put(GrowFood.class, 0.4d);
	}
	
	public void update(double delta){
		super.update(delta);
		// TODO run action
	}
}
