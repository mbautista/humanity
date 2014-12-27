package fr.ircf.humanity;

import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public abstract class Aster implements SceneObject{

	public static int
		ENERGY = 0,
		FOOD = 1,
		WATER = 2,
		PEOPLE = 3,
		ATMOSPHERE = 4,
		OVER = 5;
	public static float[][] COLORS = {
		new float[] { 1, 1, 0 },
		new float[] { 0, 1, 0 },
		new float[] { 0, 0, 1 },
		new float[] { 1, 0, 0 },
		new float[] { 0, 1, 1 },
		new float[] { 1, 0, 0 }
	};
	public static double
		TEXT_DY = 11, // Spacing between planet and text
		MIN_Z_FOR_TEXT = 4, // do not display texts at galaxy scale
		MAX_RESOURCE = 128,
		MIN_SCREEN_VIEWPORT_X = 10; // Avoid to display/interact viewports below that size
	protected static Random random = new Random();
	protected String name;
	protected double x = 0, y = 0, size, distance;
	protected float[] color = new float[3];
	protected HashMap<Integer, Double> resources;
	// Aster viewport represents the aster's renderable/interactive zone (e.g. star)
	// Aster extended viewport includes children asters (e.g. star system)
	protected Rectangle2D viewport, extendedViewport, screenViewport;
	protected Text text;
	protected HashMap<Integer, TextBar> bars;
	protected boolean highlight = false;
	
	public Aster(){
		resources = new HashMap<Integer, Double>();
	}
	
	public void create(){
		text = new Text(name);
		bars = new HashMap<Integer, TextBar>();
		for(Entry<Integer, Double> resource : resources.entrySet()){
			bars.put(resource.getKey(), new TextBar(
					getGame().i18n("resources." + resource.getKey()), 
					COLORS[resource.getKey()]
			));
			bars.get(resource.getKey()).setValue(resource.getValue());
			bars.get(resource.getKey()).setMax(MAX_RESOURCE);
		}
	}
	
	public void destroy(){
	}
	
	@Override
	public void render(){
		if (getCamera().hasZMax()) return;
		renderViewport();
		if (getCamera().getZ()>MIN_Z_FOR_TEXT) renderText();
		if (highlight) renderBars();
	}
	
	private void renderText(){
		text.setPosition(getScreenX(), getScreenY() - this.getScreenSize() - (bars.size()+1) * TEXT_DY);
		text.render();
	}
	
	private void renderBars(){
		int i = 0;
		for(Entry<Integer, TextBar> bar : bars.entrySet()){
			bar.getValue().setPosition(getScreenX(), getScreenY() - this.getScreenSize() - (bars.size()-i) * TEXT_DY);
			bar.getValue().render();
			i++;
		}
	}
	
	private void renderViewport(){
		if (screenViewport!=null && highlight){
			GL11.glColor3f(COLORS[OVER][0], COLORS[OVER][1], COLORS[OVER][2]);
			GL11.glBegin(GL11.GL_LINE_STRIP);
		    	GL11.glVertex2d(screenViewport.getX(), screenViewport.getY());
		    	GL11.glVertex2d(screenViewport.getMaxX(), screenViewport.getY());
		    	GL11.glVertex2d(screenViewport.getMaxX(), screenViewport.getMaxY());
		    	GL11.glVertex2d(screenViewport.getX(), screenViewport.getMaxY());
		    	GL11.glVertex2d(screenViewport.getX(), screenViewport.getY());
		    GL11.glEnd();
		}
	}
	
	@Override
	public void update(double delta){
		if (getCamera().hasZMax()) return;
		screenViewport = getScreenViewport();
		if (extendedViewport!=null && screenViewport.getWidth() < MIN_SCREEN_VIEWPORT_X) screenViewport = getScreenExtendedViewport();
		if (screenViewport!=null && screenViewport.contains(Mouse.getX(), Display.getHeight() - Mouse.getY())){
			over();
			if (Mouse.isButtonDown(0)) down();
			while (Mouse.next()){
			    if (!Mouse.getEventButtonState() && Mouse.getEventButton() == 0) up();
			}
		}else{
			out();
		}
	}
	
	/**
	 * Update aster viewport from x, y and size
	 */
	protected void updateViewport(){
		viewport = new Rectangle2D.Double(
			x - size,
			y - size,
			2 * size,
			2 * size
		);
	}
	
	protected void over(){
		getCamera().setSlowMotion(this, true);
		highlight = true;
	}
	
	protected void out(){
		getCamera().setSlowMotion(this, false);
		highlight = false;
	}
	
	protected void down(){
	}
	
	protected void up(){
		getCamera().show(this);
	}
	
	public void serialize(){
	}
	
	public void unserialize(){
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getSize() {
		return size;
	}

	@Override
	public Rectangle2D getViewport() {
		return viewport;
	}
	
	@Override
	public Rectangle2D getExtendedViewport() {
		return extendedViewport;
	}
	
	@Override
	public double getScreenX(){
		return getCamera().getObjectX(this);
	}
	
	@Override
	public double getScreenY(){
		return getCamera().getObjectY(this);
	}
	
	@Override
	public double getScreenZ(){
		return getCamera().getObjectZ(this);
	}
	
	@Override
	public double getScreenSize(){
		return size * getScreenZ();
	}
	
	@Override
	public Rectangle2D getScreenViewport(){
		return getCamera().getObjectViewport(this);
	}
	
	public Rectangle2D getScreenExtendedViewport(){
		return getCamera().getObjectExtendedViewport(this);
	}
	
	public int getPolygons(){
		return getCamera().getPolygons(this);
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Random helpers
	 */
	protected double randomBetween(double min, double max){
		return min + random.nextDouble() * (max - min);
	}
	
	protected double randomGaussian(double max){
		return max * random.nextGaussian();
	}
	
	protected double randomBetweenWithFactor(double min, double max, double factor){
		return min + random.nextDouble() * (max-min) * factor;
	}
	
	protected float[] randomColorBetweenIntensity(double min, double max){
		float[] c = new float[3];
		c[0] = random.nextFloat();
		c[1] = random.nextFloat();
		c[2] = random.nextFloat();
		float i = (float) (3 * (min + random.nextDouble() * (max-min)) / (c[0] + c[1] + c[2]));
		c[0]*= i;
		c[1]*= i;
		c[2]*= i;
		return c;
	}
	
	private static int MIN_SYLLABUS = 1, MAX_SYLLABUS = 3;
	private static String VOWELS = "aeiouy";
	private static String CONSONANTS = "bcdfghjklmnpqrstvwxz";
	protected String randomName(){
		int length = MIN_SYLLABUS + random.nextInt(MAX_SYLLABUS);
		String name = "";
		for (int i=0; i<length; i++) name+= randomSyllabus();
		return name;
	}
	
	private String randomSyllabus(){
		String syllabus = "" + randomChar();
		syllabus += randomChar(!VOWELS.contains(syllabus));
		return syllabus;
	}

	private char randomChar(){
		return (char)(97 + random.nextInt(26));
	}
	
	private char randomChar(boolean vowel){
		if (vowel){
			return VOWELS.charAt(random.nextInt(VOWELS.length()));
		}else{
			return CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
		}
	}
	
	public abstract Game getGame();
}