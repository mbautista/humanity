package fr.ircf.humanity;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import fr.ircf.humanity.aster.Planet;
import fr.ircf.humanity.aster.AsterType;
import fr.ircf.humanity.aster.Star;

public class Galaxy implements Scene, GameElement {
	
	public static double SIZE = 16000, PITCH = 0.5, WINDING = 8,
			ARM_WIDTH = 400, BULB_LIMIT = 0.2, BULB_WIDTH = 1600, BULB_HEIGHT = 400;
	private Game game;
	private ArrayList<Star> stars, sceneStars;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		stars = new ArrayList<Star>();
		sceneStars = new ArrayList<Star>();
		create(1);
	}

	@Override
	public boolean visible() {
		return true;
	}
	
	/**
	 * Create a galaxy with arbitrary number of stars
	 * @param stars
	 */
	public void create(int stars){
		for (int i=0; i<stars; i++){
			Star star = new Star(this);
			star.create(stars==1);
			this.stars.add(star);
		}
	}
	
	@Override
	// TODO render camera first (e.g. global glTanslate)
	public void render(){
		for (Star star: sceneStars){
			star.render();
		}
	}
	
	@Override
	public void update(double delta) {
		for (Star star: stars){ //sceneStars){
			star.update(delta);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
	        game.setState(State.PAUSE);
	    }
	}

	/**
	 * Update visible stars, from the camera viewport
	 */
	public void updateSceneObjects() {
		sceneStars.clear();
		for(Star star: stars){
			if (game.getCamera().showsExtendedViewport(star)){
				sceneStars.add(star);
				star.updateSceneObjects();
			}
		}
	}
	
	/**
	 * Get a spiral distance from an angle a in this galaxy according to its size, pitch and winding
	 * @see http://arxiv.org/pdf/0908.0892.pdf
	 * @param an angle
	 * @return distance in the spiral
	 */
	public double getSpiralDistance(double a){
		return SIZE / Math.log(PITCH * Math.tan(a / WINDING));
	}

	/**
	 * Get a random habitable planet in the galaxy (for the player)
	 * @return Planet
	 */
	public Planet getRandomHabitablePlanet(){
		Planet planet = null;
		for (Star s: stars){
			for (Planet p: s.getPlanets()){
				if (p.getType() == AsterType.HABITABLE_PLANET) return p;
			}
		}
		return planet;
	}
	
	public Game getGame(){
		return game;
	}
	
	@Override
	public double getSize(){
		return SIZE;
	}

	public int getStarSize(){
		return stars.size();
	}
	
	public ArrayList<Star> getStars() {
		return stars;
	}
}
