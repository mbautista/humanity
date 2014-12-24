package fr.ircf.humanity;

import java.awt.geom.Rectangle2D;

import org.lwjgl.opengl.Display;

public class Camera {

	public static double Z_MIN = 0, Z_MAX = 12, DZ = 0.1f;
	private double x, y, z, scale;
	private Rectangle2D viewport;
	private Scene scene;
	private static boolean locked = false;
	private SceneObject object;
	
	public Camera(Scene scene){
		this.scene = scene;
		viewport = new Rectangle2D.Double();
	}
	
	/**
	 * Points camera to a specific scene object
	 * @param o
	 */
	public void show(SceneObject o){
		show(o, z);
	}
	
	public void showWithZMax(SceneObject o){
		show(o, getObjectZMax(o));
	}
	
	public void show(SceneObject o, double z){
		object = o;
		this.x = o.getX();
		this.y = o.getY();
		this.z = z;
		updateViewport();
	}
	
	/**
	 * Updates camera viewport and scene objects
	 */
	public void updateViewport(){
		if (isLocked()) return;
		setLocked(true);
		double rx = (scene.getSize()/Math.pow(2, z-1));
		double ry = (rx * Display.getHeight()/Display.getWidth());
		viewport.setFrameFromCenter(x, y, x+rx, y+ry);
		scale = (float)(Display.getWidth() / viewport.getWidth());
		scene.updateSceneObjects();
		setLocked(false);
	}
	
	/**
	 * @param SceneObject
	 * @return TRUE if camera shows this scene object, else FALSE
	 */
	public boolean shows(SceneObject o){
		 return viewport.contains(o.getX(), o.getY());
	}
	
	/**
	 * @param SceneObject
	 * @return TRUE if camera shows this scene object viewport, else FALSE (e.g. star system)
	 */
	public boolean showsViewport(SceneObject o){
		 return viewport.intersects(o.getViewport());
	}
	
	/**
	 * Zoom helpers
	 */
	public void zoomIn(){
		double zMax = object != null ? getObjectZMax(object) : Z_MAX;
		z = Math.min(zMax, z + DZ);
		updateViewport();
	}
	
	public void zoomOut(){
		z = Math.max(Z_MIN, z - DZ);
		updateViewport();
	}
	
	/**
	 * Get object attributes for render on screen
	 */
	public double getObjectX(SceneObject o){
		return ((o.getX() - viewport.getX()) * scale);
	}
	
	public double getObjectY(SceneObject o){
		return ((o.getY() - viewport.getY()) * scale);
	}

	public double getObjectZ(SceneObject o){
		return scale;
	}
	
	/**
	 * Computes the maximum zoom for object, so that it can fit on screen
	 * @param o
	 * @return
	 */
	public double getObjectZMax(SceneObject o){
		return Math.log(scene.getSize()/o.getSize()*Display.getHeight()/Display.getWidth())/Math.log(2) + 2;
	}
	
	public int getPolygons(SceneObject o){
		return Math.max(3, (int)(4*z));
	}
	
	// DEPRECATED : use getObjectZ instead
	/*public int getObjectSize(SceneObject o){
		return (int) (o.getSize() * scale);
	}*/
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
		updateViewport();
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
