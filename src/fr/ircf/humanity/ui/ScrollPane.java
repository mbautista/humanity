package fr.ircf.humanity.ui;

public class ScrollPane extends Panel {
	
	private Panel container;
	private Slider slider;
	private Button up, down;
	public enum Overflow { VISIBLE, HIDDEN };
	private Overflow overflow = Overflow.HIDDEN; // TODO Overflow.VISIBLE
	private boolean autoscroll = false;
	
	public ScrollPane(){
		container = new Panel();
		up = new Button();
		down = new Button();
		slider = new Slider();
	}
	
	public void render(){
		if (overflow == Overflow.VISIBLE){
			up.render();
			down.render();
			slider.render();
		}
		container.render();
	}
	
	public void add(Component c){
		container.add(c);
		if (autoscroll) scrollTo(getCursorX(), getCursorY());
	}
	
	public void scrollTo(int x, int y){
		container.moveComponentsTo(x, y);
	}
	
	public void scrollBy(int dx, int dy){
		container.moveComponentsBy(dx, dy);
	}
	
	public void setAutoscroll(boolean autoscroll){
		this.autoscroll = autoscroll;
	}
	
	public void setPosition(int x, int y){
		container.setPosition(x,  y);
	}
}
