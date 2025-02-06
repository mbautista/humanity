package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.ResourceType;

public class GrowFood extends Action {

	public static double LEVEL_SPEED = 0.0001,
		MIN_WATER_PER_FOOD = 0.1, MAX_WATER_PER_FOOD = 0.5,
		MIN_ENERGY_PER_FOOD = 0.1, MAX_ENERGY_PER_FOOD = 0.5;
	
	public GrowFood(Aster source){
		super(source);
		name = "growFood";
		job = Job.FARMERS;
		icon = "actions/animals.jpg";
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
				source.incrementResourceDelta(ResourceType.FOOD, foodInYear);
				source.incrementResourceDelta(ResourceType.WATER, - foodInYear * getWaterPerFood());
				source.incrementResourceDelta(ResourceType.ENERGY, - foodInYear * getEnergyPerFood());
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
		return (1 - getDifficulty()/100 + getLevel()) * people/100 * source.getResourceValue(ResourceType.PEOPLE);
	}
	
	/**
	 * Water consumption per food unit
	 */
	private double getWaterPerFood(){
		return MIN_WATER_PER_FOOD; // TODO dynamize water consumption per food unit
	}
	
	/**
	 * Water consumption per food unit
	 */
	private double getEnergyPerFood(){
		return MIN_ENERGY_PER_FOOD; // TODO dynamize energy consumption per food unit
	}
}
