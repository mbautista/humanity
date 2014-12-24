package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Planet extends Aster {

	public static float MAX_LOCALX = 64,
			MIN_WATER = 64, MAX_WATER = 128,
			MIN_ATMOSPHERE = 64, MAX_ATMOSPHERE = 128,
			MIN_ENERGY = 64, MAX_ENERGY = 128,
			MIN_SIZE = 0.2f, MAX_SIZE = 2,
			MIN_HOURS = 10, MAX_HOURS = 30,
			MAX_SATELLITES = 20;
	public static float MIN_INTENSITY = 0.3f, MAX_INTENSITY = 0.7f;
	private Star star;
	private int satellites = 0;
	private float localX, localY, water = 0, atmosphere = 0, hours, hour = 0;
	private ArrayList<Population> populations;
	private PlanetType type = PlanetType.ROCKY;
	private enum Rings { NONE, THIN, LARGE };
	private Rings rings = Rings.NONE;
	private static HashMap<String, Texture> textures;
	private Texture texture;
	
	public Planet(Star star){
		this.star = star;
	}
	
	/**
	 * Randomly create a planet :
	 * generate a random planet around the star
	 * distance (r) depends on bode's law
	 * type and size depends on distance (r)
	 * energy and satellites depends on size
	 * HABITABLE are located in the habitable zone and have water and atmosphere
	 * GAZEOUS may have rings
	 * TODO water should depend on atmosphere (or inversely)
	 * TODO HABITABLE may have AI populations
	 * color intensity depends on type
	 * @param planet's rank around the star, needed for our modified BODE law
	 * @param number of planets around the star, needed for our modified BODE law
	 */
	public void create(int rank){
		double r = bode(rank) * star.getSize() * 2;
		double a = 0; // TODO 2 * random.nextDouble() * Math.PI;
		localX = (float)(r * Math.cos(a));
		localY = (float)(r * Math.sin(a));
		double t = r/MAX_LOCALX - 0.5;
		type =  t<0 ? PlanetType.ROCKY : PlanetType.GAZEOUS;
		if (Math.abs(t)<star.getHabitability()) type = PlanetType.HABITABLE;
		if (type == PlanetType.HABITABLE){
			atmosphere = randomBetween(MIN_ATMOSPHERE, MAX_ATMOSPHERE);
			water = randomBetween(MIN_WATER, MAX_WATER);
		}
		int dust = random.nextInt(3);
		if (type == PlanetType.GAZEOUS && dust>0){
			rings = dust<2 ? Rings.THIN : Rings.LARGE;
		}
		size = randomBetweenWithFactor(MIN_SIZE, MAX_SIZE, (float)r/MAX_LOCALX); // TODO unrealistic
		energy = randomBetweenWithFactor(MIN_ENERGY, MAX_ENERGY, size/MAX_SIZE);
		satellites = (int) randomBetweenWithFactor(0, MAX_SATELLITES, size/MAX_SIZE);
		color = randomColorBetweenIntensity(
				MIN_INTENSITY + type.getValue(),
				MIN_INTENSITY + (type.getValue()+1) * (MAX_INTENSITY - MIN_INTENSITY) / 3
		);
		texture = getTexture();
		hours = randomBetween(MIN_HOURS, MAX_HOURS);
		updateXY();
	}
	
	private void updateXY(){
		x = star.x + localX;
		y = star.y + localY;
	}
	
	/**
	 * Render all planet elements (planet, orbit, atmosphere...)
	 */
	@Override
	public void render(){
		GL11.glColor3f(color[0], color[1], color[2]);
		renderOrbit();
		renderPlanet();
	}
	
	/**
	 * Render orbit
	 */
	private void renderOrbit(){
		// TODO
	}
	
	/**
	 * Render planet only
	 */
	// FIXME sometimes texture does not render ?!
	private void renderPlanet(){
		GL11.glPushMatrix();
		GL11.glTranslatef(getScreenX(), getScreenY(), size*getScreenZ());
		GL11.glRotatef(90, 1, 0, 0); // FIXME change texture orientation to avoid this
		GL11.glPushMatrix();
		GL11.glRotatef(hour, 0, 0, 1);
		Sphere sphere = new Sphere();
		sphere.setTextureFlag(true);
		texture.bind();
		sphere.draw(size*getScreenZ(), getPolygons(), getPolygons());
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	public void update(double delta){
		// TODO energy, water, atmosphere, type
		hour+= delta / hours;
		super.update(delta);
		// TODO update viewport
	}
	
	/**
	 * Texture helper
	 * @return
	 */
	private Texture getTexture(){
		String file = "assets/images/" + type.getName() + ".jpg";
		if (textures == null) textures = new HashMap<String, Texture>();
		if (textures.containsKey(file)){
			texture = textures.get(file);
		}else{
			try{
				texture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(file));
			}catch(Exception e){
				e.printStackTrace();
				System.exit(0);
			}
			textures.put(file, texture);
		}
		return texture;
	}

	/**
	 * Bode law helper
	 */
	private double BODE_A = 0.4, BODE_B = 0.15, BODE_C = 2;
	private double bode(int rank){
		return BODE_A + BODE_B * Math.pow(BODE_C, rank+1);
	}
	
	@Override
	public Camera getCamera(){
		return star.getCamera();
	}
}