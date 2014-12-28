package fr.ircf.humanity.job;

import java.util.HashMap;
import java.util.Map.Entry;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.Menu;

public class JobMenu extends Menu {

	private Game game;
	private HashMap<Integer, Job> jobs;
	
	public void init(Game game) throws Exception{
		this.game = game;
		addJob(Job.FARMERS, new Farmers());
		addJob(Job.PHYSICISTS, new Physicists());
		addJob(Job.BIOLOGISTS, new Biologists());
		addJob(Job.ARMY, new Army());
		addJob(Job.MERCHANTS, new Merchants());
		for(Entry<Integer, Job> entry : jobs.entrySet()){
			entry.getValue().init(game);
		}
	}
	
	public void render(){
		for(Entry<Integer, Job> entry : jobs.entrySet()){
			entry.getValue().render();
		}
	}
	
	public void update(double delta){
		for(Entry<Integer, Job> entry : jobs.entrySet()){
			entry.getValue().update(delta);
		}
	}
	
	public void addJob(int key, Job value){
		jobs.put(key, value);
	}
}