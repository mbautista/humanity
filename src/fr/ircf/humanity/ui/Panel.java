package fr.ircf.humanity.ui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

public class Panel extends Component {

	private ArrayList<Component> components;
	public enum DisplayMode { INLINE, BLOCK };
	private DisplayMode display = DisplayMode.BLOCK;
	private int cursorX = 0, cursorY = 0;
	
	public Panel(){
		components = new ArrayList<Component>();
	}
	
	@Override
	public void render() {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		for (Component c: components){
			c.render();
		}
		GL11.glPopMatrix();
	}
	
	public void add(Component c){
		components.add(c);
		c.setPosition(cursorX, cursorY);
		if (display == DisplayMode.INLINE){
			cursorX += c.getWidth();
			// TODO CRLF
		}else{
			cursorY += c.getHeight();
		}
		width = Math.max(c.getWidth(), cursorX);
		height = Math.max(c.getHeight(), cursorY);
	}
	
	public int getCursorX(){
		return cursorX;
	}
	
	public int getCursorY(){
		return cursorY;
	}
}
