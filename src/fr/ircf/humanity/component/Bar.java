package fr.ircf.humanity.component;

import org.lwjgl.opengl.GL11;

public class Bar extends Component {

	private static float[] COLOR_DEFAULT = {1f, 1f, 1f, 0.5f};
	private static float[] COLOR_BACK = {0.2f, 0.2f, 0.2f, 0.5f};
	protected int width = 100, height = 8, padding = 1;
	protected double value, max;
	protected float[] color = COLOR_DEFAULT;
	
	public Bar(){
		color = COLOR_DEFAULT;
	}
	
	public Bar(float[] color){
		this.color = color;
	}
	
	public void render() {
		GL11.glColor4f(COLOR_BACK[0], COLOR_BACK[1], COLOR_BACK[2], COLOR_BACK[3]);
		GL11.glRecti(x, y, x + width + 2*padding, y + height + 2*padding);
		GL11.glColor3d(color[0], color[1], color[2]);
		GL11.glRectd(x + padding, y + padding, x + width*Math.min(value/max, 1), y + height);
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
	
	public void setColor(float[] color){
		this.color = color;
	}
}
