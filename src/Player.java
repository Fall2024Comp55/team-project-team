import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
    public static final int RUNSPEED = 3;
    public static final int GRAVITY = 3;
    
	private GImage player;
	private Skin skin;
	private int posX;
	private int posY;
	private boolean isFacingLeft;
    private int runDirection = 0;
    private int jumpVelocity = 0;
    
    public Player() {
    	skin = new Skin();
    }
    
    public int getPosX() {
    	return posX;
    }
    
    public int getPosY() {
    	return posY;
    }
    
    public int setRunDirection(int speed) {
    	return runDirection + speed;
    }
    
    public int setJumpVelocity(int speed) {
    	return jumpVelocity + speed;
    }
    
    public void move(int velocity) {
    	runDirection = RUNSPEED;
    	if (isFacingLeft) {
    		player.move(-runDirection, 0);
    	} else {
    		player.move(runDirection, 0);
    	}
    }
    
    public void jump(int velocity) {
    	player.move(runDirection, jumpVelocity);
    }
}
