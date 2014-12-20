package fr.ircf.humanity;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Title implements GameElement {

	private static String TITLE_FONT = "assets/fonts/hyperspace/Hyperspace.ttf";
	private static String VERSION_FONT = "assets/fonts/visitor/visitor1.ttf";
	private static float TITLE_SIZE = 48f, VERSION_SIZE = 12f;
	private static boolean ALIASING = false;
	private static Color COLOR = Color.white;
	private static int TITLE_Y = 0;
	private TrueTypeFont titleFont, versionFont;

	public void init() throws Exception {
		titleFont = loadFont(TITLE_FONT, TITLE_SIZE);
		versionFont = loadFont(VERSION_FONT, VERSION_SIZE);
	}
	
	public boolean visible(State state){
		return state != State.GAME;
	}
	
	public void render(){
		float titleX = (float)(Display.getDisplayMode().getWidth() - titleFont.getWidth(Humanity.TITLE))/2;
		String versionText = "v" + Humanity.VERSION;
		float versionX = titleX + titleFont.getWidth(Humanity.TITLE);
		float versionY = TITLE_Y + titleFont.getHeight(Humanity.TITLE) - versionFont.getHeight(versionText) - VERSION_SIZE/2;
		titleFont.drawString(titleX, TITLE_Y, Humanity.TITLE, COLOR);
		versionFont.drawString(versionX, versionY, versionText, COLOR);
	}

	public void update(double delta){
		
	}
	
	private TrueTypeFont loadFont(String file, float size) throws Exception {
		InputStream fontStream = ResourceLoader.getResourceAsStream(file);
		Font awtFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
		return new TrueTypeFont(awtFont.deriveFont(size), ALIASING);
	}
}