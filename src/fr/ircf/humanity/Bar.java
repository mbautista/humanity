package fr.ircf.humanity;

import org.lwjgl.opengl.GL11;

public class Bar {

	protected int x, y, width = 100, height = 10, padding = 1, value, max;
	protected float[] color;
	protected float r = 0.2f, g = 0.2f, b = 0.2f, a = 0.5f;
	
	public void render() {
		GL11.glColor4f(r, g, b, a);
		GL11.glRecti(x, y, x + width + 2*padding, y + height + 2*padding);
		GL11.glColor3d(color[0], color[1], color[2]);
		GL11.glRecti(x + padding, y + padding, x + value*width/max, y + height);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
