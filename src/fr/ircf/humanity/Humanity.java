package fr.ircf.humanity;

import java.util.ResourceBundle;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import fr.ircf.humanity.aster.AsterMenu;

public class Humanity implements Game{

	static final String TITLE = "Humanity";
	static final double VERSION = 0.1, SPEED_SCALE = 500;
	private GameElement[] gameElements;	
	private State state = State.MENU, previousState = State.MENU;
	private long lastFrame;
	private ResourceBundle messages;
	private Options options;
	private Camera camera;
	private Galaxy galaxy;
	private Loader loader;
	private Player player;
	private Audio audio;
	private Log log;

	public Humanity(){
		options = new Options();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Humanity h = new Humanity();
		h.init();
		h.run();
	}
	
    @Override
	public void init(){
		try{
			initLocale();
			initDisplay();
			initGl();
			initGameElements();
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void initLocale(){
		messages = ResourceBundle.getBundle(TITLE, options.getLocale());
	}
    
	public void initDisplay() throws LWJGLException {
		Display.setDisplayMode(options.getDisplayMode());
		Display.setFullscreen(options.isFullScreen());
		if (Display.isCreated()) return;
		Display.setVSyncEnabled(true);
		Display.setTitle(TITLE);
		Display.create();
	}
	
	private void initGl(){
		GL11.glClearDepth(1);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, -2000, 0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void initGameElements() throws Exception{
		lastFrame = getTime();
		galaxy = new Galaxy();
		camera = new Camera();
		loader = new Loader();
		player = new Human();
		audio = new Audio();
		log = new Log();
		// TODO use HashMap<String, GameElement> instead of [] so we can get any game element by its name
		gameElements = new GameElement[] {
			audio,
			galaxy,
			camera,
			new Title(),
			new Menu(),
			options,
			new Zoom(),
			//new Map(),
			player,
			log,
			new AsterMenu(),
			loader,
		};
		for(GameElement gameElement : gameElements){
			gameElement.init(this);
		}
		// set and show player planet
		camera.showWithZMax(player.getPlanet());
		// welcome message
		log.add(new Event(i18n("event.welcome")));
	}
	
    @Override
	public void run(){
		while(!Display.isCloseRequested() && state!=State.QUIT){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			render();
			update(getDelta());
			Display.sync(40);
			Display.update();
		}
		Display.destroy();
	}
    
    private void render(){
		for(GameElement gameElement : gameElements){
			if (gameElement.visible()){
				gameElement.render();
			}
		}
    }
    
    private void update(double delta){
    	switch(state){
	    	case NEW :
	    		state = State.LOAD;
	    		loader.setMax(options.getGalaxySize());
	    		galaxy.create(options.getGalaxySize());
	    		break;
	    	case LOAD :
	    		loader.setValue(galaxy.getStarSize());
	    		break;
	    	case GAME :
	    		break;
    	}
		for(GameElement gameElement : gameElements){
			if (gameElement.visible()){
				gameElement.update(delta);
			}
		}
    }
    
    /**
     * Time helpers
     */
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private double getDelta() {
		long currentTime = getTime();
		double delta = ((double) currentTime - (double) lastFrame) * options.getSpeed()/SPEED_SCALE;
		lastFrame = getTime();
		return delta;
	}
    
	@Override
	public Options getOptions() {
		return options;
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
		previousState = this.state;
		this.state = state;
	}

	@Override
	public Camera getCamera() {
		return camera;
	}
	
	@Override
	public Scene getScene(){
		return galaxy;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public State getPreviousState() {
		return previousState;
	}
	
	public Galaxy getGalaxy() {
		return galaxy;
	}
	
	public Log getLog(){
		return log;
	}
}