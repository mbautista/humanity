package fr.ircf.humanity;

public interface Game {

	public void init();
	public void run();
	public State getState();
	// TODO public GameElement getElement(String name);
	public void setState(State state);
	public String i18n(String message);
	public Options getOptions();
	public Camera getCamera();
}
