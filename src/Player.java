import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
	private GImage player;
	private Skin skin;
	private int posX;
	private int posY;
	private boolean isFacingLeft;
	private int gravity = 2;
    private int runSpeed = 3;
    private int runDirection = 0;
    private int jumpVelocity = 0;
    
    public Player() {
    	
    }
    public int getPosX() {
    	return posX;
    }
    
    public int getPosY() {
    	return posY;
    }
    
    public void move(int velocity) {
    	player.move(runDirection, 0);
    }
    
    public void jump(int velocity) {
    	player.move(runDirection, jumpVelocity);
    }
}
