package fr.ircf.humanity.ui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ButtonIcon extends Button {

	protected String icon;
	protected Texture texture;
	protected int padding = 2;
	
	public ButtonIcon(){
		super();
	}
	
	public ButtonIcon(String icon){
		super();
		setIcon(icon);
	}
	
	public void render(){
		if (visible() && texture!=null){
			super.render();
			// TODO image.render();
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
		}
	}

	public void update(double delta) {
		super.update(delta);
	}
	
	public void setIcon(String icon) {
		if (this.icon != icon){
			this.icon = icon;
			try{
				texture = TextureLoader.getTexture("JPG",
					ResourceLoader.getResourceAsStream("assets/icons/" + icon)
				);
				width = texture.getTextureWidth();
				height = texture.getTextureHeight();
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
