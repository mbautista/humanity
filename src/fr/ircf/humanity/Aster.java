package fr.ircf.humanity;

import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public abstract class Aster implements SceneObject{

	protected static Random random = new Random();
	protected double x, y, size, energy;
	protected float[] color = new float[3];
	protected ArrayList<Bar> bars;
	protected Rectangle viewport;
	protected boolean mousedown = false;
	
	public void create(){
	}
	
	public void destroy(){
	}
	
	@Override
	public void render(){
	}
	
	@Override
	public void update(double delta){
		if (viewport!=null && viewport.contains(Mouse.getX(), Display.getHeight()-Mouse.getY())){
			over();
			if (Mouse.isButtonDown(0)) click();
		}
	}
	
	protected void over(){
		// TODO enlighten
	}
	
	protected void click(){
		// FIXME Concurrent Modification Exception
		// TODO prevent this if camera movement is in progress
		//System.out.println("click aster : " + this.getClass().getName());
		//getCamera().show(this);
	}
	
	public void serialize(){
	}
	
	public void unserialize(){
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}

	public double getSize() {
		return size;
	}

	@Override
	public Rectangle getViewport() {
		return viewport;
	}
	
	@Override
	public double getScreenX(){
		return getCamera().getObjectX(this);
	}
	
	@Override
	public double getScreenY(){
		return getCamera().getObjectY(this);
	}
	
	@Override
	public double getScreenZ(){
		return getCamera().getObjectZ(this);
	}
	
	public int getPolygons(){
		return getCamera().getPolygons(this);
	}
	
	/**
	 * Random helpers
	 */
	protected double randomBetween(double min, double max){
		return min + random.nextDouble() * (max - min);
	}
	
	protected double randomGaussian(double max){
		return (float) (max/2 * (1 + random.nextGaussian()));
	}
	
	protected double randomBetweenWithFactor(double min, double max, double factor){
		return min + random.nextDouble() * (max-min) * factor;
	}
	
	protected float[] randomColorBetweenIntensity(double min, double max){
		float[] c = new float[3];
		c[0] = random.nextFloat();
		c[1] = random.nextFloat();
		c[2] = random.nextFloat();
		float i = (float) (3 * (min + random.nextDouble() * (max-min)) / (c[0] + c[1] + c[2]));
		c[0]*= i;
		c[1]*= i;
		c[2]*= i;
		return c;
	}
}