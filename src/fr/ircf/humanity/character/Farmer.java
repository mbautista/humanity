package fr.ircf.humanity.character;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.Job;

public class Farmer extends Character {

	@Override
	public void init(Game game) throws Exception {
		name = "farmer";
		path = "characters/farmer1.jpg";
		job = Job.FARMERS;
		super.init(game);
	}
}
