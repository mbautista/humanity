package fr.ircf.humanity.component;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Slider extends Component {
	
	private static float[] COLOR_DEFAULT = {1f, 1f, 1f, 0.5f};
	private static float[] COLOR_BACK = {0.1f, 0.1f, 0.1f, 1f};
	private static int BUTTON_WIDTH = 28, BUTTON_HEIGHT = 20, BAR_HEIGHT = 10, BAR_WIDTH = 4;
	private double value, max;
	private Button button;
	
	public Slider(){
		final Slider slider = this;
		button = new Button() {
			public void down() { slider.down(); }
			public void up() { slider.up(); }
		};
		button.setWidth(BUTTON_WIDTH);
		button.setHeight(BUTTON_HEIGHT);
	}
	
	public void render(){
		GL11.glColor4f(COLOR_BACK[0], COLOR_BACK[1], COLOR_BACK[2], COLOR_BACK[3]);
		GL11.glRecti(x + button.getWidth()/2 - BAR_WIDTH/2, y, x + button.getWidth()/2 + BAR_WIDTH/2, y + getHeight());
		button.render();
	}

	public void update(double delta) {
		button.setPosition(x, y + Math.min(value,  max) * BAR_HEIGHT - BUTTON_HEIGHT/2);
		button.update(delta);
	}
	
	public void down(){
		value = Math.min(getHeight(), Math.max(0, Display.getHeight()-Mouse.getY()-y)) / (double)BAR_HEIGHT;
	}
	
	public void up(){
		
	}
	
	public int getWidth(){
		return BAR_WIDTH;
	}
	
	public int getHeight(){
		return ((int)max * BAR_HEIGHT);
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}
}
