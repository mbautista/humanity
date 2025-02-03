package fr.ircf.humanity.component;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public class Button extends Component {

	public static enum State { OUT, OVER, DOWN, UP, RDOWN, RUP };
	protected State state = State.OUT;
	protected static float[] COLOR_DISABLED = {0.1f, 0.1f, 0.1f, 0.5f};
	protected static float[] COLOR_OUT = {0.2f, 0.2f, 0.2f, 0.5f};
	protected static float[] COLOR_OVER = {0.4f, 0.4f, 0.4f, 0.5f};
	protected static float[] COLOR_CLICK = {0.6f, 0.6f, 0.6f, 0.5f};
	protected float[] color;
	protected int padding = 10;
	protected Text text;
	protected boolean disabled;
	
	public Button() {
		this("");
	}
	
	public Button(String label) {
		this.text = new Text(label);
		setColor(COLOR_OUT);
	}

	public void render() {
		if (visible()) {
			GL11.glColor4f(color[0], color[1], color[2], color[3]);
			GL11.glRecti(x, y, x + getWidth(), y + getHeight());
			text.render();
		}
	}

	public void update(double delta) {
		updateViewport();
		if (disabled){
			setColor(COLOR_DISABLED);
		}else if (viewport.contains(Mouse.getX(), Display.getHeight()-Mouse.getY())){
			over(delta);
			if (Mouse.isButtonDown(0)) down(delta);
			if (Mouse.isButtonDown(1)) rdown(delta);
			while (Mouse.next()){
			    if (!Mouse.getEventButtonState() && Mouse.getEventButton() == 0) up();
			}
		}else{
			out();
		}
	}
	
	public void over(double delta){
		state = State.OVER;
		setColor(COLOR_OVER);
	}
	
	public void out(){
		state = State.OUT;
		setColor(COLOR_OUT);
	}
	
	public void down(double delta){
		state = State.DOWN;
		setColor(COLOR_CLICK);
	}
	
	public void rdown(double delta){
		state = State.RDOWN;
		setColor(COLOR_CLICK);
	}
	
	public void up(){
		state = State.UP;
		setColor(COLOR_OUT);
	}
	
	public int getWidth() {
		return width>0 ? width : text.getWidth() + 2*padding;
	}
	
	public int getHeight() {
		return height>0 ? height : text.getHeight() + 2*padding;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		text.setPosition(x+padding, y+padding);
		updateViewport();
	}
	
	public void setColor(float[] color) {
		this.color = color;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public void setText(String text){
		this.text.setText(text);
		updateViewport();
	}
}
