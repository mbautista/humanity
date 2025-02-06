package fr.ircf.humanity.event;

import java.util.ArrayList;
import java.util.List;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class EventManager {

	private List<EventListener> listeners;

	public EventManager(){
		listeners = new ArrayList<>();
	}

	public void addListener(EventListener listener) {
		listeners.add(listener);
	}

	public void removeListener(EventListener listener) {
		listeners.remove(listener);
	}

	public void notify(Event event) {
		for (EventListener listener : listeners) {
			listener.update(event);
		}
	}
}
