package fr.ircf.humanity.ui;

import java.awt.Font;

import org.lwjgl.opengl.*;
import org.newdawn.slick.TrueTypeFont;

public class SlickTest{
	
	private TrueTypeFont font;
	
	public void init(){
		try{
			// Display
			Display.setDisplayMode(new DisplayMode(800, 600));
			//Display.setFullscreen(true);
			Display.setVSyncEnabled(true);
			Display.setTitle("Slick Test");
			Display.create();
			
			// Open GL
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			
			// Font
		    Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		    font = new TrueTypeFont(awtFont, true);
			
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void run(){
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			// FIXME alpha does not work !
			GL11.glColor4f(1.0f, 0.0f, 0.0f, 0.5f);
			GL11.glRecti(50, 50, 750, 550);

	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			font.drawString(300, 300, "hello world");
	        GL11.glDisable(GL11.GL_BLEND);
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SlickTest a = new SlickTest();
		a.init();
		a.run();
	}
}