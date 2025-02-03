package fr.ircf.humanity.component;

public class ButtonIcon extends Button {

	protected Image image;
	
	public ButtonIcon(String path){
		super();
		image = new Image(path);
	}
	
	public ButtonIcon() {
		super();
	}

	public void render(){
		if (visible() && image != null){
			super.render();
			image.render();
		}
	}

	public Image getImage() {
		return image;
	}
}
