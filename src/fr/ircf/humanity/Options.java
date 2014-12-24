package fr.ircf.humanity;

import java.util.Locale;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Options extends Menu {

	private DisplayMode displayMode;
	private boolean fullScreen = false;
	private Locale locale = Locale.US;
	private int soundVolume = 100, musicVolume = 100, galaxySize = 2000, starSize = 6, speed = 2, difficulty = 1;
	private float life = 0.1f; // 0 = None, 1 = Everywhere
	
	@Override
	public void init(final Game game) throws Exception {
		this.game = game;
		// TODO option dialog with input field
		buttons = new Button[] {
			new Button(game.i18n("options.display") + " : " + Display.getWidth() + " x " + Display.getHeight()) {
				public void click() {}
			},
			new Button(game.i18n("options.fullScreen") + " : " + (Display.isFullscreen() ? game.i18n("yes") : game.i18n("no"))) {
				public void click() {}
			},
			new Button(game.i18n("options.locale") + " : " + locale.getDisplayName()) {
				public void click() {}
			},
			new Button(game.i18n("options.sound") + " : " + soundVolume + "%") {
				public void click() {}
			},
			new Button(game.i18n("options.musicVolume") + " : " + musicVolume + "%") {
				public void click() {}
			},
			new Button(game.i18n("options.galaxySize") + " : " + galaxySize ) {
				public void click() {}
			},
			new Button(game.i18n("options.starSize") + " : " + starSize ) {
				public void click() {}
			},
			new Button(game.i18n("options.life") + " : " + life ) {
				public void click() {}
			},
			new Button(game.i18n("options.speed") + " : " + speed + "/10") {
				public void click() {}
			},
			new Button(game.i18n("options.difficulty") + " : " + difficulty + "/10") {
				public void click() {}
			},
			new Button(game.i18n("options.back")) {
				public void click() { game.setState(State.MENU); }
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

	@Override
	public boolean visible() {
		return game.getState() == State.OPTIONS;
	}
	
	public DisplayMode getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}
	public boolean isFullScreen() {
		return fullScreen;
	}
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public int getSoundVolume() {
		return soundVolume;
	}
	public void setSoundVolume(int soundVolume) {
		this.soundVolume = soundVolume;
	}
	public int getMusicVolume() {
		return musicVolume;
	}
	public void setMusicVolume(int musicVolume) {
		this.musicVolume = musicVolume;
	}
	public int getGalaxySize() {
		return galaxySize;
	}
	public void setGalaxySize(int galaxySize) {
		this.galaxySize = galaxySize;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getStarSize() {
		return starSize;
	}
	public void setStarSize(int starSize) {
		this.starSize = starSize;
	}
	public float getLife() {
		return life;
	}
	public void setLife(float life) {
		this.life = life;
	}
}
