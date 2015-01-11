package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.opengl.Display;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.action.ActionMenuItem;
import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.ui.Button;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public abstract class Player extends Panel implements GameElement{

	private static int X = 10, DY = 20;
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
	private boolean canExplore = false;
	
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
		texts[1].setText(game.i18n("player.humanity") + " : " + humanity);
		texts[2].setText(game.i18n("player.kardashev") + " : " + level);
		for (Text text: texts){
			text.render();
		}
	}

	@Override
	public void update(double delta) {
		home.update(delta);
		updatePopulations(delta);
		updateHumanity();
		// TODO level
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
		// Humanity ends
		if (humanity <= 0) game.setState(State.END);
		// Humanity passed billions event
		int oi = (int)Math.floor(old);
		int hi = (int)Math.floor(humanity);
		if (hi > oi){
			game.getLog().addEvent(String.format(game.i18n("event.humanity_passed_billion" + (hi>1?"s":"")), hi));	
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
	
	public double getZMin(){
		return Camera.Z_MIN; // TODO optics level
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public boolean canExplore() {
		return canExplore;
	}

	public void setCanExplore(boolean canExplore) {
		this.canExplore = canExplore;
	}
	
	public void updateLevel(Class<?> actionClass, double delta){
		levels.put(actionClass, levels.get(actionClass) + delta);
	}
}