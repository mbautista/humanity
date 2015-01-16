package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.ResourceType;

public class GrowFood extends Action {

	public static double SCALE = 10000;
	
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
				double foodInYear = getFoodInYear();
				source.updateResource(delta, ResourceType.FOOD, foodInYear);
				source.updateResource(delta, ResourceType.WATER, -foodInYear / 2);
				source.updateResource(delta, ResourceType.ENERGY, -foodInYear / 2);
				incrementLevel(getEfficiency() * people/100 * delta / SCALE); // TODO level equation
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
		return getEfficiency() * people/100 * source.getResourceValue(ResourceType.PEOPLE);
	}
	
	/**
	 * Efficiency level : number of people a farmer can feed in 1 year (including himself)
	 * @return
	 */
	private double getEfficiency(){
		return 0.01 + getLevel(); // can't be zero
	}
}
