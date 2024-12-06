import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
    public int RUNSPEED = 1;
    public static final int GRAVITY = 2;
    public static final int PLAYER_SIZE = 50;

    private GImage playerImage;
    private GRect hitbox; // Add a GRect as the hitbox
    private Skin skin = new Skin();
    private Timer animationTimer;
    private int currentFrame = 1;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    //private boolean isJumpingLeft = false;
    //private boolean isJumpingRight = false;

    public Player(double startX, double startY) {
        // Initialize player image
        playerImage = new GImage("FrogAvatarIdle#1.png");
        double scaleFactor = 1;
        playerImage.setSize(playerImage.getWidth()* scaleFactor, playerImage.getHeight()* scaleFactor);
        playerImage.setLocation(startX - PLAYER_SIZE, startY - PLAYER_SIZE);
        System.out.println(playerImage.getLocation());

        // Initialize the hitbox
        hitbox = new GRect(App.PROG_WIDTH- PLAYER_SIZE, App.PROG_HEIGHT- PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
        hitbox.setFilled(false); // Invisible hitbox for collision
    	Color transparent = new Color(0,0,0,0);// Makes platform transparent
        hitbox.setColor(transparent); // Optional: Debugging aid to see the hitbox
        System.out.println(hitbox.getLocation());
        
        double imageOffsetX = (hitbox.getWidth() - playerImage.getWidth()) / 2;
        double imageOffsetY = (hitbox.getHeight() - playerImage.getHeight()) / 2;
        playerImage.setLocation(startX + imageOffsetX, startY + imageOffsetY);
        
        animationTimer = new Timer(85, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
    }

    public GImage getSkin() {
        return playerImage;
    }

    public GRect getHitbox() {
        return hitbox;
    }

    public void moveLeft() {
        playerImage.move(-RUNSPEED, 0);
        hitbox.move(-RUNSPEED, 0);
        isMovingLeft = true;
        isMovingRight = false;
        animationTimer.start();
    }

    public void moveRight() {
        playerImage.move(RUNSPEED, 0);
        hitbox.move(RUNSPEED, 0);
        isMovingLeft = false;
        isMovingRight = true;
        animationTimer.start();
    }
    public void stopMoving() {
        isMovingLeft = false;
        isMovingRight = false;
        animationTimer.stop();
        playerImage.setImage("media/FrogAvatarIdle#1.png");
    }

    public void move(double dx, double dy) {
        playerImage.move(dx, dy);
        hitbox.move(dx, dy);
    }

    public void setLocation(double x, double y) {
        hitbox.setLocation(x, y);
        // Recalculate the offsets for the image
        double imageOffsetX = (hitbox.getWidth() - playerImage.getWidth()) / 2;
        double imageOffsetY = (hitbox.getHeight() - playerImage.getHeight()) / 2;
        playerImage.setLocation(x + imageOffsetX, y + imageOffsetY);
    }
    
    public void jump(double velocity) {
    	playerImage.move(0, velocity); // Apply velocity for upward or downward motion
    	hitbox.move(0, velocity);     // Ensure hitbox follows the player
    	/* jumping animation(not working)
    	if(isMovingLeft) {
    		isJumpingLeft = true;
    		isMovingLeft = false;
            animationTimer.start()
;

    	}
    	else if(isMovingRight) {
    		isJumpingRight = true;
    		isMovingRight = false;
            animationTimer.start();

    	}
    	*/
    }
    /* More jumping animation(not working)
    public void notJumping() {
    	isJumpingLeft = false;
        isJumpingRight = false;
        animationTimer.stop();
        playerImage.setImage("media/FrogAvatarIdle#1.png");

    }
    */
    private void updateAnimation() {
        if (isMovingLeft) {
            currentFrame = ((currentFrame) % 15) + 1;
            playerImage.setImage("media/FrogAvatarStride#"+currentFrame+"Flipped.png");
        } else if (isMovingRight) {
            currentFrame = ((currentFrame) % 15) + 1;
            playerImage.setImage("media/FrogAvatarStride#"+currentFrame+".png");
        }/* else if(isJumpingLeft) {
        	currentFrame = ((currentFrame) % 8) + 1;
            playerImage.setImage("media/FrogAvatarHop#"+currentFrame+"Flipped.png");
        } else if(isJumpingRight) {
        	currentFrame = ((currentFrame) % 8) + 1;
            playerImage.setImage("media/FrogAvatarHop#"+currentFrame+".png");
        }*/
    }
}