package fr.ircf.humanity;

import java.awt.Rectangle;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public class Button extends Component {

	private static float[] COLOR_OUT = {0.2f, 0.2f, 0.2f, 0.5f};
	private static float[] COLOR_OVER = {0.4f, 0.4f, 0.4f, 0.5f};
	private static float[] COLOR_CLICK = {0.6f, 0.6f, 0.6f, 0.5f};
	private float[] color;
	private int padding = 10;
	private Text text;
	private Rectangle viewport;
	
	public Button(String label) throws Exception {
		this.text = new Text(label);
		updateViewport();
		setColor(COLOR_OUT);
	}

	public void render() {
		GL11.glColor4f(color[0], color[1], color[2], color[3]);
		GL11.glRecti(x, y, x + getWidth(), y + getHeight());
		text.render();
	}

	public void update(double delta) {
		if (viewport.contains(Mouse.getX(), Display.getHeight()-Mouse.getY())){
			over();
			if (Mouse.isButtonDown(0)) down();
			while (Mouse.next()){
			    if (!Mouse.getEventButtonState() && Mouse.getEventButton() == 0) up();
			}
		}else{
			out();
		}
	}
	
	public void over(){
		setColor(COLOR_OVER);
	}
	
	public void out(){
		setColor(COLOR_OUT);
	}
	
	public void down(){
		setColor(COLOR_CLICK);
	}
	
	public void up(){
		setColor(COLOR_OUT);
	}
	
	public int getWidth() {
		return text.getWidth() + 2*padding;
	}
	
	public int getHeight() {
		return text.getHeight() + 2*padding;
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
	
	private void updateViewport(){
		viewport = new Rectangle(x, y, getWidth(), getHeight());
	}
}
