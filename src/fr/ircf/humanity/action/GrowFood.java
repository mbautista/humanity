package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.ResourceType;

public class GrowFood extends Action {

	public static double LEVEL_SPEED = 0.0001;
	
	public GrowFood(Aster source){
		super(source);
		name = "growFood";
		job = Job.FARMERS;
		icon = "animals.jpg";
		needsPeople = true;
		requiresLevels.put(Discover.class, 0.01d);
	}
	
	public void start(){
		super.start();
		if (getLevel() <= 0) incrementLevel(0.01);
	}
	
	public void update(double delta){
		super.update(delta);
		if (state == State.START){
			if (source.getResourceValue(ResourceType.ENERGY) > 0 && source.getResourceValue(ResourceType.WATER) > 0){
				double foodInYear = getFoodInYear();
				source.updateResource(delta, ResourceType.FOOD, foodInYear);
				source.updateResource(delta, ResourceType.WATER, -foodInYear / 2);
				source.updateResource(delta, ResourceType.ENERGY, -foodInYear / 2);
				incrementLevel(getLevel() * people/100 * delta * LEVEL_SPEED);
			}else{
				stop();
			}
		}
	}
	
	/**
	 * Food equation : Computes how much food is produced in one year
	 * @return
	 */
	private double getFoodInYear(){
		return getLevel() * people/100 * source.getResourceValue(ResourceType.PEOPLE);
	}
}
