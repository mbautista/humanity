package fr.ircf.humanity;

import java.util.ArrayList;
import java.util.Random;

public abstract class Aster implements SceneObject{

	protected static Random random = new Random();
	protected int x, y, size, energy;
	protected ArrayList<Bar> bars;
	
	public void create(){
	}
	
	public void destroy(){
	}
	
	public void render(){
	}
	
	public void serialize(){
	}
	
	public void unserialize(){
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
