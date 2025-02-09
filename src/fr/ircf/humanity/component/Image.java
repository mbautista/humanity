package fr.ircf.humanity.component;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Image extends Component {

	protected String path;
	protected Texture texture;

	public Image(){
	}
	
	public Image(String path){
		setPath(path);
	}
	
	public void render(){
		if (visible() && texture != null){
			Color.white.bind();
			texture.bind(); // or GL11.glBind(texture.getTextureID());
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2f(x, y);
				GL11.glTexCoord2f(1, 0);
				GL11.glVertex2f(x + width, y);
				GL11.glTexCoord2f(1, 1);
				GL11.glVertex2f(x + width, y + height);
				GL11.glTexCoord2f(0, 1);
				GL11.glVertex2f(x, y + height);
			GL11.glEnd();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 1);
		}
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
}
