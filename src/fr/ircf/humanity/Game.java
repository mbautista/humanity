package fr.ircf.humanity;

import org.lwjgl.LWJGLException;

import fr.ircf.humanity.game.Audio;
import fr.ircf.humanity.game.Camera;
import fr.ircf.humanity.game.Log;
import fr.ircf.humanity.game.Options;
import fr.ircf.humanity.game.Player;

public interface Game {

	public void init();
	public void initDisplay() throws LWJGLException;
	public void initLocale();
	public void run();
	public State getState();
	// TODO public GameElement getElement(int id);
	public void setState(State state);
	public String i18n(String message);
	public Options getOptions();
	public Camera getCamera();
	public Audio getAudio();
	public Scene getScene();
	public Player getPlayer();
	public State getPreviousState();
	public Log getLog();
}
