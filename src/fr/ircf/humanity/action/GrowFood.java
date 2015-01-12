package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;

public class GrowFood extends Action {

	public GrowFood(Aster source){
		super(source);
		name = "growFood";
		job = Job.FARMERS;
		icon = "animals.jpg";
		needsPeople = true;
		requiresLevels.put(Discover.class, 1d);
	}
	
	public void update(double delta){
		super.update(delta);
		// TODO run action
	}
}
