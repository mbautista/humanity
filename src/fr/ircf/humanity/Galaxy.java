package fr.ircf.humanity;

import java.util.ArrayList;

public class Galaxy extends Aster implements Scene, GameElement {
	
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

	@Override
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

	@Override
	public void create(){
		create(game.getOptions().getGalaxySize());
	}
	
	public void create(int stars){
		for (int i=0; i<stars; i++){
			Star star = new Star(this);
			star.create();
			this.stars.add(star);
		}
	}

	public void updateSceneObjects() {
		sceneStars.clear();
		for(Star star: stars){
			if (game.getCamera().shows(star)){ // FIXME shows(star system) instead of star
				sceneStars.add(star);
			}
		}
	}
	
	public Star getRandomStar(){
		return stars.get(random.nextInt(stars.size()));
	}
	
	public Game getGame(){
		return game;
	}
}
