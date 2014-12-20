package fr.ircf.humanity;

import org.lwjgl.opengl.*;

public class Button {

	private float r = 1.0f, g = 1.0f, b = 1.0f, a = 1.0f;
	private int x = 0, y = 0, padding = 10;
	private Text text;
	
	public Button(String label) throws Exception {
		this.text = new Text(label);
	}

	public void render() {
		GL11.glColor4f(r, g, b, a);
		GL11.glRecti(x, y, x + getWidth(), y + getHeight());
		text.render();
	}

	public void update(double delta) {
		// TODO update button
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
	}
	
	public void setColor(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
}
