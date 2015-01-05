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
		MIN_PEOPLE = 32, MAX_PEOPLE = 64,
		MIN_LIFESPAN = 32, MAX_LIFESPAN = 64,
		MIN_FERTILITY = 0.1, MAX_FERTILITY = 3;
	private Player player;
	private Planet planet;
	private double people, lifespan, fertility;
	private HashMap<Class<?>, Job> jobs;
	
	public Population(Player player, Planet planet){
		this.player = player;
		this.planet = planet;
		this.planet.setPopulation(this);
		jobs = JobFactory.getJobs();
	}

	public void initPeople(double factor){
		people = Random.betweenWithFactor(MIN_PEOPLE, MAX_PEOPLE, factor); // TODO random centered gaussian
		lifespan = Random.betweenWithFactor(MIN_LIFESPAN, MAX_LIFESPAN, factor); // TODO random centered gaussian
		fertility = Random.betweenWithFactor(MIN_FERTILITY, MAX_FERTILITY, factor); // TODO random centered gaussian
		planet.addResource(ResourceType.PEOPLE, people);
	}
	
	public void update(double delta){
		// TODO lifespan, fertility, actions
		updatePeople(delta);
	}
	
	/**
	 * Update people on planet depending on fertility and lifespan
	 * @param delta
	 */
	// TODO move this to Aster.updateResources() (?)
	public void updatePeople(double delta){
		double peopleInYear = people * (fertility - 1) / lifespan;
		people += peopleInYear * delta / planet.getHoursInYear();
		planet.getResource(ResourceType.PEOPLE).setValue(people);
		planet.getResource(ResourceType.PEOPLE).setDelta(peopleInYear);
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
