package fr.ircf.humanity;

import java.awt.Rectangle;

public interface SceneObject {

	public double getX();
	public double getY();
	public double getScreenX();
	public double getScreenY();
	public double getScreenZ();
	public double getSize();
	public Rectangle getViewport();
	public Camera getCamera();
	public void render();
	public void update(double delta);
}
