package fr.ircf.humanity;

import java.util.Locale;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import fr.ircf.humanity.ui.Button;

public class Options extends Menu {

	private static DisplayMode[] DISPLAY_MODES;
	private static Locale[] LOCALES = new Locale[] { Locale.US, Locale.FRANCE };
	private int displayMode = 0, locale = 0, soundVolume = 10, musicVolume = 10,
			galaxySize = 1000, starSize = 6, 
			life = 10, previousSpeed = 40, speed = 40, difficulty = 40;
	private boolean fullScreen = false;
	
	public Options(){
		try{
			DISPLAY_MODES = Display.getAvailableDisplayModes(); // FIXME only keep monitor compatible modes
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		// FIXME buggy screen on full screen disable
		// FIXME menu buttons positions not updated on resolution change
		// FIXME menu texts not updated on locale change
		buttons = new Button[] {
			new Button(game.i18n("options.display") + " : " + Display.getWidth() + " x " + Display.getHeight()) {
				public void up() { displayMode = (displayMode + 1) % DISPLAY_MODES.length; apply(); }
			},
			new Button(game.i18n("options.fullScreen") + " : " + (Display.isFullscreen() ? game.i18n("yes") : game.i18n("no"))) {
				public void up() { fullScreen = !fullScreen; apply(); }
			},
			new Button(game.i18n("options.locale") + " : " + getLocale().getDisplayName()) {
				public void up() { locale = (locale + 1) % LOCALES.length; game.initLocale(); apply(); }
			},
			new Button(game.i18n("options.soundVolume") + " : " + soundVolume + "%") {
				public void up() { soundVolume = (soundVolume + 10) % 110; apply(); }
			},
			new Button(game.i18n("options.musicVolume") + " : " + musicVolume + "%") {
				public void up() { musicVolume = (musicVolume + 10) % 110; apply(); }
			},
			new Button(game.i18n("options.galaxySize") + " : " + galaxySize ) {
				public void up() { galaxySize = 100 + (galaxySize % 2000); apply(); }
			},
			new Button(game.i18n("options.starSize") + " : " + starSize + "/10" ) {
				public void up() { starSize = 1 + (starSize % 10); apply(); }
			},
			new Button(game.i18n("options.life") + " : " + life + "%") {
				public void up() { life = (life + 10) % 110; apply(); }
			},
			new Button(game.i18n("options.speed") + " : " + speed + "%") {
				public void up() { speed = 10 + (speed % 100); apply(); }
			},
			new Button(game.i18n("options.difficulty") + " : " + difficulty + "%") {
				public void up() { difficulty = 10 + (difficulty % 100); apply(); }
			},
			new Button(game.i18n("options.back")) {
				public void up() { game.setState(game.getPreviousState()); }
			},
		};
		int i = 0;
		for (Button button: buttons){
			button.setPosition(
				(Display.getWidth() - button.getWidth())/2,
				(Display.getHeight() - getHeight())/2 + i * (button.getHeight() + DY)
			);
			i++;
		}
	}

	/**
	 * Apply new options
	 */
	public void apply(){
		try{
			if (
				Display.isFullscreen() != fullScreen 
				|| Display.getDisplayMode() != getDisplayMode()
			) game.initDisplay();
			init(game);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public boolean visible() {
		return game.getState() == State.OPTIONS;
	}
	
	public DisplayMode getDisplayMode() {
		return DISPLAY_MODES[displayMode];
	}
	public boolean isFullScreen() {
		return fullScreen;
	}
	public Locale getLocale() {
		return LOCALES[locale];
	}
	public int getSoundVolume() {
		return soundVolume;
	}
	public int getMusicVolume() {
		return musicVolume;
	}
	public int getGalaxySize() {
		return galaxySize;
	}
	public void setSpeed(int speed){
		previousSpeed = this.speed;
		this.speed = speed;
	}
	public void restoreSpeed(){
		speed = previousSpeed;
	}
	public int getSpeed() {
		return speed;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public int getStarSize() {
		return starSize;
	}
	public int getLife() {
		return life;
	}
}
