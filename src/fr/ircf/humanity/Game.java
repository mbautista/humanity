package fr.ircf.humanity;

import org.lwjgl.LWJGLException;

public interface Game {

	public void init();
	public void initDisplay() throws LWJGLException;
	public void initLocale();
	public void run();
	public State getState();
	// TODO public GameElement getElement(String name);
	public void setState(State state);
	public String i18n(String message);
	public Options getOptions();
	public Camera getCamera();
	public Audio getAudio();
	public Scene getScene();
	public Player getPlayer();
	public State getPreviousState();
}
