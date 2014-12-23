package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.Random;

public class Galaxy implements Scene, GameElement {
	
	public static int SIZE = 2048;
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
	 * Create a galaxy with with Options.galaxySize stars
	 */
	public void create(){
		create(game.getOptions().getGalaxySize());
	}
	
	/**
	 * Create a galaxy with arbitrary number of stars
	 * @param stars
	 */
	public void create(int stars){
		for (int i=0; i<stars; i++){
			Star star = new Star(this);
			star.create();
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
		for (Star star: sceneStars){
			star.update(delta);
		}
	}

	/**
	 * Update visible stars, from the camera viewport
	 */
	public void updateSceneObjects() {
		sceneStars.clear();
		for(Star star: stars){
			if (game.getCamera().showsViewport(star)){
				sceneStars.add(star);
				star.updateSceneObjects();
			}
		}
	}
	
	public Star getRandomStar(){
		return stars.get(new Random().nextInt(stars.size()));
	}
	
	public Game getGame(){
		return game;
	}
	
	@Override
	public int getSize(){
		return SIZE;
	}
}
