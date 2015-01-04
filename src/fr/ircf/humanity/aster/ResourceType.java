package fr.ircf.humanity.aster;

public enum ResourceType {

	ENERGY(0, "energy", new float[] { 1, 1, 0 }),
	FOOD(1, "food", new float[] { 0, 1, 0 }),
	WATER(2, "water", new float[] { 0, 0, 1 }),
	PEOPLE(3, "people", new float[] { 1, 0, 0 });
	
	private final int id;
	private final String name;
	private final float[] color;
	
	private ResourceType(int id, String name, float[] color){
		this.id = id;
		this.name = name;
		this.color = color;
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
}
