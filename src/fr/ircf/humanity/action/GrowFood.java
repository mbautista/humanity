package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.ResourceType;

public class GrowFood extends Action {

	public static double SCALE = 1000;
	
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
		if (state == State.START){
			if (source.getResourceValue(ResourceType.ENERGY) > 0 && source.getResourceValue(ResourceType.WATER) > 0){
				// TODO food equation
				source.incrementResourceValue(ResourceType.FOOD, delta / SCALE);
				source.incrementResourceValue(ResourceType.WATER, - delta / 2 / SCALE);
				source.incrementResourceValue(ResourceType.ENERGY, - delta / 2 / SCALE);
				incrementLevel(delta / SCALE);
			}else{
				stop();
			}
		}
	}
}
