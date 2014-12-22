package fr.ircf.humanity;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

public class Camera {

	private float x, y, z, scale;
	public static int Z_MIN = 1, Z_MAX = 7;
	private Rectangle viewport;
	private Scene scene;
	
	public Camera(Scene scene){
		this.scene = scene;
		viewport = new Rectangle();
	}
	
	public void show(SceneObject o){
		this.x = o.getX();
		this.y = o.getY();
		this.z = Z_MAX;
		updateViewport();
	}
	
	public void updateViewport(){
		int r = (int)(scene.getSize()/Math.pow(2, z));
		viewport.setBounds((int)x-r, (int)y-r, 2*r, 2*r);
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
