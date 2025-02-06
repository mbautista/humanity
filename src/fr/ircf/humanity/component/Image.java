package fr.ircf.humanity.component;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Image extends Component {

	protected String path;
	protected Texture texture;
	protected int padding = 2;
	
	public Image(String path){
		super();
		setPath(path);
	}
	
	public void render(){
		if (visible() && texture != null){
			Color.white.bind();
			texture.bind(); // or GL11.glBind(texture.getTextureID());
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2f(x + padding, y + padding);
				GL11.glTexCoord2f(1, 0);
				GL11.glVertex2f(x + width + padding, y + padding);
				GL11.glTexCoord2f(1, 1);
				GL11.glVertex2f(x + width + padding, y + height + padding);
				GL11.glTexCoord2f(0, 1);
				GL11.glVertex2f(x + padding, y + height + padding);
			GL11.glEnd();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 1);
		}
	}

	public void update(double delta) {
		super.update(delta);
	}
	
	public void setPath(String path) {
		if (this.path != path){
			this.path = path;
			try{
				texture = TextureLoader.getTexture("JPG",
					ResourceLoader.getResourceAsStream("assets/images/" + path)
				);
			}catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public int getWidth(){
		return width + 2 * padding;
	}
	
	
	public int getHeight(){
		return height + 2 * padding;
	}
}
