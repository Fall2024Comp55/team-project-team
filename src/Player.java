import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
    public static final int RUNSPEED = 3;
    public static final int GRAVITY = 3;
    
	private GOval player;
	private Skin skin = new Skin();
	private int posX;
	private int posY;
	private boolean isFacingLeft;
    private int runDirection = 0;
    private int jumpVelocity = 0;
    
    public Player() {
    	//player = skin.getSkin();
    	//player.move(0, GRAVITY);
    	//for now while we do not have skins yet
    	player = new GOval(0, 0, 50, 50); // Placeholder rectangle, 50x50
        player.setFilled(true);
        player.setColor(java.awt.Color.BLUE); // Use a distinctive color
    }
    
    public GOval getSkin() {
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
    
    public void setRow(int row) {
        this.posX = row;
    }

    public void setCol(int col) {
        this.posY = col;
    }
}