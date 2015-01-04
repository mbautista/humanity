package fr.ircf.humanity.aster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.opengl.Display;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.job.JobFactory;
import fr.ircf.humanity.job.JobMenuItem;
import fr.ircf.humanity.ui.Component;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public class AsterMenu extends Panel implements GameElement {

	private static int X = 10, Y = 10, DY = 20, WIDTH = 150;
	private Text name, type;
	private HashMap<ResourceType, Text> resources;
	private Aster aster;
	private Game game;
	private HashMap<Class<?>, JobMenuItem> jobs;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		name = new Text();
		name.setPosition(Display.getWidth() - getWidth() - X, Y);
		type = new Text();
		type.setPosition(Display.getWidth() - getWidth() - X, Y + DY);
		initResources();
		// TODO jobs = JobFactory.getJobMenuItems();
		// TODO jobs.setPosition();
	}
	
	public void initResources(){
		resources = new HashMap<ResourceType, Text>();
		for (ResourceType type : ResourceType.values()){
			Text t = new Text(null, type.getColor());
			t.setX(Display.getWidth() - getWidth() - X);
			resources.put(type, t);
		}
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && game.getCamera().getObject()!=null;
	}
	
	@Override
	public void render() {
		name.render();
		type.render();
		renderResources();
		// TODO renderJobs();
	}
	
	public void renderResources(){
		for (Entry<ResourceType, Text> e : resources.entrySet()) {
			e.getValue().render();
		}
	}
	
	public void renderJobs(){
		for(Entry<Class<?>, JobMenuItem> job : jobs.entrySet()){
			job.getValue().render();
		}
	}
	
	@Override
	public void update(double delta) {	
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			name.setText(aster.getName());
			type.setText(game.i18n("aster." + aster.getType().getName()));
			// TODO updateJobs(delta);
		}
		updateResources();
	}
	
	public void updateResources(){
		int i = 0;
		for (Entry<ResourceType, Text> e : resources.entrySet()) {
			Resource r = aster.getResource(e.getKey());
			e.getValue().setText(r==null ? null : r.toString(game));
			if (r==null) continue;
			e.getValue().setY(Y + DY * (2 + i));
			i++;
		}
	}
	
	public void updateJobs(double delta){
		for(Entry<Class<?>, JobMenuItem> job : jobs.entrySet()){
			job.getValue().setJob(((Planet)aster).getPopulation().getJob(job.getKey()));
			job.getValue().update(delta);
		}
	}
	
	public int getWidth(){
		return WIDTH;
	}
}
