package fr.ircf.humanity.character;

import java.util.ArrayList;
import java.util.List;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.action.Job;
import fr.ircf.humanity.component.Image;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.dialogue.Dialogue;
import fr.ircf.humanity.quest.Quest;

public abstract class Character implements GameElement {

	protected Game game;
	protected String name, path;
	protected Job job;
	protected List<Dialogue> dialogues;
	protected List<Dialogue> currentDialogues;
	protected Image image;
	protected Text text;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		text = new Text(name);
		image = new Image(path);
		currentDialogues = new ArrayList<>();
		dialogues = new ArrayList<>();
	}

	@Override
	public boolean visible() {
		return !currentDialogues.isEmpty();
	}

	@Override
	public void render() {
		image.render();
		text.render();
		currentDialogues.getFirst().render();
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
	}

	public void giveQuest(Quest quest) {
		// TODO
	}
}
