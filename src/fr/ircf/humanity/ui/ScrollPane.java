package fr.ircf.humanity.ui;

public class ScrollPane extends Panel {
	
	private Slider slider;
	private Button up, down;
	
	public ScrollPane(){
		up = new Button();
		down = new Button();
		slider = new Slider();
	}
	
	public void render(){
		up.render();
		down.render();
		slider.render();
		super.render();
	}
}
