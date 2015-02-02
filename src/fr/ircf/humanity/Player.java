package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.opengl.Display;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.action.Explore;
import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.ui.Button;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public abstract class Player extends Panel implements GameElement{

	private static int X = 10, DY = 20;
	public static double MAX_LEVEL = 3;
	private Game game;
	private double humanity = 0, level = 0;
	private Text[] texts;
	private Button home;
	// TODO avatar
	// TODO color
	private Planet planet;
	private ArrayList<Population> populations;
	private HashMap<Class<?>, Double> levels;
	private Action action;
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		planet = ((Humanity)game).getGalaxy().getRandomHabitablePlanet();
		initPopulations();
		initUi();
		initLevels();
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
	
	private void initLevels(){
		levels = new HashMap<Class<?>, Double>();
		for (Class<?> actionClass : Action.CLASSES){
			levels.put(actionClass, 0d);
		}
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}

	@Override
	public void render() {
		// TODO avatar	
		if (planet.discovered()){
			home.setText(planet.getName());
			home.render();
		}
		texts[0].setText(game.i18n("player.year") + " : " + planet.getYear());
		texts[1].setText(game.i18n("player.humanity") + " : " + (Math.floor(humanity*100)/100d));
		texts[2].setText(game.i18n("player.level") + " : " + (Math.floor(level*100)/100d));
		for (Text text: texts){
			text.render();
		}
	}

	@Override
	public void update(double delta) {
		home.update(delta);
		updatePopulations(delta);
		updateHumanity();
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
		// FIXME Humanity ends
		if (humanity < 0.01) game.setState(State.END);
		// Humanity billions event
		if (Math.floor(humanity) > Math.floor(old)){
			game.getLog().addEvent(String.format(game.i18n("event.billion" + (humanity<2?"":"s")), Math.floor(humanity)));	
		}
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public boolean canZoomOut() {
		return level > 0.02;
	}
	
	public void incrementLevel(Class<?> actionClass, double delta){
		double old = level;
		levels.put(actionClass, levels.get(actionClass) + delta);
		level += delta;
		// Humanity kardashev event
		if (Math.floor(level * 100) > Math.floor(old * 100)){
			game.getLog().addEvent(String.format(game.i18n("event.kardashev"), Math.floor(level * 100)/100d));	
		}
	}
	
	public double getLevel(Class<?> actionClass){
		return levels.get(actionClass);
	}
	
	public boolean hasLevels(HashMap<Class<?>, Double> requiredLevels){
		for (Entry<Class<?>, Double> e : requiredLevels.entrySet()){
			if (getLevel(e.getKey()) < e.getValue()) return false;
		}
		return true;
	}
	
	public double getLevel(){
		return level;
	}
	
	public Game getGame(){
		return game;
	}
}