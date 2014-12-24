package fr.ircf.humanity;

import java.util.ResourceBundle;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

public class Humanity implements Game{

	static final String TITLE = "Humanity";
	static final double VERSION = 0.1;
	private GameElement[] gameElements;	
	private State state = State.MENU;
	private long lastFrame;
	private Options options;
	private Camera camera;
	private ResourceBundle messages;
	private Galaxy galaxy;
	private Loader loader;
	private Player player;

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
			initDisplay();
			initGl();
			initGameElements();
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void initDisplay() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(800, 600));
		//Display.setFullscreen(true);
		Display.setVSyncEnabled(true);
		Display.setTitle(TITLE);
		Display.create();
	}
	
	private void initGl(){
		GL11.glClearDepth(1);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, -100, 100);
		//float aspect = Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight();
	    //GLU.gluPerspective(45f, aspect, 0.1f, 100.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void initGameElements() throws Exception{
		lastFrame = getTime();
		options = new Options();
		messages = ResourceBundle.getBundle(TITLE, options.getLocale());
		galaxy = new Galaxy();
		camera = new Camera();
		loader = new Loader();
		player = new Human();
		// TODO use HashMap<String, GameElement> instead of [] so we can get any game element by its name
		gameElements = new GameElement[] {
			galaxy,
			camera,
			new Title(),
			new Menu(),
			options,
			new Zoom(),
			//new Map(),
			player,
			//new Log(),
			//new Actions(),
			//new Audio(),
			loader,
		};
		for(GameElement gameElement : gameElements){
			gameElement.init(this);
		}
		camera.showWithZMax(galaxy.getRandomStar().getRandomPlanet()); // TODO set & show player planet
	}
	
    @Override
	public void run(){
		while(!Display.isCloseRequested() && state!=State.QUIT){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			render();
			update(getDelta());
			Display.sync(60);
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
	    		//System.out.println("new game");
	    		state = State.LOAD;
	    		loader.setMax(options.getGalaxySize());
	    		galaxy.create(options.getGalaxySize());
	    		break;
	    	case LOAD :
	    		//System.out.println("loading...");
	    		loader.setValue(galaxy.getStarSize());
	    		break;
	    	case GAME :
	    		//System.out.println("game started");
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
		double delta = ((double) currentTime - (double) lastFrame) * options.getSpeed()/10;
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
}