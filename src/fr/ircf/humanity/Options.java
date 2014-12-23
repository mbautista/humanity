package fr.ircf.humanity;

import java.util.Locale;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Options implements GameElement {

	private DisplayMode displayMode;
	private boolean fullScreen = false;
	private Locale locale = Locale.US;
	private int soundVolume = 100, musicVolume = 100, galaxySize = 200, starSize = 10, speed = 2, difficulty = 1;
	private float habitability = 0.1f; // 0 = None, 0.5 = Everywhere
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
	public float getHabitability() {
		return habitability;
	}
	public void setHabitability(float habitability) {
		this.habitability = habitability;
	}
}
