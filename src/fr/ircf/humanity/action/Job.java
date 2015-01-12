package fr.ircf.humanity.action;

public enum Job {

	HUMANITY(0, "humanity", new float[] { 1, 1, 1, 0.5f }),
	FARMERS(1, "farmers", new float[] { 0, 1, 0, 0.5f }),
	SCIENTISTS(2, "scientists", new float[] { 0, 0, 1, 0.5f }),
	ARMY(3, "army", new float[] { 0, 0, 1, 0.5f }),
	MERCHANTS(4, "merchants", new float[] { 1, 1, 0, 0.5f }); 
	
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
