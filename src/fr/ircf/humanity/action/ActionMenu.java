package fr.ircf.humanity.action;

import java.util.HashMap;
import java.util.Map.Entry;
import org.lwjgl.opengl.Display;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.State;
import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.aster.Resource;
import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.component.Panel;
import fr.ircf.humanity.component.Text;

public class ActionMenu extends Panel implements GameElement {

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
		for (Class<?> actionClass : Action.CLASSES){
			actions.put(actionClass, new ActionMenuItem());
		}
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && game.getCamera().getObject()!=null;
	}
	
	@Override
	public void render() {
		if (aster == null) return;
		if (aster.discovered()){
			name.render();
			type.render();
			renderResources();
		}
		if (aster.getPopulation() != null) renderActions();
	}
	
	public void renderResources(){
		for (Entry<ResourceType, Text> e : resources.entrySet()) {
			e.getValue().render();
		}
	}
	
	public void renderActions(){
		for(Entry<Class<?>, ActionMenuItem> e : actions.entrySet()){
			if (e.getValue().visible()) e.getValue().render();
		}
	}
	
	@Override
	public void update(double delta) {
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			name.setText(aster.getName());
			type.setText(game.i18n("aster." + aster.getType().getName()));
		}
		if (aster == null) return;
		if (aster.getPopulation() != null) updateActions(delta);
		updateResources();
	}
	
	public void updateResources(){
		int i = 0;
		for (Entry<ResourceType, Text> e : resources.entrySet()) {
			Resource r = aster.getResource(e.getKey());
			e.getValue().setText(r==null ? null : r.toString());
			if (r==null) continue;
			e.getValue().setY(Y + DY * (2 + i));
			i++;
		}
	}
	
	public void updateActions(double delta){
		int i = 0;
		for(Entry<Class<?>, ActionMenuItem> e : actions.entrySet()){
			e.getValue().setAction(aster.getPopulation().getAction(e.getKey()));
			if (!e.getValue().visible()) continue;
			e.getValue().setPosition(
				Display.getWidth() - e.getValue().getWidth(),
				Y + 7 * DY + i * e.getValue().getHeight()
			);
			e.getValue().update(delta);
			i++;
		}
	}
	
	public int getWidth(){
		return WIDTH;
	}
}
