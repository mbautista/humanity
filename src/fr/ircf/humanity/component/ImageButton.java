package fr.ircf.humanity.component;

public class ImageButton extends Button {

	protected Image image;
	protected int padding = 2;
	
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
			//System.out.println("render image : x = "+image.getX()+", y="+image.getY()+",width="+image.getWidth()+", height="+image.getHeight());
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(String path) {
		image = new Image(path);
		image.setWidth(width - 2*padding);
		image.setHeight(height - 2*padding);
	}

	public void setPosition(int x, int y){
		super.setPosition(x,y);
		if (image!= null) image.setPosition(x + padding, y + padding);
	}
	public void setPosition(double x, double y){
		super.setPosition(x,y);
		if (image!= null) image.setPosition(x + padding, y + padding);
	}
	public void setX(int x) {
		super.setX(x);
		if (image!= null) image.setX(x + padding);
	}
	public void setY(int y) {
		super.setY(y);
		if (image!= null) image.setY(y + padding);
	}
	public void setWidth(int width) {
		super.setWidth(width);
		if (image!= null) image.setWidth(width - 2*padding);
	}
	public void setHeight(int height) {
		super.setHeight(height);
		if (image!= null) image.setHeight(height - 2*padding);
	}
}
