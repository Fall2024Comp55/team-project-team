import acm.graphics.*;
import acm.program.*;

public class Test extends GraphicsProgram {
	public static final int PROGRAM_WIDTH = 500;
    public static final int PROGRAM_HEIGHT = 500;
    
    private Player player;

    public void run() {
    	player = new Player();
        add(player.getSkin());
    }
    public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}
    public static void main(String[] args) {
        new Test().start();
    }
}
