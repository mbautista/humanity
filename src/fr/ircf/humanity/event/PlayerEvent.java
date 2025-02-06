package fr.ircf.humanity.event;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.game.Player;

public class PlayerEvent extends Event {
	
	private Player player;
	
	public PlayerEvent(int year, String type, Player player){
		super(year, type);
		this.player = player;
	}

	public Panel getLogPanel(Game game) {
		Panel panel = super.getLogPanel(game);
		switch(type) {
		case "humanity":
			panel.add(new Text(String.format(game.i18n("event.billion" + (player.getHumanity()<2?"":"s")), Math.floor(player.getHumanity()))));
			break;
		case "level":
			panel.add(new Text(String.format(game.i18n("event.kardashev"), Math.floor(player.getLevel() * 100)/100d)));	
			break;
		}
		return panel;
	}
}
