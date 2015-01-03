package fr.ircf.humanity.aster;

import java.util.HashMap;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.job.Job;
import fr.ircf.humanity.ui.Button;
import fr.ircf.humanity.ui.Slider;
import fr.ircf.humanity.ui.Text;

public class AsterMenu implements GameElement {

	private Text name;
	private Aster aster;
	private Game game;
	private HashMap<Integer, Job> jobs;
	private HashMap<Integer, Action> actions;
	// TODO type, people
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		name = new Text();
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && game.getCamera().getObject()!=null;
	}
	
	@Override
	public void render() {
		// TODO render name, jobs, actions
	}
	
	@Override
	public void update(double delta) {	
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			name.setText(aster.getName());
			// TODO update jobs according to player
			// TODO update actions according to population
		}
	}
}
