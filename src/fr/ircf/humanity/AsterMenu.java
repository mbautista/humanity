package fr.ircf.humanity;

import java.util.HashMap;

public class AsterMenu implements GameElement {

	private Text name;
	private HashMap<Integer, Slider> jobs;
	private Aster aster;
	private Game game;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.GAME && game.getCamera().getObject()!=null;
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void update(double delta) {	
		if (aster != (Aster)game.getCamera().getObject()){
			aster = (Aster) game.getCamera().getObject();
			try { name = new Text(aster.getName()); } catch(Exception e){}
			// TODO jobs
		}
	}
}
