package fr.ircf.humanity.job;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.action.ActionMenuItem;
import fr.ircf.humanity.ui.Button;

public class JobMenuItem {
	
	private Game game;
	private Job job;
	private Button button;
	private HashMap<Class<?>, ActionMenuItem> actions;

	public void init(Game game, Job job) {
		this.game = game;
		this.job = job;
		button = new Button(game.i18n(job.getName()));
	}
	
	public void initActions(){
		actions = new HashMap<Class<?>, ActionMenuItem>();
		// TODO
	}
	
	public void render(){
		button.render();
		for(Entry<Class<?>, ActionMenuItem> e : actions.entrySet()){
			e.getValue().render();
		}
	}
	
	public void update(double delta){
		for(Entry<Class<?>, ActionMenuItem> e : actions.entrySet()){
			e.getValue().update(delta);
		}
	}
	
	public void setJob(Job job) {
		this.job = job;
		init(game, job);
	}
	
	public void setPosition(int x, int y){
		// TODO
	}
}
