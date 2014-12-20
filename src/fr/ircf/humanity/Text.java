package fr.ircf.humanity;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Text {

	private static Color COLOR = Color.white;
	private static float SIZE = 12f;
	private static String FONT = "assets/fonts/visitor/visitor1.ttf";
	private static boolean ALIASING = false;
	private String text;
	private Color color;
	private float size;
	private TrueTypeFont font;
	private static HashMap<String, TrueTypeFont> fonts;
	private int x = 0, y = 0;

	public Text(String text) throws Exception {
		this(text, COLOR, SIZE, FONT);
	}
	
	public Text(String text, Color color) throws Exception {
		this(text, color, SIZE, FONT);
	}
	
	public Text(String text, Color color, float size) throws Exception {
		this(text, color, size, FONT);
	}
	
	public Text(String text, Color color, float size, String file) throws Exception {
		this.text = text;
		this.color = color;
		this.size = size;
		initFont(file, size);
	}
	
	public void render(){
		font.drawString(x, y, text, color);
	}
	
	private void initFont(String file, float size) throws Exception {
		if (fonts == null) fonts = new HashMap<String, TrueTypeFont>();
		if (fonts.containsKey(file + size)){
			font = fonts.get(file + size);
		}else{
			InputStream fontStream = ResourceLoader.getResourceAsStream(file);
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			font = new TrueTypeFont(awtFont.deriveFont(size), ALIASING);
			fonts.put(file + size, font);
		}
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getWidth(){
		return font.getWidth(text);	
	}
	
	public int getHeight(){
		return font.getHeight(text);	
	}
}