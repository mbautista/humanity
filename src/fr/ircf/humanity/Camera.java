package fr.ircf.humanity;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

public class Camera {

	private float x, y, z, scale;
	public static float Z_MIN = 0, Z_MAX = 10, DZ = 0.1f;
	private Rectangle viewport;
	private Scene scene;
	
	public Camera(Scene scene){
		this.scene = scene;
		viewport = new Rectangle();
	}
	
	/**
	 * Points camera to a specific scene object
	 * @param o
	 */
	public void show(SceneObject o){
		show(o, z);
	}
	
	public void show(SceneObject o, float z){
		this.x = o.getX();
		this.y = o.getY();
		this.z = z;
		updateViewport();
	}
	
	/**
	 * Updates camera viewport and scene objects
	 */
	public void updateViewport(){
		int rx = (int)(scene.getSize()/Math.pow(2, z-1));
		int ry = (int)(rx * Display.getHeight()/Display.getWidth());
		viewport.setBounds((int)x-rx, (int)y-ry, 2*rx, 2*ry);
		scale = (float)(Display.getWidth() / viewport.getWidth());
		scene.updateSceneObjects();
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
		z = Math.min(Z_MAX, z + DZ);
		updateViewport();
	}
	
	public void zoomOut(){
		z = Math.max(Z_MIN, z - DZ);
		updateViewport();
	}
	
	/**
	 * Get object attributes for render on screen
	 */
	public float getObjectX(SceneObject o){
		return (float) ((o.getX() - viewport.getX()) * scale);
	}
	
	public float getObjectY(SceneObject o){
		return (float) ((o.getY() - viewport.getY()) * scale);
	}

	public float getObjectZ(SceneObject o){
		return scale;
	}
	
	public int getPolygons(SceneObject o){
		return Math.max(4, (int)(4*z));
	}
	
	// DEPRECATED : use getObjectZ instead
	/*public int getObjectSize(SceneObject o){
		return (int) (o.getSize() * scale);
	}*/
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
		updateViewport();
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
}
