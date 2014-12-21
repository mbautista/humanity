package fr.ircf.humanity;

import java.util.ArrayList;

public class Star extends Aster {

	public static int MIN_SIZE = 2, MAX_SIZE = 8,
			MIN_ENERGY = 512, MAX_ENERGY = 1024;
	private Galaxy galaxy;
	private ArrayList<Planet> planets, scenePlanets;
	
	public Star(Galaxy galaxy){
		this.galaxy = galaxy;
		this.planets = new  ArrayList<Planet>();
	}
	
	@Override
	public void create(){
		// TODO spiral repartition
		x = (int)(Galaxy.SIZE/2 * (1 + random.nextGaussian()));
		y = (int)(Galaxy.SIZE/2 * (1 + random.nextGaussian()));
		size = MIN_SIZE + random.nextInt(MAX_SIZE - MIN_SIZE);
		energy = MIN_ENERGY + random.nextInt(MAX_ENERGY - MIN_ENERGY);
		createPlanets(1 + random.nextInt(galaxy.getGame().getOptions().getStarSize()));
	}
	
	private void createPlanets(int planets){
		for (int i=0; i<planets; i++){
			Planet planet = new Planet(this);
			planet.create();
			this.planets.add(planet);
		}
	}
	
	@Override
	public void render(){
		if (galaxy.getGame().getCamera().shows(this)){
			// TODO render star
		}
		// TODO render scene planets
	}

	//@Override
	public void update(double delta){
		// TODO update scene planets
	}
	
	public Planet getRandomPlanet(){
		return planets.get(random.nextInt(planets.size()));
	}
	
	public float getHabitability(){
		return galaxy.getGame().getOptions().getHabitability();
	}
}
