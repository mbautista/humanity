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
			image.render(); // FIXME position and size is zero ?!
			System.out.println("render image : x = "+image.getX()+", y="+image.getY()+",width="+image.getWidth()+", height="+image.getHeight());
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(String path) {
		this.image = new Image(path);
	}

	/* TODO find a better way */
	/*public void setPosition(int x, int y){
		super.setPosition(x,y);
		if (image!= null) image.setPosition(x, y);
	}
	public void setPosition(double x, double y){
		super.setPosition(x,y);
		if (image!= null) image.setPosition(x, y);
	}
	public void setX(int x) {
		super.setX(x);
		if (image!= null) image.setX(x);
	}
	public void setY(int y) {
		super.setY(y);
		if (image!= null) image.setY(y);
	}
	public void setWidth(int width) {
		super.setWidth(width);
		if (image!= null) image.setWidth(width);
	}
	public void setHeight(int height) {
		super.setHeight(height);
		if (image!= null) image.setHeight(height);
	}*/
}
