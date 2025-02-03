package fr.ircf.humanity.component;

import java.awt.Rectangle;

public abstract class Component {

	protected int x, y, width = 0, height = 0;
	protected Rectangle viewport;
	
	public boolean visible(){
		return true;
	}
	
	abstract public void render();
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(double x, double y){
		this.x = (int)x;
		this.y = (int)y;
	}
	
	public void update(double delta){	
	}
	
	public void updateViewport(){
		viewport = new Rectangle(x, y, getWidth(), getHeight());
	}

	public Rectangle getViewport() {
		return viewport;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
