package fr.ircf.humanity;

import java.util.Locale;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Options implements GameElement {

	private DisplayMode displayMode;
	private boolean fullScreen = false;
	private Locale locale = Locale.US;
	private float soundVolume = 100, musicVolume = 100, galaxySize = 200, speed = 1, difficulty = 1;
	private Game game;
	private static int Y = 100;
	private static int DY = 40;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
	}

	@Override
	public boolean visible() {
		return game.getState() == State.OPTIONS;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
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
	public float getSoundVolume() {
		return soundVolume;
	}
	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
	public float getMusicVolume() {
		return musicVolume;
	}
	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}
	public float getGalaxySize() {
		return galaxySize;
	}
	public void setGalaxySize(float galaxySize) {
		this.galaxySize = galaxySize;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}
}
