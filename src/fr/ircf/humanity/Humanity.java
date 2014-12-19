package fr.ircf.humanity;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class Humanity {

	private Title title;
	private Menu menu;
	private Options options;
	private Galaxy galaxy;
	private Zoom zoom;
	private Map map;
	private PlayerInfo playerInfo;
	private Log log;
	private Actions actions;
	private Audio audio;
	private Loader loader;
	static final String NAME = "Humanity";
	static final double VERSION = 0.1;

	public void init(){
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setVSyncEnabled(true);
			Display.setTitle(NAME);
			Display.create();
			GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
			GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		}catch( LWJGLException e ){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(!Display.isCloseRequested()){
			view();
			controller();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public void view(){
		// TODO
	}
	
	public void controller(){
		// TODO
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Humanity h = new Humanity();
		h.init();
		h.run();
	}
}