package fr.ircf.humanity;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public abstract class Player extends Panel implements GameElement{

	private static int X = 10, DY = 20;
	private Game game;
	private double humanity = 0, kardashev = 0;
	private Text[] texts;
	// TODO avatar
	// TODO color 
	private Planet planet;
	private ArrayList<Population> populations;

	@Override
	// TODO dynamic text
	public void init(Game game) throws Exception {
		this.game = game;
		texts = new Text[] { new Text(), new Text(), new Text() };
		int i = 0;
		for (Text text: texts){
			text.setPosition(X, (Display.getHeight() - getHeight()) + i * DY);
			i++;
		}
	}

	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}

	@Override
	public void render() {
		// TODO avatar
		texts[0].setText(game.i18n("player.year") + " : " + planet.getYear());
		texts[1].setText(game.i18n("player.humanity") + " : " + humanity);
		texts[2].setText(game.i18n("player.kardashev") + " : " + kardashev);
		for (Text text: texts){
			text.render();
		}
	}

	@Override
	public void update(double delta) {
		// TODO kardashev
		// TODO population
	}
	
	public int getHeight(){
		return texts.length * (texts[0].getHeight() + DY);
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
}