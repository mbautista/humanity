package fr.ircf.humanity;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class Audio implements GameElement{

	public static String SOUNDS = "assets/sounds/", MUSICS = "assets/musics/",
			CLICK = "click.ogg", STOP = "stop.ogg";
	private ArrayList<String> musics;
	private org.newdawn.slick.openal.Audio music, sound;
	private static String requestedMusic, requestedSound;
	private Game game;
	
	public void init(Game game) throws Exception {
		this.game = game;
		initMusics();
	}

	private void initMusics() throws Exception {
		musics = new ArrayList<String>();
		File[] files = new File(MUSICS).listFiles();
		for (File file : files) {
		    if (file.isFile() && file.getName().endsWith(".ogg")) {
		    	musics.add(file.getName());
		    }
		}
	}
	
	@Override
	public boolean visible() {
		return true;
	}

	@Override
	public void render() {
		// nothing to render
	}

	@Override
	public void update(double delta) {
		if (requestedMusic!=null || music==null || !music.isPlaying()){
			updateMusic();
		}
		if (requestedSound!=null){
			updateSound();
		}
		// update music volume
		SoundStore.get().setCurrentMusicVolume(getMusicVolume());
		// queue music buffers
		SoundStore.get().poll(0);
	}
	
	private void updateMusic(){
		try {
			if (requestedMusic==null) requestedMusic = randomMusic();
			music = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(MUSICS + requestedMusic));
			music.playAsMusic(1.0f, getMusicVolume(), false);
			requestedMusic = null;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void updateSound(){
		try {
			sound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(SOUNDS + requestedSound));
			sound.playAsSoundEffect(1.0f, getSoundVolume(), false);
			requestedSound = null;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void playMusic(String music){
		requestedMusic = music;
	}
	
	public static void playSound(String sound){
		requestedSound = sound;
	}
	
	private String randomMusic(){
		// TODO avoid repeating current music
		Random random = new Random();
		return musics.get(random.nextInt(musics.size()));
	}
	
	/**
	 *  Volume helpers
	 */
	public float getMusicVolume(){
		return game.getOptions().getMusicVolume()/100.0f;
	}
	
	public float getSoundVolume(){
		return game.getOptions().getSoundVolume()/100.0f;
	}
}