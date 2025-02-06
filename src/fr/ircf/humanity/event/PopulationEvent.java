package fr.ircf.humanity.event;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.Population;
import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

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

	public Panel getLogPanel(Game game) {
		Panel panel = super.getLogPanel(game);
		panel.add(new Text(game.i18n("resource.people"), ResourceType.PEOPLE.getColor()));
		panel.add(new Text(" " + game.i18n("event.from")));
		panel.add(new Text(" " + population.getAster().getName(), population.getAster().getColor()));
		panel.add(new Text(" " + game.i18n("event.population." + need.getName())));
		return panel;
	}
}
