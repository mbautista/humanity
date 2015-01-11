package fr.ircf.humanity.aster;

import java.util.HashMap;
import java.util.Map.Entry;
import org.lwjgl.opengl.Display;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.action.Action;
import fr.ircf.humanity.action.ActionMenuItem;
import fr.ircf.humanity.ui.Panel;
import fr.ircf.humanity.ui.Text;

public class AsterMenu extends Panel implements GameElement {

	private static int X = 10, Y = 10, DY = 20, WIDTH = 150;
	private Text name, type;
	private HashMap<ResourceType, Text> resources;
	private Aster aster;
	private Game game;
	private HashMap<Class<?>, ActionMenuItem> actions;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		name = new Text();
		name.setPosition(Display.getWidth() - getWidth() - X, Y);
		type = new Text();
		type.setPosition(Display.getWidth() - getWidth() - X, Y + DY);
		initResources();
		initActions();
	}
	
	public void initResources(){
		resources = new HashMap<ResourceType, Text>();
		for (ResourceType type : ResourceType.values()){
			Text text = new Text(null, type.getColor());
			text.setX(Display.getWidth() - getWidth() - X);
			resources.put(type, text);
		}
	}
	
	public void initActions(){
		actions = new HashMap<Class<?>, ActionMenuItem>();
		int i = 0;
		for (Class<?> actionClass : Action.CLASSES){
			ActionMenuItem action = new ActionMenuItem();
			action.setPosition(
				Display.getWidth() - action.getWidth(),
				Y + 7 * DY + i * action.getHeight()
			);
			actions.put(actionClass, action);
			i++;
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
		renderActions();
	}
	
	public void renderResources(){
		for (Entry<ResourceType, Text> e : resources.entrySet()) {
			e.getValue().render();
		}
	}
	
	public void renderActions(){
		for(Entry<Class<?>, ActionMenuItem> e : actions.entrySet()){
			e.getValue().render();
		}
	}
	
	@Override
	public void update(double delta) {	
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			name.setText(aster.getName());
			type.setText(game.i18n("aster." + aster.getType().getName()));
			updateActions(delta);
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
	
	public void updateActions(double delta){
		for(Entry<Class<?>, ActionMenuItem> e : actions.entrySet()){
			e.getValue().setAction(((Planet)aster).getPopulation().getAction(e.getKey()));
			e.getValue().update(delta);
		}
	}
	
	public int getWidth(){
		return WIDTH;
	}
}
