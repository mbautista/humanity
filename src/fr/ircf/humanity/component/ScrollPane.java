package fr.ircf.humanity.component;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

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
		GL11.glScissor(x, Display.getHeight() - y - height, width, height);
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		container.render();
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
	
	public void add(Component c){
		container.add(c);
		if (autoscroll) scrollTo(0, Math.min(0, height - container.getHeight()));
	}
	
	public void scrollTo(int x, int y){
		container.setPosition(this.x + x, this.y + y);
	}
	
	public void setAutoscroll(boolean autoscroll){
		this.autoscroll = autoscroll;
	}
	
	public void setPosition(int x, int y){
		super.setPosition(x, y);
		container.setPosition(x,  y);
	}
}
