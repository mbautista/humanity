package fr.ircf.humanity.character;

import fr.ircf.humanity.Game;
import fr.ircf.humanity.GameElement;
import fr.ircf.humanity.action.Job;
import fr.ircf.humanity.component.Image;
import fr.ircf.humanity.component.Text;
import fr.ircf.humanity.game.Dialogue;
import fr.ircf.humanity.quest.Quest;

public abstract class Character implements GameElement {

	protected Game game;
	protected String name, path;
	protected Job job;
	protected Dialogue dialogue;
	protected Image image;
	protected Text text;
	
	@Override
	public void init(Game game) throws Exception {
		this.game = game;
		text = new Text(name);
		image = new Image(path);
	}

	@Override
	public boolean visible() {
		return dialogue != null;
	}

	@Override
	public void render() {
		image.render();
		text.render();
		dialogue.render();
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
	}

	public void giveQuest(Quest quest) {
		// TODO
	}

	public void setDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
	}
}
