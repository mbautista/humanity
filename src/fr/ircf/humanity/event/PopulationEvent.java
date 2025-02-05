package fr.ircf.humanity.event;

import fr.ircf.humanity.Population;
import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.log.LogEntry;

public class PopulationEvent extends Event {

	private Population population;
	private ResourceType need;
	
	public PopulationEvent(int year, String type, Population population){
		super(year, type);
		this.population = population;
	}
	
	public PopulationEvent(int year, String type, Population population, ResourceType need){
		this(year, type, population);
		this.need = need;
	}

	public LogEntry toLogEntry() {
		// TODO
		return null;
	}
}
