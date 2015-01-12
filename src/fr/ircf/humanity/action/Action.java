package fr.ircf.humanity.action;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.ircf.humanity.Event;
import fr.ircf.humanity.Game;
import fr.ircf.humanity.Player;
import fr.ircf.humanity.Population;
import fr.ircf.humanity.aster.Aster;
import fr.ircf.humanity.ui.Text;

abstract public class Action {

	public static Class<?>[] CLASSES = new Class[] {
		Discover.class,
		Explore.class,
		GrowFood.class,
		UseChemicals.class,
		UseHydroponics.class
		// TODO all actions
	};
	public static enum State { START, STOP };
	protected Job job;
	protected String name, icon;
	protected double people, progress;
	protected State state = State.STOP;
	protected Aster source, target;
	protected boolean selected = false,
			needsPeople = false, 
			needsTarget = false, 
			discovered = false;
	protected HashMap<Class<?>, Double> requiresLevels;
	
	public Action(Aster source){
		this.source = source;
		requiresLevels = new HashMap<Class<?>, Double>();
	}
	
	public void render(){
		// TODO render action
	}
	
	public void update(double delta){
		if (source.getGame().getPlayer().hasLevels(requiresLevels)){
			discover();
		}
	}
	
	protected void incrementLevel(double delta){
		source.getGame().getPlayer().incrementLevel(this.getClass(), delta);
	}
	
	public void start(){
		state = State.START;
		source.getGame().getLog().addEvent(createEvent());
	}
	
	public void start(Aster target){
		this.target = target;
		start();
	}
	
	public void stop(){
		state = State.STOP;
		target = null;
		select();
	}

	public String getName() {
		return name;
	}
	
	public Job getJob(){
		return job;
	}
	
	public boolean discovered() {
		return discovered;
	}
	
	public void discover(){
		discovered = true;
		source.getGame().getLog().addEvent(createDiscoverEvent());
		// TODO discover job
	}
	
	public void select(){
		selected = needsTarget && !selected;
		source.getGame().getPlayer().setAction(selected ? this : null);
	}

	public boolean selected(){
		return selected;
	}
	
	public boolean needsPeople(){
		return needsPeople;
	}
	
	public boolean needsTarget(){
		return needsTarget;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public Aster getSource(){
		return source;
	}
	
	public Aster getTarget(){
		return target;
	}
	
	protected Event createEvent(){
		Event event = new Event(source.getGame());
		event.add(new Text(source.getGame().i18n("job." + job.getName()), job.getColor()));
		if (target == null || source != target ){
			event.add(new Text(" " + source.getGame().i18n("event.from")));
			event.add(new Text(" " + source.getName(), source.getColor()));
		}
		event.add(new Text(" " + source.getGame().i18n("action." + name)));
		if (target != null){
			event.add(new Text(" " + target.getName(), target.getColor()));
		}
		return event;
	}
	
	protected Event createDiscoverEvent(){
		Event event = new Event(source.getGame());
		event.add(new Text(source.getGame().i18n("job." + job.getName()), job.getColor()));
		event.add(new Text(" " + source.getGame().i18n("event.discovered.action")));
		event.add(new Text(source.getGame().i18n("action." + name), job.getColor()));
		return event;
	}
}
