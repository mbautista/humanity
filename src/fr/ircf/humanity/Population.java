package fr.ircf.humanity;

import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.Planet;

public class Population {

	public static double
		MIN_PEOPLE = 32, MAX_PEOPLE = 128,
		MIN_LIFESPAN = 32, MAX_LIFESPAN = 128;
	private Player player;
	private Planet planet;
	private double people, lifespan;
	
	public Population(Player player, Planet planet){
		this.player = player;
		this.planet = planet;
	}

	public void initPeople(double factor){
		people = Random.betweenWithFactor(MIN_PEOPLE, MAX_PEOPLE, factor);
		lifespan = Random.betweenWithFactor(MIN_LIFESPAN, MAX_LIFESPAN, factor);
	}
	
	public void update(double delta){
		// TODO people, lifespan
		planet.setResource(Aster.PEOPLE, people);
	}
	
	public double getPeople() {
		return people;
	}

	public void setPeople(double people) {
		this.people = people;
	}

	public double getLifespan() {
		return lifespan;
	}

	public void setLifespan(double lifespan) {
		this.lifespan = lifespan;
	}
}
