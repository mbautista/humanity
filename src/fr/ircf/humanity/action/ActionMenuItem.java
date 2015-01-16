package fr.ircf.humanity.action;

import fr.ircf.humanity.aster.ResourceType;
import fr.ircf.humanity.ui.ButtonIcon;
import fr.ircf.humanity.ui.Text;
import fr.ircf.humanity.ui.TextBar;

public class ActionMenuItem extends ButtonIcon {
	
	public static double DOWN_SPEED = 10;
	public static int WIDTH = 32, HEIGHT = 32, TEXT_X = 230, TEXT_DY = 10;
	protected Action action;
	protected Text name;
	protected TextBar people, level, progress;
	protected boolean selected = false;
	
	public ActionMenuItem(){
		super();
		width = WIDTH;
		height = HEIGHT;
		name = new Text();
		name.setX(x - TEXT_X);
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
			level.render();
			if (action.needsPeople()) people.render();
			//if (action.started()) progress.render();
		}
	}

	public void update(double delta) {
		super.update(delta);
		name.setText(action.i18n("action." + action.getName()));
		name.setPosition(x - TEXT_X, y);
		level.setText(action.i18n("player.level") + " : " + Math.round(action.getLevel()*100)/100d);
		level.setValue(action.getLevel());
		level.setPosition(x - TEXT_X, y + TEXT_DY);
		int i = 1;
		if (action.needsPeople()){
			people.setText(action.i18n("job." + action.getJob().getName()) + " : " + (int)action.getPeople() + "%");
			people.setValue(action.getPeople());
			people.setPosition(x - TEXT_X, y + (++i) * TEXT_DY);
		}
		/*if (action.started()){
			progress.setText((int)action.getProgress() + "%");
			progress.setValue(action.getProgress());
			progress.setPosition(x - TEXT_X, y + (++i) * TEXT_DY);
		}*/
	}
	
	public void down(double delta){
		if (action.needsPeople()) action.incrementPeople(delta / DOWN_SPEED);
	}
	
	public void rdown(double delta){
		if (action.needsPeople()) action.incrementPeople(-delta / DOWN_SPEED);
	}

	public void up(){
		if (!action.needsPeople()) action.toggle();
	}
	
	public void setAction(Action action){
		this.action = action;
		super.setIcon(action.getIcon());
	}
}
