package fr.ircf.humanity.aster;

public enum AsterType {
	PLANET(10, "planet"),
	ROCKY_PLANET(11, "rocky_planet"),
	HABITABLE_PLANET(12, "habitable_planet"),
	GAZEOUS_PLANET(13, "gazeous_planet"),
	STAR(20, "star"),
	BLUE_STAR(21, "blue_star"),
	MEDIUM_STAR(22, "medium_star"),
	RED_GIANT_STAR(23, "red_giant_star");
	// TODO comet
	// TODO asteroid
	// TODO black hole
	// TODO nebula
	
    private final int value;
    private final String name;
    
    private AsterType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPlanetValue(){
    	return value - PLANET.getValue() - 1;
    }
    
    public int getStarValue(){
    	return value - STAR.getValue() - 1;
    }
}
