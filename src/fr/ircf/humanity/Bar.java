package fr.ircf.humanity;

import org.lwjgl.opengl.GL11;

public class Bar extends Component {

	private static float[] COLOR_DEFAULT = {1f, 1f, 1f, 0.5f};
	private static float[] COLOR_BACK = {0.2f, 0.2f, 0.2f, 0.5f};
	protected int x, y, width = 100, height = 10, padding = 1, value, max;
	protected float[] color = COLOR_DEFAULT;
	
	public void render() {
		GL11.glColor4f(COLOR_BACK[0], COLOR_BACK[1], COLOR_BACK[2], COLOR_BACK[3]);
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
}
