package fr.ircf.humanity;

public class Slider extends Component {
	
	private static float[] COLOR_DEFAULT = {1f, 1f, 1f, 0.5f};
	private static float[] COLOR_BACK = {0.2f, 0.2f, 0.2f, 0.5f};
	private int value, max;
	private Button button;
	
	public void render(){
		// TODO
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
}
