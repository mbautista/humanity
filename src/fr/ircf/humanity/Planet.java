package fr.ircf.humanity;

import java.util.ArrayList;

public class Planet extends Aster {

	public static int MAX_LOCALX = 16,
			MIN_FOOD = 512, MAX_FOOD = 1024,
			MIN_ENERGY = 128, MAX_ENERGY = 512,
			MIN_SIZE = 1, MAX_SIZE = 4,
			MAX_SATELLITES = 20;
	private Star star;
	private int localX, localY, food;
	private ArrayList<Population> populations;
	private enum Type { ROCKY, HABITABLE, GAZEOUS };
	private Type type;
	private enum Rings { NONE, THIN, LARGE };
	private Rings rings;
	private int satellites;
	
	public Planet(Star star){
		this.star = star;
	}
	
	/**
	 * Randomly create a planet :
	 * generate a random planet around the star, with gaussian repartition at MAX_LOCALX
	 * type and size depends on star distance (r)
	 * energy and satellites depends on size
	 * HABITABLE are located in the habitable zone and have food
	 * GAZEOUS may have rings
	 * TODO only HABITABLE have populations/AI
	 */
	public void create(){
		double r = (int)(MAX_LOCALX * (1 + random.nextGaussian()));
		double a = random.nextDouble() * Math.PI;
		localX = (int)(MAX_LOCALX + r * Math.cos(a));
		localY = (int)(MAX_LOCALX + r * Math.sin(a));
		double t = r/MAX_LOCALX - 0.5;
		type =  t<0 ? Type.ROCKY : Type.GAZEOUS;
		if (Math.abs(t)<star.getHabitability()) type = Type.HABITABLE;
		if (type == Type.HABITABLE) food = MIN_FOOD + random.nextInt(MAX_FOOD - MIN_FOOD);
		int dust = random.nextInt(3);
		if (type == Type.GAZEOUS && dust>0){
			rings = dust<2 ? Rings.THIN : Rings.LARGE;
		}else{
			rings = Rings.NONE;
		}
		size = MIN_SIZE + random.nextInt((int)r*(MAX_SIZE - MIN_SIZE))/(int)r;
		energy = MIN_ENERGY + random.nextInt(size*(MAX_ENERGY - MIN_ENERGY))/size;
		satellites = random.nextInt(size) * MAX_SATELLITES;
		updateXY();
	}
	
	private void updateXY(){
		x = star.x + localX;
		y = star.y + localY;
	}
	
	public void render(){
		// TODO
	}
	
	public void update(double delta){
		// TODO
	}
}