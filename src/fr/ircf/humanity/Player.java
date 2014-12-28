package fr.ircf.humanity;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.ui.Button;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public abstract class Player extends Panel implements GameElement{

	public static double MIN_POPULATION = 32, MAX_POPULATION = 128;
	private static int X = 10, DY = 20;
	private Game game;
	private double humanity = 0, kardashev = 0;
	private Text[] texts;
	private Button home;
	// TODO avatar
	// TODO color
	private Planet planet;
	private ArrayList<Population> populations;
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		planet = ((Humanity)game).getGalaxy().getRandomHabitablePlanet();
		// TODO planet = game.getElement(Humanity.GALAXY).getRandomHabitablePlanet();
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
		humanity = 0;
		for (Population population: populations){
			humanity += population.getPeople();
		}
		humanity = Math.round(humanity * 100) / 100;
	}
	
	public int getHeight(){
		return (2 + texts.length) * (texts[0].getHeight() + DY);
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
}