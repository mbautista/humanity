package fr.ircf.humanity;

import java.util.ResourceBundle;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

public class Humanity implements Game{

	static final String TITLE = "Humanity";
	static final double VERSION = 0.1;
	private GameElement[] gameElements;	
	private State state = State.MENU;
	private long lastFrame;
	private Options options;
    private ResourceBundle messages;

    @Override
	public void init(){
		try{
			initDisplay();
			initGl();
			initGameElements();
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void initDisplay() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(640, 480));
		//Display.setFullscreen(true);
		Display.setVSyncEnabled(true);
		Display.setTitle(TITLE);
		Display.create();
	}
	
	private void initGl(){
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void initGameElements() throws Exception{
		lastFrame = getTime();
		gameElements = new GameElement[] {
			new Title(),
			new Menu(),
			options = new Options(),
			//new Galaxy(),
			//new Zoom(),
			//new Map(),
			//new PlayerInfo(),
			//new Log(),
			//new Actions(),
			//new Audio(),
			//new Loader(),
		};
		messages = ResourceBundle.getBundle(TITLE, options.getLocale());
		for(GameElement gameElement : gameElements){
			gameElement.init(this);
		}
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private double getDelta() {
		long currentTime = getTime();
		double delta = (double) currentTime - (double) lastFrame;
		lastFrame = getTime();
		return delta;
	}
	
    @Override
	public void run(){
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			double delta = getDelta();
			for(GameElement gameElement : gameElements){
				if (gameElement.visible()){
					gameElement.render();
					gameElement.update(delta);
				}
			}
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	@Override
	public String i18n(String message){
		return messages.getString(message);
	}
	
	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setState(State state) {
		this.state = state;
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