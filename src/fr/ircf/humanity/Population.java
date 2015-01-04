package fr.ircf.humanity;

import java.util.HashMap;

import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.job.Job;
import fr.ircf.humanity.job.JobFactory;

public class Population {

	public static double
		MIN_PEOPLE = 32, MAX_PEOPLE = 128,
		MIN_LIFESPAN = 32, MAX_LIFESPAN = 128;
	private Player player;
	private Planet planet;
	private double people, lifespan;
	private HashMap<Class<?>, Job> jobs;
	
	public Population(Player player, Planet planet){
		this.player = player;
		this.planet = planet;
		this.planet.setPopulation(this);
		jobs = JobFactory.getJobs();
	}

	public void initPeople(double factor){
		people = Random.betweenWithFactor(MIN_PEOPLE, MAX_PEOPLE, factor);
		lifespan = Random.betweenWithFactor(MIN_LIFESPAN, MAX_LIFESPAN, factor);
		planet.addResource(ResourceType.PEOPLE, people);
	}
	
	public void update(double delta){
		// TODO people, lifespan, actions
		planet.getResource(ResourceType.PEOPLE).setValue(people);
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

	public HashMap<Class<?>, Job> getJobs() {
		return jobs;
	}

	public void setJobs(HashMap<Class<?>, Job> jobs) {
		this.jobs = jobs;
	}

	public Job getJob(Class<?> key) {
		return jobs.get(key);
	}
}
