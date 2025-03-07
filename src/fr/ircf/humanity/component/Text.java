package fr.ircf.humanity.component;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Text extends Component {

	private static Color COLOR = Color.white;
	private static float SIZE = 14f;
	private static String FONT = "assets/fonts/visitor/visitor1.ttf";
	private static boolean ALIASING = true;
	private String text;
	private String[] lines;
	private Color color;
	private float size;
	private TrueTypeFont font;
	private static HashMap<String, TrueTypeFont> fonts;

	public Text(){
		this(null, COLOR, SIZE, FONT);
	}
	
	public Text(String text) {
		this(text, COLOR, SIZE, FONT);
	}
	
	public Text(String text, float[] color){
		this(text, new Color(color[0], color[1], color[2]), SIZE, FONT);
	}
	
	public Text(String text, Color color) {
		this(text, color, SIZE, FONT);
	}
	
	public Text(String text, Color color, float size) {
		this(text, color, size, FONT);
	}
	
	public Text(String text, Color color, float size, String file) {
		setText(text);
		this.color = color;
		this.size = size;
		initFont(file, size);
	}
	
	public void render(){
		if (isEmpty()) return;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        int index = 0;
        for (String line: lines) {
        	font.drawString(x, y + index * font.getLineHeight(), line, color);
        	index++;
        }
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void initFont(String file, float size) {
		try{
			if (fonts == null) fonts = new HashMap<String, TrueTypeFont>();
			if (fonts.containsKey(file + size)){
				font = fonts.get(file + size);
			}else{
				InputStream fontStream = ResourceLoader.getResourceAsStream(file);
				Font awtFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
				font = new TrueTypeFont(awtFont.deriveFont(size), ALIASING);
				fonts.put(file + size, font);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public int getWidth(){
		return font.getWidth(text);	
	}
	
	public int getHeight(){
		return font.getHeight(text);	
	}
	
	public void setText(String text){
		this.text = text;
		lines = text == null ? new String[] {} : text.split("\\r?\\n");
	}

	public boolean isEmpty() {
		return text == null;
	}
	
	public void setColor(float[] color){
		this.color = new Color(color[0], color[1], color[2]);
	}
}