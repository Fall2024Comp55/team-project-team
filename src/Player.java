import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
    public static final int RUNSPEED = 3;
    public static final int GRAVITY = 3;
    
	private GImage player;
	private Skin skin = new Skin();
	private int posX;
	private int posY;
	private boolean isFacingLeft;
    private int runDirection = 0;
    private int jumpVelocity = 0;
    
    public Player() {
    	player = skin.getSkin();
    	player.move(0, GRAVITY);
    }
    
    public GImage getSkin() {
    	return player;
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
    
    public void move_left() {
    	player.move(-RUNSPEED, 0);
    }
    
    public void move_right() {
    	player.move(RUNSPEED, 0);
    }
    
    public void jump() {
    	player.move(0, -GRAVITY * 2);
    }
}