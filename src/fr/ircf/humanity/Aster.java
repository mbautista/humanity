package fr.ircf.humanity;

import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public abstract class Aster implements SceneObject{

	protected static Random random = new Random();
	protected int x, y, size, energy;
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
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}

	public int getSize() {
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
	
	/**
	 * Random helpers
	 */
	protected int randomBetween(int min, int max){
		return min + random.nextInt(max - min);
	}
	
	protected int randomGaussian(int max){
		return (int)(max/2 * (1 + random.nextGaussian()));
	}
	
	protected int randomBetweenWithFactor(int min, int max, int factor){
		return min + (int)(random.nextInt(factor * (max - min)) / factor);
	}
	
	protected float[] randomColor(){
		return new float[] {
			(float) random.nextDouble(),
			(float) random.nextDouble(),
			(float) random.nextDouble()
		};
	}
}
