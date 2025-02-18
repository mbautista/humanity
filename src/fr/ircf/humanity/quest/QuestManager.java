package fr.ircf.humanity.quest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.event.Event;
import fr.ircf.humanity.event.EventListener;
import fr.ircf.humanity.component.ScrollPane;
import fr.ircf.humanity.component.Text;

public class QuestManager implements GameElement, EventListener {

	private Game game;
	private List<Quest> quests, remainingQuests;
	private static int X = 10, DY = 20;
	private Text message;
	private ScrollPane scrollPane;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		quests = new ArrayList<Quest>();
		remainingQuests = new ArrayList<Quest>(Arrays.asList(Quest.values()));

		scrollPane = new ScrollPane();
		scrollPane.setPosition(X, Display.getHeight() / 2 - getHeight());
		scrollPane.setWidth(getWidth());
		scrollPane.setHeight(getHeight());
		
		message = new Text();
		message.setPosition(0, Display.getHeight() / 2 - DY);
		message.setWidth(Display.getWidth());
		message.setHeight(DY);
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && !quests.isEmpty();
	}

	@Override
	public void render() {
		scrollPane.render();
		message.render();
	}

	@Override
	public void update(double delta) {
		// TODO Animate message
	}

	@Override
	public void update(Event event) {
		// Trigger quests by event
		for (Iterator<Quest> iterator = remainingQuests.iterator(); iterator.hasNext(); ) {
			Quest q = iterator.next();
		      if (q.getTrigger().apply(event)) {
		    	  if (!q.isRepeatable()) {
		    		  iterator.remove();
		    	  }
		    	  quests.add(q);
		    	  //scrollPane.add(new Text()); // TODO
		      }
		}
		// Success or failure quests by event
		for (Iterator<Quest> iterator = quests.iterator(); iterator.hasNext(); ) {
			Quest q = iterator.next();
		      if (q.getSuccess().apply(event)) {
		    	  quests.remove(q);
		    	  // TODO remove quest from scrollPane
		    	  // TODO set success message
		    	  // TODO play success sound
		      }else if (q.getFailure().apply(event)) {
		    	  quests.remove(q);
		    	  // TODO remove quest from scrollPane
		    	  // TODO set failure message
		    	  // TODO play failure sound
		      }
		}
	}

	public int getWidth() {
		return Display.getWidth()/3;
	}

	public int getHeight() {
		return 150;
	}
}
