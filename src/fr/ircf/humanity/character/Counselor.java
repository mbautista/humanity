package fr.ircf.humanity.character;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.Job;

public class Counselor extends Character {

	@Override
	public void init(Game game) throws Exception {
		name = "counselor";
		path = "characters/counselor1.jpg";
		job = Job.HUMANITY;
		super.init(game);
	}
}
