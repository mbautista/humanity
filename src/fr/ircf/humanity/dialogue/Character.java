package fr.ircf.humanity.dialogue;

import fr.ircf.humanity.action.Job;

public enum Character {

	COUNSELOR("counselor", "characters/counselor1.jpeg", Job.HUMANITY),
	FARMER("farmer", "characters/farmer1.jpeg", Job.FARMERS);

	private String name, path;
	private Job job;
	
	Character(String name, String path, Job job) {
		this.name = name;
		this.path = path;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public Job getJob() {
		return job;
	}

}
