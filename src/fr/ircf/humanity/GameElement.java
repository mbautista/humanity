package fr.ircf.humanity;

public interface GameElement {
	public void init() throws Exception;
	public boolean visible(State state);
	public void render();
	public void update(double delta);
}
