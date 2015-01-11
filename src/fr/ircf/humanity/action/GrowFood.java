package fr.ircf.humanity.action;

public class GrowFood extends Action {

	public GrowFood(){
		job = Job.FARMERS;
		name = "growFood";
		icon = "animals.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
