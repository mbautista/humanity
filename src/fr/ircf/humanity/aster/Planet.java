package fr.ircf.humanity.aster;

import java.util.HashMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import fr.ircf.humanity.Camera;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.Random;

public class Planet extends Aster {

	public static String[] NUMBER = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
	public static double MIN_LOCALX = 2, MAX_LOCALX = 64,
			MIN_WATER = 64, MAX_WATER = 128,
			MIN_ATMOSPHERE = 64, MAX_ATMOSPHERE = 128,
			MIN_ENERGY = 64, MAX_ENERGY = 128,
			MIN_SIZE = 0.2f, MAX_SIZE = 1,
			MIN_HOURS = 10, MAX_HOURS = 30,
			MAX_SATELLITES = 20,
			ROCKY_LIMIT = 0.5,
			MIN_INTENSITY = 0.3f, MAX_INTENSITY = 0.7f,
			YEAR_SCALE = 10;
	private Star star;
	private int satellites = 0, year = 0;
	private double hours, hour = 0, days, day = 0, hoursInYear = 0;
	private enum Rings { NONE, THIN, LARGE };
	private Rings rings = Rings.NONE;
	private static HashMap<String, Texture> textures;
	private Texture texture;
	
	public Planet(Star star){
		super();
		this.star = star;
		type = AsterType.PLANET;
	}
	
	/**
	 * Randomly create a planet :
	 * generate a random planet around the star
	 * first planet distance is random
	 * other planets distance depends on bode's law and first planet distance
	 * type and size depends on distance (r)
	 * energy and satellites depends on size
	 * HABITABLE_PLANET are located in the habitable zone and have water and atmosphere
	 * GAZEOUS_PLANET may have rings
	 * TODO water should depend on atmosphere (or inversely)
	 * TODO HABITABLE_PLANET may have AI populations
	 * color intensity depends on type
	 * @param planet's rank around the star, needed for our modified BODE law
	 * @param number of planets around the star, needed for our modified BODE law
	 */
	public void create(int rank, int max){
		if (rank>0){
			distance = bode(rank) * star.getPlanet(0).getDistance();
		}else{
			distance = Random.between(star.getSize() + MIN_LOCALX, MAX_LOCALX/bode(max));
		}
		days = kepler3();
		day = Random.nextDouble() * days;
		type = (ROCKY_LIMIT - distance/MAX_LOCALX > 0) ? AsterType.ROCKY_PLANET : AsterType.GAZEOUS_PLANET;
		if (habitable()) type = AsterType.HABITABLE_PLANET;
		if (type == AsterType.HABITABLE_PLANET){
			//setResource(ATMOSPHERE, Random.between(MIN_ATMOSPHERE, MAX_ATMOSPHERE));
			addResource(ResourceType.WATER, Random.between(MIN_WATER, MAX_WATER));
		}
		int dust = Random.nextInt(3);
		if (type == AsterType.GAZEOUS_PLANET && dust>0){
			rings = dust<2 ? Rings.THIN : Rings.LARGE;
		}
		size = Random.betweenWithFactor(MIN_SIZE, MAX_SIZE, distance/MAX_LOCALX); // TODO unrealistic
		addResource(ResourceType.ENERGY, Random.betweenWithFactor(MIN_ENERGY, MAX_ENERGY, size/MAX_SIZE));
		satellites = (int) Random.betweenWithFactor(0, MAX_SATELLITES, size/MAX_SIZE);
		color = Random.colorBetweenIntensity(
				MIN_INTENSITY + type.getPlanetValue(),
				MIN_INTENSITY + (type.getPlanetValue() + 1) * (MAX_INTENSITY - MIN_INTENSITY) / 3
		);
		texture = getTexture();
		hours = Random.between(MIN_HOURS, MAX_HOURS);
		hoursInYear = hours * days;
		name = star.getName() + " " + NUMBER[rank];
		updatePosition();
		super.create();
	}
	
	/**
	 * Render all planet elements (planet, atmosphere...)
	 */
	@Override
	public void render(){
		if (star.isEnlighten()) GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glColor3f(color[0], color[1], color[2]);
		GL11.glTranslated(getScreenX(), getScreenY(),  getScreenZ());
		GL11.glPushMatrix();
		GL11.glRotated(hour, 0, 0, 1);
		Sphere sphere = new Sphere();
		sphere.setTextureFlag(true);
		texture.bind();
		sphere.draw((float)Math.max(1, getScreenSize()), getPolygons(), getPolygons());
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		if (star.isEnlighten()) GL11.glDisable(GL11.GL_LIGHTING);
		super.render();
	}
	
	@Override
	public void update(double delta){
		super.update(delta);
		// TODO energy, water, atmosphere, type
		hour+= delta;
		day = hour / hours;
		year = (int)(YEAR_SCALE * day / days);
		updatePosition();
	}
	
	private void updatePosition(){
		x = star.getX() + distance * Math.cos(day * 2 * Math.PI / days);
		y = star.getY() + distance * Math.sin(day * 2 * Math.PI / days);
		updateViewport();
	}
	
	protected void over(){
		// TODO slow time
		super.over();
	}
	
	protected void out(){
		// TODO resume time
		super.out();
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
	 * Bode's law for planet distance
	 */
	private double BODE_A = 0.4, BODE_B = 0.15, BODE_C = 2;
	private double bode(int rank){
		return BODE_A + BODE_B * Math.pow(BODE_C, rank+2);
	}
	
	/**
	 * Kepler's 3rd law for planet orbit period
	 * KEPLER_A is the time scale factor
	 */
	private double KEPLER_A = 5, KEPLER_B = 1.5;
	private double kepler3(){
		return KEPLER_A * Math.pow(distance, KEPLER_B);
	}
	
	/**
	 * Habitable law
	 * @see http://www.emerginginvestigators.org/2013/05/determining-the-habitable-zone-around-a-star/
	 * HABITABLE_A and _B is approx bode's number
	 */
	private double HABITABLE_A = 1, HABITABLE_B = 3, HABITABLE_C = 1.75;
	private boolean habitable(){
		double scale = Math.pow(star.getSize(), HABITABLE_C);
		return distance > HABITABLE_A * scale && distance < HABITABLE_B * scale;
	}
	
	@Override
	public Camera getCamera(){
		return star.getCamera();
	}
	
	public double getDistance() {
		return distance;
	}

	public Star getStar() {
		return star;
	}
	
	public int getYear(){
		return year;
	}
	
	public double getHoursInYear(){
		return hoursInYear;
	}
	
	@Override
	public Game getGame(){
		return star.getGame();
	}
}