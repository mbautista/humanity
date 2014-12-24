package fr.ircf.humanity;

import java.awt.geom.Rectangle2D;

import org.lwjgl.opengl.Display;

public class Camera implements GameElement {

	public static double Z_MIN = 0, Z_MAX = 12, DZ = 0.1f;
	private double x, y, z, scale;
	private Rectangle2D viewport;
	private SceneObject object;
	private Game game;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		viewport = new Rectangle2D.Double();
	}

	@Override
	public boolean visible() {
		return true;
	}

	@Override
	public void render() {
		// TODO translate camera (instead of translating every object !)
	}

	@Override
	public void update(double delta) {
		if (object!=null) show(object); 
		double rx = game.getScene().getSize()/Math.pow(2, z-1);
		double ry = rx * Display.getHeight()/(double)Display.getWidth();
		viewport.setFrameFromCenter(x, y, x+rx, y+ry);
		scale = Display.getWidth() / viewport.getWidth();
		game.getScene().updateSceneObjects();
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
	}
	
	public void zoomOut(){
		z = Math.max(Z_MIN, z - DZ);
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
		return Math.log(game.getScene().getSize()/o.getSize()*Display.getHeight()/Display.getWidth())/Math.log(2) + 2;
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
}
