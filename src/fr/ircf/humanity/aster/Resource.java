package fr.ircf.humanity.aster;

import fr.ircf.humanity.Game;

public class Resource {

	private Game game;
	private ResourceType type;
	private double value = 0, delta = 0;
	
	public Resource(Game game, ResourceType type, double value){
		this.game = game;
		this.type = type;
		this.value = value;
	}

	public void update(double delta){
		this.value += this.delta * delta * Planet.YEAR_SCALE / game.getPlayer().getPlanet().getHoursInYear();
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getDelta() {
		return delta;
	}
	
	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	public void incrementDelta(double delta) {
		this.delta += delta;
	}
	
	public String toString(){
		return game.i18n("resource." + type.getName())
				+ " : " + (Math.floor(value*100)/100d)
				+ " " + (delta<0 ? "" : "+") + (Math.floor(delta*100)/100d)
				+ "/" + game.i18n("resource.year");
	}
}
