package fr.ircf.humanity;

import java.awt.Rectangle;

public class Camera {

	private float x, y, z;
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
		int r = (int)(Scene.SIZE/Math.pow(2, z));
		viewport.setBounds((int)x-r, (int)y-r, 2*r, 2*r);
		scene.updateSceneObjects();
	}
	
	public boolean shows(SceneObject o){
		 return viewport.contains(o.getX(), o.getY());
	}
	
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
