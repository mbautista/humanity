package fr.ircf.humanity.aster;

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
}
