package fr.ircf.humanity.ui;

import java.util.ArrayList;

public class Panel extends Component {

	private ArrayList<Component> components;
	
	@Override
	public void render() {
		for (Component component: components){
			component.render();
		}
	}
	
	public void add(Component c){
		components.add(c);
	}
}
