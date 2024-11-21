import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
    public static final int RUNSPEED = 3;
    public static final int GRAVITY = 3;
    
    private GImage playerImage;
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
        
    playerImage = new GImage("FrogAvatarDamage#1.png");
    //playerImage = new GImage("PushPlatform#1.png");
    
         
    // Optionally, scale the image if zooming is required
    double scaleFactor = 5; // Change this value for different zoom levels, but don't go over 5
    //double scaleFactor = 2; //Recommended scale factor for push platform ?
    playerImage.setSize(playerImage.getWidth() * scaleFactor, playerImage.getHeight() * scaleFactor);
            
    // Set default position
    playerImage.setLocation(0, 0); 
    }
    
    public GImage getSkin() {
        return playerImage;
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }
    
    public void setRunDirection(int speed) {
        runDirection = speed;
    }
    
    public void setJumpVelocity(int speed) {
        jumpVelocity = speed;
    }
    
    public void moveLeft() {
        playerImage.move(-RUNSPEED, 0);
    }
    
    public void moveRight() {
        playerImage.move(RUNSPEED, 0);
    }
    
    public void jump() {
        playerImage.move(0, -GRAVITY * 2);
    }
    
    public void setRow(int row) {
        this.posX = row;
    }

    public void setCol(int col) {
        this.posY = col;
    }
}