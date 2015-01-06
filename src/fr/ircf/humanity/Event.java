package fr.ircf.humanity;

import java.util.ArrayList;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.ui.Component;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public class Event extends Panel {
	
	public Event(String message){
		super();
		add(new Text(message));
	}
	
	public Event(Action action){
		// TODO
	}
}
