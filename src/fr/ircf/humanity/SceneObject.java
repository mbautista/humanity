package fr.ircf.humanity;

import java.awt.Rectangle;

public interface SceneObject {

	public int getX();
	public int getY();
	public float getScreenX();
	public float getScreenY();
	public float getScreenZ();
	public Rectangle getViewport();
	public Camera getCamera();
	public void render();
	public void update(double delta);
}
