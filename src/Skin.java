import java.util.ArrayList;
import acm.graphics.*;

public class Skin {
	private GImage skin;

	public void setSkin() {
		skin = new GImage("frog.png", 0, 0);
	}
	
	public GImage getSkin() {
		return skin;
	}
}