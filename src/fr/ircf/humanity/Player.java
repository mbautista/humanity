package fr.ircf.humanity;

import java.util.ArrayList;

public abstract class Player implements GameElement{

	private Game game;
	private double kardashev;
	private double year;
	private double population;
	// TODO avatar
	// TODO color 
	private ArrayList<Population> populations;

	@Override
	public void init(Game game) throws Exception {
		this.game = game;
	}

	@Override
	public boolean visible() {
		return game.getState() == State.GAME;
	}

	@Override
	public void render() {
		// TODO year, kardashev, population, avatar
	}

	@Override
	public void update(double delta) {
		// TODO year, kardashev, population
	}
}
