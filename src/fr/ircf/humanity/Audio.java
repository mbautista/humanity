package fr.ircf.humanity;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class Audio implements GameElement{

	public static String SOUNDS = "../assets/sounds/", MUSICS = "assets/musics/",
			BUTTON_OVER = "button-over.ogg", BUTTON_UP = "button-up.ogg";
	private ArrayList<String> musics;
	private org.newdawn.slick.openal.Audio music, sound;
	private String requestedMusic, requestedSound;
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
		// queue music buffers
		SoundStore.get().poll(0);
	}
	
	private void updateMusic(){
		try {
			if (requestedMusic==null) requestedMusic = randomMusic();
			music = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(MUSICS + requestedMusic));
			music.playAsSoundEffect(1.0f, (float)(game.getOptions().getMusicVolume()/100), false);
			requestedMusic = null;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void updateSound(){
		try {
			sound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(SOUNDS + requestedSound));
			sound.playAsSoundEffect(1.0f, (float)(game.getOptions().getSoundVolume()/100), false);
			requestedSound = null;
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void playMusic(String music){
		requestedMusic = music;
	}
	
	public void playSound(String sound){
		requestedSound = sound;
	}
	
	private String randomMusic(){
		// TODO avoid repeating current music
		Random random = new Random();
		return musics.get(random.nextInt(musics.size()));
	}
}