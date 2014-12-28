package fr.ircf.humanity.aster;

public enum PlanetType {
	ROCKY(0, "rocky"), HABITABLE(1, "habitable"), GAZEOUS(2, "gazeous");

    private final int value;
    private final String name;
    
    private PlanetType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
}
