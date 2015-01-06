package fr.ircf.humanity.ui;

import java.util.ArrayList;

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
		for (Component c: components){
			if (viewport != null && viewport.contains(c.getViewport())){
				c.render();
			}
		}
	}
	
	public void add(Component c){
		components.add(c);
		c.setPosition(x + cursorX, y + cursorY);
		if (display == DisplayMode.INLINE){
			cursorX += c.getWidth();
			// TODO CRLF
		}else{
			cursorY += c.getHeight();
		}
	}
	
	public void setPosition(int x, int y){
		moveComponentsTo(x, y); // TODO useless if components position would be relative
		super.setPosition(x, y);
	}
	
	public void moveComponentsBy(int dx, int dy){
		for (Component c: components){
			c.setPosition(c.getX() + dx, c.getY() + dy);
		}
	}
	
	public void moveComponentsTo(int x, int y){
		for (Component c: components){
			c.setPosition(c.getX() - this.x + x, c.getY() - this.y + y);
		}
	}
	
	public int getCursorX(){
		return cursorX;
	}
	
	public int getCursorY(){
		return cursorY;
	}
}
