package fr.ircf.humanity;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Planet extends Aster {

	public static int MAX_LOCALX = 16,
			MIN_WATER = 64, MAX_WATER = 128,
			MIN_ATMOSPHERE = 64, MAX_ATMOSPHERE = 128,
			MIN_ENERGY = 64, MAX_ENERGY = 128,
			MIN_SIZE = 1, MAX_SIZE = 4,
			MAX_SATELLITES = 20;
	private Star star;
	private int localX, localY, water = 0, atmosphere = 0, satellites = 0;
	private ArrayList<Population> populations;
	private enum Type { ROCKY, HABITABLE, GAZEOUS };
	private Type type = Type.ROCKY;
	private enum Rings { NONE, THIN, LARGE };
	private Rings rings = Rings.NONE;
	
	public Planet(Star star){
		this.star = star;
	}
	
	/**
	 * Randomly create a planet :
	 * generate a random planet around the star, with gaussian repartition at MAX_LOCALX
	 * type and size depends on star distance (r)
	 * energy and satellites depends on size
	 * HABITABLE are located in the habitable zone and have water and atmosphere
	 * GAZEOUS may have rings
	 * TODO water should depend on atmosphere (or inversely)
	 * TODO HABITABLE may have AI populations
	 * TODO random color (ground material)
	 */
	@Override
	public void create(){
		double r = randomBetween(star.getSize() + 1, MAX_LOCALX);
		// FIXME double r = star.getSize() + 1 + randomGaussian(MAX_LOCALX - star.getSize() - 1);
		double a = random.nextDouble() * Math.PI;
		localX = (int)(MAX_LOCALX + r * Math.cos(a));
		localY = (int)(MAX_LOCALX + r * Math.sin(a));
		double t = r/MAX_LOCALX - 0.5;
		type =  t<0 ? Type.ROCKY : Type.GAZEOUS;
		if (Math.abs(t)<star.getHabitability()) type = Type.HABITABLE;
		if (type == Type.HABITABLE){
			atmosphere = randomBetween(MIN_ATMOSPHERE, MAX_ATMOSPHERE);
			water = randomBetween(MIN_WATER, MAX_WATER);
		}
		int dust = random.nextInt(3);
		if (type == Type.GAZEOUS && dust>0){
			rings = dust<2 ? Rings.THIN : Rings.LARGE;
		}
		size = randomBetweenWithFactor(MIN_SIZE, MAX_SIZE, (int)r); // TODO unrealistic
		energy = randomBetweenWithFactor(MIN_ENERGY, MAX_ENERGY, size);
		satellites = randomBetweenWithFactor(0, MAX_SATELLITES, size);
		color = randomColor();
		updateXY();
	}
	
	private void updateXY(){
		x = star.x + localX;
		y = star.y + localY;
	}
	
	@Override
	public void render(){
		GL11.glTranslatef(getScreenX(), getScreenY(), getScreenZ());
		// TODO GL11.glColor3f(color[0], color[1], color[2]);
		Sphere sphere = new Sphere();
		//sphere.setDrawStyle(GLU.GLU_FILL);
		//sphere.setTextureFlag(true);
		//sphere.setNormals(GLU.GLU_SMOOTH);
		//try { getTexture().bind(); }catch(Exception e){}
		sphere.draw(size, 16, 16);
	}
	
	@Override
	public void update(double delta){
		// TODO energy, water, atmosphere, type
	}
	
	@Override
	public Camera getCamera(){
		return star.getCamera();
	}
	
	private Texture getTexture() throws IOException{
		// TODO cache
		String file = null;
		switch(type){
			case ROCKY : 		file = "assets/images/rocky.jpg"; break;
			case HABITABLE : 	file = "assets/images/habitable.jpg"; break;
			case GAZEOUS : 		file = "assets/images/gazeous.jpg"; break;
		}
		return TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(file));
	}
}