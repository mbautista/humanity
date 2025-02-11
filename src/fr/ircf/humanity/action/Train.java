package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.ResourceType;

public class Train extends Action {
	
	public Train(Aster source){
		super(source);
		name = "train";
		job = Job.ARMY;
		icon = "actions/barracks.jpg";
		needsPeople = true;
		requiresLevels.put(GrowFood.class, 0.01d);
	}
	
	public void start(){
		super.start();
		if (getLevel() <= 0) incrementLevel(0.01);
	}
	
	public void update(double delta){
		super.update(delta);
	}
}
