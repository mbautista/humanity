package fr.ircf.humanity;

import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public abstract class Aster implements SceneObject{

	protected static Random random = new Random();
	protected float x, y, size, energy;
	protected float[] color = new float[3];
	protected ArrayList<Bar> bars;
	protected Rectangle viewport;
	
	public void create(){
	}
	
	public void destroy(){
	}
	
	@Override
	public void render(){
	}
	
	@Override
	public void update(double delta){
	}
	
	public void serialize(){
	}
	
	public void unserialize(){
	}
	
	@Override
	public float getX() {
		return x;
	}
	
	@Override
	public float getY() {
		return y;
	}

	public float getSize() {
		return size;
	}

	@Override
	public Rectangle getViewport() {
		return viewport;
	}
	
	@Override
	public float getScreenX(){
		return getCamera().getObjectX(this);
	}
	
	@Override
	public float getScreenY(){
		return getCamera().getObjectY(this);
	}
	
	@Override
	public float getScreenZ(){
		return getCamera().getObjectZ(this);
	}
	
	public int getPolygons(){
		return getCamera().getPolygons(this);
	}
	
	/**
	 * Random helpers
	 */
	protected float randomBetween(float min, float max){
		return min + random.nextFloat() * (max - min);
	}
	
	protected float randomGaussian(float max){
		return (float) (max/2 * (1 + random.nextGaussian()));
	}
	
	protected float randomBetweenWithFactor(float min, float max, float factor){
		float f = random.nextFloat();
		return min + f + random.nextInt((int)(factor * (max - min - f))) / factor;
	}
	
	protected float[] randomColorBetweenIntensity(float min, float max){
		float[] c = new float[3];
		c[0] = random.nextFloat();
		c[1] = random.nextFloat();
		c[2] = random.nextFloat();
		float i = 3 * (min + random.nextFloat() * (max-min)) / (c[0] + c[1] + c[2]);
		c[0]*= i;
		c[1]*= i;
		c[2]*= i;
		return c;
	}
}
