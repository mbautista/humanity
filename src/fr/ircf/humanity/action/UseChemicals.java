package fr.ircf.humanity.action;


public class UseChemicals extends Action {

	public UseChemicals(){
		job = Job.FARMERS;
		name = "useChemicals";
		icon = "customs.jpg";
		needsPeople = true;
	}
	
	public void update(double delta){
		// TODO level
		// TODO people
	}
}
