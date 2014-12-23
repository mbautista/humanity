package fr.ircf.humanity.tests;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.lwjgl.opengl.*;

public class AwtTest extends Frame{

	private static boolean closeRequested = false;
	private Dialog dialog;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AwtTest a = new AwtTest();
		a.init();
		a.run();
	}
	
	public void init(){
		try{
			// Frame
			Canvas canvas = new Canvas();
		    add(canvas);
	        setMinimumSize(new Dimension(800, 600));
	        setVisible(true);
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e)
	            { closeRequested = true; }
	        });
	        
	        // Dialog
			dialog = new Dialog(this);
			Button button = new Button("hello world");
			dialog.add(button);
			dialog.setUndecorated(true);
			dialog.setOpacity(0.5f);
			dialog.setBounds(100, 100, 200, 200);
			dialog.setVisible(true);
			
			// Display
			Display.setDisplayMode(new DisplayMode(800, 600));
			//Display.setFullscreen(true);
			Display.setVSyncEnabled(true);
			Display.setTitle("Awt Test");
			Display.setParent(canvas);
			Display.create();
			
			// Open GL
			// FIXME Required for font rendering, but disables other graphics ?!
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void run(){
		while(!Display.isCloseRequested() && !closeRequested){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			// TODO display something
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
			GL11.glRecti(50, 50, 750, 550);
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		dispose();
	}
}