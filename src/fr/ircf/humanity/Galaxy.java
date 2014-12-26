package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;

public class Galaxy implements Scene, GameElement {
	
	public static double SIZE = 8192, PITCH = 0.5, WINDING = 8, ARM_WIDTH = 200;
	private Game game;
	private ArrayList<Star> stars, sceneStars;
	private Star smbh; // Super massive black hole
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		stars = new ArrayList<Star>();
		sceneStars = new ArrayList<Star>();
		// TODO smbh = new Star(this);
		// TODO smbh.createSuperMassiveBlackHole();
		// TODO stars.add(smbh);
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
	// FIXME Concurrent Modification Exception caused by zooming/clicking asters
	public void update(double delta) {
		try{
			for (Star star: sceneStars){
				star.update(delta);
			}
		}catch(Exception e){}
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
	public double getSize(){
		return SIZE;
	}

	public int getStarSize(){
		return stars.size();
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
}
