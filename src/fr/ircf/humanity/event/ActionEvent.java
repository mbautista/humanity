package fr.ircf.humanity.event;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class ActionEvent extends Event{
	
	private Action action;

	public Action getAction() {
		return action;
	}

	public ActionEvent(int year, String type, Action action){
		super(year, type);
		this.action = action;
	}

	// TODO move method to EventListener ?
	public Panel getLogPanel(Game game) {
		Panel panel = super.getLogPanel(game);
		panel.add(new Text(game.i18n("job." + action.getJob().getName()), action.getJob().getColor()));
		switch(type) {
			case "discover":
				panel.add(new Text(" " + game.i18n("event.discovered.action")));
				panel.add(new Text(" " + game.i18n("action." + action.getName()), action.getJob().getColor()));
				break;
			case "start":
				if (action.getTarget() == null || action.getSource() != action.getTarget() ){
					panel.add(new Text(" " + game.i18n("event.from")));
					panel.add(new Text(" " + action.getSource().getName(), action.getSource().getColor()));
				}
				panel.add(new Text(" " + game.i18n("action." + action.getName())));
				if (action.getTarget() != null){
					panel.add(new Text(" " + action.getTarget().getName(), action.getTarget().getColor()));
				}
				break;
		}
		return panel;
	}
}
