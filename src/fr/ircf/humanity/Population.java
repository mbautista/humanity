package fr.ircf.humanity;

import fr.ircf.humanity.aster.Planet;

public class Population {

	private Player player;
	private Planet planet;
	private int people;
	private double lifespan;
	
	public Population(Player player, Planet planet){
		this.player = player;
		this.planet = planet;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public double getLifespan() {
		return lifespan;
	}

	public void setLifespan(double lifespan) {
		this.lifespan = lifespan;
	}
}
