package fr.ircf.humanity.action;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.job.Job;

public class GrowFood extends Action {

	public static String name = "growFood";

	public void init(Game game){
		setJob(Job.FARMERS);
	}
	
	public void render(){
		// TODO button, bar (if running)
	}
	
	public void update(double delta){
		// TODO level, duration, time, running
	}
}
