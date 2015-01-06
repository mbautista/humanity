package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.job.Job;
import fr.ircf.humanity.job.JobFactory;
import fr.ircf.humanity.ui.Button;
import fr.ircf.humanity.ui.Component;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public abstract class Player extends Panel implements GameElement{

	private static int X = 10, DY = 20;
	private Game game;
	private double humanity = 0, kardashev = 0;
	private Text[] texts;
	private Button home;
	// TODO avatar
	// TODO color
	private Planet planet;
	private ArrayList<Population> populations;
	private HashMap<Class<?>, Job> jobs;
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		planet = ((Humanity)game).getGalaxy().getRandomHabitablePlanet();
		jobs = JobFactory.getJobs();
		initPopulations();
		initUi();
	}

	private void initUi(){
		texts = new Text[] { new Text(), new Text(), new Text() };
		int i = 2;
		for (Text text: texts){
			text.setPosition(X, (Display.getHeight() - getHeight()) + i * DY);
			i++;
		}
		home = new Button() {
			public void up(){ game.getCamera().show(planet); }
		};
		home.setPosition(X, Display.getHeight() - getHeight());
	}
	
	private void initPopulations(){
		populations = new ArrayList<Population>();
		Population population = new Population(this, planet);
		population.initPeople(1 - game.getOptions().getDifficulty()/100);
		addPopulation(population);
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}

	@Override
	public void render() {
		// TODO avatar	
		home.setText(planet.getName());
		home.render();
		texts[0].setText(game.i18n("player.year") + " : " + planet.getYear());
		texts[1].setText(game.i18n("player.humanity") + " : " + humanity);
		texts[2].setText(game.i18n("player.kardashev") + " : " + kardashev);
		for (Text text: texts){
			text.render();
		}
	}

	@Override
	public void update(double delta) {
		home.update(delta);
		updatePopulations(delta);
		updateHumanity();
		// TODO kardashev
	}
	
	private void updatePopulations(double delta){
		for (Population population: populations){
			population.update(delta);
		}
	}
	
	private void updateHumanity(){
		double old = humanity;
		humanity = 0;
		for (Population population: populations){
			humanity += population.getPeople();
		}
		humanity = Math.round(humanity * 100) / 100d;
		int oi = (int)Math.floor(old);
		int hi = (int)Math.floor(humanity);
		// TODO scanf
		if (oi != hi) game.getLog().add(new Event(game.i18n("event.humanity_passed") + " " + hi + " " + game.i18n("event.billions")));
	}
	
	public int getHeight(){
		return (2 + texts.length) * DY;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	
	public void addPopulation(Population population){
		populations.add(population);
	}
	
	public double getZMin(){
		return Camera.Z_MIN; // TODO optics level
	}
}