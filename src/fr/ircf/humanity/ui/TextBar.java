package fr.ircf.humanity.ui;

import org.newdawn.slick.Color;

public class TextBar extends Bar {

	private static int SPACING = 10, BAR_Y = 2;
	private Text text;
	
	public TextBar(String text, float[] color) {
		super(color);
		this.text = new Text(text, new Color(color[0], color[1], color[2]));
	}

	@Override
	public void render() {
		super.render();
		text.render();
	}
	
	@Override
	public void setPosition(double x, double y){
		text.setPosition(x,  y);
		super.setPosition(x + SPACING + text.getWidth(), y + BAR_Y);
	}
	
	@Override
	public int getWidth(){
		return super.getWidth() + SPACING + text.getWidth();
	}
	
	@Override
	public int getHeight(){
		return Math.max(super.getHeight(), text.getHeight());
	}
}