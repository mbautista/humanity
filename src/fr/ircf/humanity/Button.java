package fr.ircf.humanity;

public class Button {

	private Text text;
	
	public Button(String label) throws Exception {
		this.text = new Text(label);
	}

	public void render() {
		// TODO render button
		text.render();
	}

	public void update(double delta) {
		// TODO update button
	}
	
	public int getWidth(){
		return text.getWidth();
	}
	
	public void setPosition(int x, int y){
		text.setPosition(x,  y);
	}
}
