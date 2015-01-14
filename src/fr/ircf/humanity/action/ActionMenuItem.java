package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.ui.ButtonIcon;
import fr.ircf.humanity.ui.Text;
import fr.ircf.humanity.ui.TextBar;

public class ActionMenuItem extends ButtonIcon {
	
	public static double DOWN_SPEED = 10;
	public static int WIDTH = 32, HEIGHT = 32;
	protected Action action;
	protected Text name;
	protected TextBar people, level, progress;
	protected boolean selected = false;
	
	public ActionMenuItem(){
		super();
		width = WIDTH;
		height = HEIGHT;
		name = new Text();
		people = new TextBar("", ResourceType.PEOPLE.getColor());
		people.setMax(100);
		level = new TextBar();
		level.setMax(100);
		progress = new TextBar();
		progress.setMax(100);
	}
	
	public boolean visible(){
		return action.discovered();
	}
	
	public void render(){
		if (action.selected()) super.setColor(action.getJob().getColor());
		super.render();
		if (state == State.OVER){
			name.render();
			if (action.needsPeople()) people.render();
			level.render();
			if (action.started()) progress.render();
		}
	}

	public void update(double delta) {
		// TODO update positions
		super.update(delta);
		name.setText(action.i18n("action." + action.getName()));
		if (action.needsPeople()){
			people.setText(action.i18n("job." + action.getJob().getName()) + " : " + action.getPeople());
			people.setValue(action.getPeople());
		}
		level.setText(action.i18n("player.level") + " : " + action.getLevel());
		level.setValue(action.getLevel());
		if (action.started()){
			progress.setText(action.getProgress() + "%");
			progress.setValue(action.getProgress());
		}
	}
	
	public void down(double delta){
		if (action.needsPeople()) action.incrementPeople(delta / DOWN_SPEED);
	}
	
	public void rdown(double delta){
		if (action.needsPeople()) action.incrementPeople(-delta / DOWN_SPEED);
	}

	public void up(){
		action.toggle();
	}
	
	public void setAction(Action action){
		this.action = action;
		super.setIcon(action.getIcon());
	}
}
