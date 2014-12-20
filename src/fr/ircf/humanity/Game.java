package fr.ircf.humanity;

public interface Game {

	public void init();
	public void run();
	public State getState();
	public void setState(State state);
	public String i18n(String message);
}
