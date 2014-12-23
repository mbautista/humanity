package fr.ircf.humanity.tests;

import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public class SphereTest{
	
	private float zTranslation = 0.1f;
	
	public static void main(String[] args) {
		SphereTest test = new SphereTest();
		test.init();
	    test.run();
	}

	private void init() {
	    try {
	    	 Display.setDisplayMode(new DisplayMode(800, 600));
	         Display.setTitle("Sphere Test");
	         Display.create();
	         
		     //GL11.glClearDepth(1.0f); // clear depth buffer
		     //GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables depth testing
		     //GL11.glDepthFunc(GL11.GL_LEQUAL); // sets the type of test to use for depth
		     // testing
		     GL11.glMatrixMode(GL11.GL_PROJECTION); // sets the matrix mode to project
		     float aspect = Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight();
		     GLU.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
		     GL11.glMatrixMode(GL11.GL_MODELVIEW);
		     //GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	         
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
	}
	
	private void run() {
		while(!Display.isCloseRequested()){ 
			render();
			Display.sync(60);
			Display.update();
		}
		Display.destroy();
	}

	private void render() {
	     GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);// | GL11.GL_DEPTH_BUFFER_BIT);
	     //GL11.glLoadIdentity();
	     //GL11.glTranslatef(0.0f, 0.0f, zTranslation);
	     renderSphere(-2f, -0.5f, -1f);
	     renderSphere(-1f, -0.5f, -2f);
	     renderSphere(-0f, -0.5f, -3f);
	     renderSphere(1f, -0.5f, -4f);
	     renderSphere(2f, -0.5f, -5f);
	}

	private void renderSphere(float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
	    Sphere s = new Sphere();
	    s.draw(0.4f, 16, 16);
	    GL11.glPopMatrix();
	}
}