package fr.ircf.humanity;

public interface GameElement {
	public void init(Game game) throws Exception;
	public boolean visible();
	public void render();
	public void update(double delta);
}
