package fr.ircf.humanity.component;

public class ImageButton extends Button {

	protected Image image;
	
	public ImageButton(String path){
		super();
		setImage(path);
	}
	
	public ImageButton() {
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

	public void setImage(String path) {
		this.image = new Image(path);
	}
}
