package fr.ircf.humanity;

import java.awt.geom.Rectangle2D;

import fr.ircf.humanity.game.Camera;

public interface SceneObject {

	public String getName();
	public double getX();
	public double getY();
	public double getScreenX();
	public double getScreenY();
	public double getScreenZ();
	public double getScreenSize();
	public double getSize();
	public Rectangle2D getViewport();
	public Rectangle2D getExtendedViewport();
	public Camera getCamera();
	public void render();
	public void update(double delta);
	public Rectangle2D getScreenViewport();
}
