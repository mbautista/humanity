package fr.ircf.humanity;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public abstract class Player extends Panel implements GameElement{

	private static int X = 10, DY = 20;
	private Game game;
	private double year = 0, population = 0, kardashev = 0;
	private Text[] texts;
	// TODO avatar
	// TODO color 
	private ArrayList<Population> populations;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		texts = new Text[] {
			new Text(game.i18n("player.year") + " : " + year),
			new Text(game.i18n("player.population") + " : " + population),
			new Text(game.i18n("player.kardashev") + " : " + kardashev),
		};
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
		for (Text text: texts){
			text.render();
		}
	}

	@Override
	public void update(double delta) {
		// TODO year, kardashev, population
	}
	
	public int getHeight(){
		return texts.length * (texts[0].getHeight() + DY);
	}
}