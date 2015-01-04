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

	private static int X = 10, Y = 10, DY = 20, WIDTH = 120;
	private Text name, type;
	private HashMap<Integer, Text> resources;
	private Aster aster;
	private Game game;
	private HashMap<Class<?>, JobMenuItem> jobMenuItems;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		name = new Text();
		name.setPosition(Display.getWidth() - getWidth() - X, Y);
		type = new Text();
		type.setPosition(Display.getWidth() - getWidth() - X, Y + DY);
		// TODO init resources
		// TODO jobMenuItems = JobFactory.getJobMenuItems();
		// TODO jobMenuItems.setPosition();
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && game.getCamera().getObject()!=null;
	}
	
	@Override
	public void render() {
		name.render();
		type.render();
		/*for(Entry<Class<?>, JobMenuItem> e : jobMenuItems.entrySet()){
			e.getValue().render();
		}*/
	}
	
	@Override
	public void update(double delta) {	
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			name.setText(aster.getName());
			type.setText(game.i18n("aster." + aster.getType().getName()));
			/*for(Entry<Class<?>, JobMenuItem> e : jobMenuItems.entrySet()){
				e.getValue().setJob(((Planet)aster).getPopulation().getJob(e.getKey()));
				e.getValue().update(delta);
			}*/
		}
	}
	
	public int getWidth(){
		return WIDTH;
	}
}
