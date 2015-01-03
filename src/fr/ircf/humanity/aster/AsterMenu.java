package fr.ircf.humanity.aster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.job.JobFactory;
import fr.ircf.humanity.job.JobMenuItem;
import fr.ircf.humanity.ui.Text;

public class AsterMenu implements GameElement {

	private Text name;
	private Aster aster;
	private Game game;
	private HashMap<Class<?>, JobMenuItem> jobMenuItems;
	// TODO type, people
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		name = new Text();
		// TODO name.setPosition();
		jobMenuItems = JobFactory.getJobMenuItems();
		// TODO jobMenuItems.setPosition();
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && game.getCamera().getObject()!=null;
	}
	
	@Override
	public void render() {
		name.render();
		for(Entry<Class<?>, JobMenuItem> e : jobMenuItems.entrySet()){
			e.getValue().render();
		}
	}
	
	@Override
	public void update(double delta) {	
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			name.setText(aster.getName());
			for(Entry<Class<?>, JobMenuItem> e : jobMenuItems.entrySet()){
				e.getValue().setJob(((Planet)aster).getPopulation().getJob(e.getKey()));
				e.getValue().update(delta);
			}
		}
	}
}
