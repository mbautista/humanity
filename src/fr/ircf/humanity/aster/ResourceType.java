package fr.ircf.humanity.aster;

public enum ResourceType {

	ENERGY(0, "energy", new float[] { 1, 1, 0 }, 128, 0.1, 0.5),
	FOOD(1, "food", new float[] { 0, 1, 0 }, 128, 0.02, 0.2),
	WATER(2, "water", new float[] { 0, 0, 1 }, 128, 0.1, 0.5),
	PEOPLE(3, "people", new float[] { 1, 0, 0 }, 16); // TODO max people should depend on planet land size
	
	private final int id;
	private final String name;
	private final float[] color;
	private final double max, minConsumption, maxConsumption;
	
	private ResourceType(int id, String name, float[] color, double max){
		this(id, name, color, max, 0, 0);
	}

	private ResourceType(int id, String name, float[] color, double max, double minConsumption, double maxConsumption){
		this.id = id;
		this.name = name;
		this.color = color;
		this.max = max;
		this.minConsumption = minConsumption;
		this.maxConsumption = maxConsumption;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public float[] getColor() {
		return color;
	}
	
	public double getMax() {
		return max;
	}
	
	public double getMinConsumption() {
		return minConsumption;
	}
	
	public double getMaxConsumption() {
		return maxConsumption;
	}
}
