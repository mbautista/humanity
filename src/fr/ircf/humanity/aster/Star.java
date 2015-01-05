package fr.ircf.humanity.aster;

import java.awt.geom.Rectangle2D;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import fr.ircf.humanity.Camera;
import fr.ircf.humanity.Galaxy;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.Random;
import fr.ircf.humanity.SceneObject;

public class Star extends Aster {

	public static double MIN_SIZE = 1, MAX_SIZE = 4,
			RED_GIANT_ENERGY = 128, MIN_ENERGY = 32, MAX_ENERGY = 512,
			MIN_Z_FOR_PLANETS = 7;
	public static int FIRST_STAR_PLANETS = 5; // Ensure we have at least one habitable planet for the player
	private Galaxy galaxy;
	private ArrayList<Planet> planets, scenePlanets;
	
	public Star(Galaxy galaxy){
		super();
		this.galaxy = galaxy;
		type = AsterType.STAR;
		planets = new  ArrayList<Planet>();
		scenePlanets = new ArrayList<Planet>();
	}
	
	/**
	 * Create a star in the galaxy :
	 * generate (x,y) randomly on a gaussian and spiral distribution (spiral shape)
	 * external viewport represents the star system size (constant)
	 * size and color depend on energy, they may also be computed during updateStar()
	 * each star have at least one planet (for game convenience)
	 * @boolean first (player) star system in the galaxy
	 */
	@Override
	public void create(){
		create(false);
	}
	public void create(boolean first){
		createPosition();
		addResource(ResourceType.ENERGY, Random.between(MIN_ENERGY, MAX_ENERGY));
		name = Random.name();
		updateSize();
		updateViewport();
		updateColor();
		createPlanets(first ? FIRST_STAR_PLANETS : Random.nextInt(galaxy.getGame().getOptions().getStarSize()));
		super.create();
	}

	private void createPosition(){
		double angle = Math.abs(Random.gaussian(Math.PI/2));
		if (angle < Galaxy.BULB_LIMIT){
			// bulb
			x = Random.gaussian(Galaxy.BULB_WIDTH);
			y = Random.gaussian(Galaxy.BULB_HEIGHT);
		}else{
			// arm
			distance = galaxy.getSpiralDistance(angle) + Random.gaussian(Galaxy.ARM_WIDTH);
			if (Random.nextInt(2)==0) angle+= Math.PI; // randomly choose arm
			x = distance * Math.cos(angle);
			y = distance * Math.sin(angle);
		}
		extendedViewport = new Rectangle2D.Double(
			x - Planet.MAX_LOCALX,
			y - Planet.MAX_LOCALX,
			2 * Planet.MAX_LOCALX,
			2 * Planet.MAX_LOCALX
		);
		if (aStarIsTooClose()) createPosition();
	}
	
	private boolean aStarIsTooClose(){
		for (Star star: galaxy.getStars()){
			if (star.getExtendedViewport().intersects(extendedViewport)) return true;
		}
		return false;
	}
	
	private void createPlanets(int planets){
		for (int i=0; i<planets; i++){
			Planet planet = new Planet(this);
			planet.create(i, planets);
			this.planets.add(planet);
		}
	}
	
	/**
	 * Render star system
	 */
	@Override
	public void render(){
		if (isEnlighten() || getCamera().shows(this)){
			renderStar();
		}
		for(Planet planet: scenePlanets){
			planet.render();
		}
		super.render();
	}

	/**
	 * Render star itself
	 */
	private void renderStar(){
		GL11.glPushMatrix();
		GL11.glTranslated(getScreenX(), getScreenY(), getScreenZ());
		if (isEnlighten()) renderLight();		
		GL11.glColor3f(color[0], color[1], color[2]);
		Sphere s = new Sphere();
		s.draw(Math.max(1, (float)getScreenSize()), getPolygons(), getPolygons());
		GL11.glPopMatrix();
	}
	
	private void renderLight(){
		FloatBuffer position = BufferUtils.createFloatBuffer(4);
		position.put(new float[] { 0f, 0f, 0f, 1f, });
		position.flip();
		
		// TODO simplify color handling (use FloatBuffer everywhere ?)
		FloatBuffer color = BufferUtils.createFloatBuffer(4);
		color.put(new float[] { this.color[0], this.color[1], this.color[2], 1f, });
		color.flip();
		FloatBuffer ambiant = BufferUtils.createFloatBuffer(4);
		ambiant.put(new float[] { this.color[0], this.color[1], this.color[2], 0.1f, });
		ambiant.flip();
		FloatBuffer specular = BufferUtils.createFloatBuffer(4);
		specular.put(new float[] { this.color[0], this.color[1], this.color[2], 1f, });
		specular.flip();
		
		// TODO tweak ambiant/specular to add "glow" effect
		GL11.glEnable(GL11.GL_LIGHT0);
		//GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, color);
	    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, color);
	    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, ambiant);
	    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, specular);
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_EMISSION, color);
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
	}
	
	public boolean isEnlighten(){
		SceneObject o = getCamera().getObject();
		return o == this || o instanceof Planet && ((Planet)o).getStar() == this;
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
		for(Planet planet: planets ){//scenePlanets){
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
	 * Update star color and type from energy
	 * @see http://www.tannerhelland.com/4435/convert-temperature-rgb-algorithm-code/
	 */
	private void updateColor(){
		double t = getResourceValue(ResourceType.ENERGY) * 100 / MAX_ENERGY;
		if (t <= 66){
			color[0] = 1;
			color[1] = (float) Math.min(1, Math.max(0, 0.388557823 * Math.log(t) - 0.629373313));
			color[2] = (float) Math.min(1, Math.max(0, 0.541084888 * Math.log(t - 10) - 1.191581222));
			type = t < 50 ? AsterType.RED_GIANT_STAR : AsterType.MEDIUM_STAR;
		}else{
			color[0] = (float) Math.min(1, Math.max(0, 1.287885654 * Math.pow(t - 60, -0.1332047592)));
			color[1] = (float) Math.min(1, Math.max(0, 1.125477225 * Math.pow(t - 60, -0.0755148492)));
			color[2] = 1;
			type = t < 80 ? AsterType.MEDIUM_STAR : AsterType.BLUE_STAR;
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
		if (getResourceValue(ResourceType.ENERGY) < RED_GIANT_ENERGY){
			size = getResourceValue(ResourceType.ENERGY) * SIZE_PEAK_A;
		}else{
			size = getResourceValue(ResourceType.ENERGY) * SIZE_PEAK_B + SIZE_PEAK_C;
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
	public Game getGame(){
		return galaxy.getGame();
	}
	
	@Override
	public Camera getCamera(){
		return galaxy.getGame().getCamera();
	}

	public ArrayList<Planet> getPlanets(){
		return planets;
	}
	
	public Planet getPlanet(int rank){
		return planets.get(rank);
	}
}