package fr.ircf.humanity.aster;

import fr.ircf.humanity.Game;

public class Resource {

	private ResourceType type;
	private double value = 0, delta = 0;
	
	public Resource(ResourceType type, double value){
		this.type = type;
		this.value = value;
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
	
	// TODO make Game.i18n() static and remove game parameter (?)
	public String toString(Game game){
		return game.i18n("resource." + type.getName())
				+ " : " + (Math.round(value*100)/100)
				+ " " + (delta<0 ? "" : "+") + (Math.round(delta*100)/100)
				+ "/" + game.i18n("resource.year");
	}
}
