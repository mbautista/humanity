package fr.ircf.humanity.component;

public class TextBar extends Bar {

	private static int SPACING = 10, BAR_Y = 2;
	private Text text;
	
	public TextBar(){
		super();
		this.text = new Text();
	}
	
	public TextBar(String text, float[] color) {
		super(color);
		this.text = new Text(text, color);
	}

	@Override
	public void render() {
		super.render();
		text.render();
	}
	
	@Override
	public void setPosition(int x, int y){
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
	
	public void setText(String text){
		this.text.setText(text);
	}
	
	public void setColor(float[] color){
		super.setColor(color);
		text.setColor(color);
	}
}