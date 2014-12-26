package fr.ircf.humanity;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class Star extends Aster {

	public static double MIN_SIZE = 1, MAX_SIZE = 4,
			RED_GIANT_ENERGY = 128, MIN_ENERGY = 32, MAX_ENERGY = 512,
			MIN_Z_FOR_PLANETS = 5,
			VIEWPORT_SIZE = Galaxy.SIZE / Math.pow(2, MIN_Z_FOR_PLANETS);
	private Galaxy galaxy;
	private ArrayList<Planet> planets, scenePlanets;
	
	public Star(Galaxy galaxy){
		this.galaxy = galaxy;
		planets = new  ArrayList<Planet>();
		scenePlanets = new ArrayList<Planet>();
	}
	
	/**
	 * Create a star in the galaxy :
	 * generate (x,y) randomly on a gaussian and spiral distribution (spiral shape)
	 * viewport represents the star system size (actually constant)
	 * size represents the star size
	 * size and color depend on energy, they may also be computed during updateStar()
	 * each star have at least one planet (for game convenience)
	 */
	@Override
	public void create(){
		// TODO avoid creating too close stars
		double angle = randomGaussian(Math.PI);
		distance = galaxy.getSpiralDistance(angle) + randomGaussian(Galaxy.ARM_WIDTH);
		if (random.nextInt(2)==0) angle+= Math.PI; // randomly choose arm
		x = distance * Math.cos(angle);
		y = distance * Math.sin(angle);
		// FIXME star viewport depends on Camera zoom
		viewport = new Rectangle2D.Double(
				x-VIEWPORT_SIZE,
				y-VIEWPORT_SIZE,
				2*VIEWPORT_SIZE,
				2*VIEWPORT_SIZE
		);
		energy = randomBetween(MIN_ENERGY, MAX_ENERGY);
		name = randomName();
		updateSize();
		updateColor();
		createPlanets(1 + random.nextInt(galaxy.getGame().getOptions().getStarSize()));
	}

	private void createPlanets(int planets){
		for (int i=0; i<planets; i++){
			Planet planet = new Planet(this);
			planet.create(i, planets);
			this.planets.add(planet);
		}
	}
	
	public void createSuperMassiveBlackHole(){
		energy = Galaxy.SIZE;
		viewport = new Rectangle2D.Double(x, y, x+size, y+size);
		updateSize();
		updateColor();
	}
	
	/**
	 * Render star system
	 */
	@Override
	public void render(){
		if (getCamera().shows(this)){
			renderStar();
		}
		for(Planet planet: scenePlanets){
			planet.render();
		}
	}

	/**
	 * Render star only
	 */
	private void renderStar(){
		// TODO enlighten sphere
		GL11.glPushMatrix();
		GL11.glTranslated(getScreenX(), getScreenY(), getScreenZ());
		GL11.glColor3f(color[0], color[1], color[2]);
		Sphere s = new Sphere();
		s.draw(Math.max(1, (float)(size*getScreenZ())), getPolygons(), getPolygons());
		GL11.glPopMatrix();
	}
	
	/**
	 * Update star and planets
	 * @param delta
	 */
	@Override
	public void update(double delta){
		if (getCamera().shows(this)){
			updateStar(delta);
		}
		for(Planet planet: scenePlanets){
			planet.update(delta);
		}
	}
	
	/**
	 * Update star only
	 * @param delta
	 */
	private void updateStar(double delta){
		// TODO position, energy, size, color
		super.update(delta);
	}

	/**
	 * Update star color from energy
	 * @see http://www.tannerhelland.com/4435/convert-temperature-rgb-algorithm-code/
	 */
	private void updateColor(){
		double t = energy * 100 / MAX_ENERGY;
		if (t <= 66){
			color[0] = 1;
			color[1] = (float) Math.min(1, Math.max(0, 0.388557823 * Math.log(t) - 0.629373313));
			color[2] = (float) Math.min(1, Math.max(0, 0.541084888 * Math.log(t - 10) - 1.191581222));
		}else{
			color[0] = (float) Math.min(1, Math.max(0, 1.287885654 * Math.pow(t - 60, -0.1332047592)));
			color[1] = (float) Math.min(1, Math.max(0, 1.125477225 * Math.pow(t - 60, -0.0755148492)));
			color[2] = 1;
		}
	}

	/**
	 * Updates star size from energy
	 * using a simple arbitrary peak function for performance
	 * size(energy = 0) = 0 (e.g. dead star, TODO BLACK HOLE)
	 * size(energy = RED_GIANT_ENERGY) = MAX_SIZE (e.g. red giant)
	 * size(energy = MAX_ENERGY) = MIN_SIZE (e.g. blue dwarf)
	 */
	private static double SIZE_PEAK_A = MAX_SIZE / RED_GIANT_ENERGY;
	private static double SIZE_PEAK_B = (MIN_SIZE - MAX_SIZE) / (MAX_ENERGY - RED_GIANT_ENERGY);
	private static double SIZE_PEAK_C = MIN_SIZE - SIZE_PEAK_B * MAX_ENERGY;
	private void updateSize() {
		if (energy < RED_GIANT_ENERGY){
			size = (energy * SIZE_PEAK_A);
		}else{
			size = (SIZE_PEAK_B * energy + SIZE_PEAK_C);
		}
		size = Math.max(0.2f, size); // FIXME min size should be 0, be not reachable on create
	}
	
	/**
	 * Update visible planets in this star system, from camera viewport
	 */
	public void updateSceneObjects() {
		scenePlanets.clear();
		if (getCamera().getZ()>=MIN_Z_FOR_PLANETS){
			for(Planet planet: planets){
				if (getCamera().shows(planet)){
					scenePlanets.add(planet);
				}
			}
		}
	}
	
	@Override
	public Camera getCamera(){
		return galaxy.getGame().getCamera();
	}

	public Planet getPlanet(int rank){
		return planets.get(rank);
	}
	
	public Planet getRandomPlanet(){
		return planets.get(random.nextInt(planets.size()));
	}
}