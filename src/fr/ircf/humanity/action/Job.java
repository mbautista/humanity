package fr.ircf.humanity.action;

public enum Job {

	FARMERS(0, "farmers", new float[] { 0, 1, 0 }),
	SCIENTISTS(1, "scientists", new float[] { 0, 0, 1 }),
	ARMY(2, "army", new float[] { 0, 0, 1 }),
	MERCHANTS(3, "merchants", new float[] { 1, 1, 0 }); 
	
	private final int id;
	private final String name;
	private final float[] color;
	
	private Job(int id, String name, float[] color){
		this.id = id;
		this.name = name;
		this.color = color;
	}

	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public float[] getColor(){
		return color;
	}
}
