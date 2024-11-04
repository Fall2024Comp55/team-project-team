import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;
import acm.program.*;

public class App extends GraphicsProgram implements KeyListener {
    public static final int PROGRAM_WIDTH = 500;
    public static final int PROGRAM_HEIGHT = 500;
    public static final int PLAYER_SIZE = 100;
    public static final int MOVEMENT = 3;
    public static final int GRAVITY = 3;

    private ArrayList<GRect> platforms;
    private GOval player;
    private Timer time;
    private Timer spaceTimer = new Timer(1000, this); //creating the timer object and having it count per second
    private int gravity = 2;
    private int movement = 3;
    private int velocity = 0;
    private boolean isCollidedX = false;
    private boolean isCollidedY = false;

    private boolean wIsPressed = false;
    private boolean aIsPressed = false;
    private boolean sIsPressed = false;
    private boolean dIsPressed = false;
    private boolean spaceIsPressed = false;

    public void run() {
        platforms = new ArrayList<GRect>();
        time = new Timer(1, this);
        time.start();
        createPlayer();
        createPlatforms();
        addKeyListeners(new MovementKeyListener());
    }

    public void actionPerformed(ActionEvent e) {

        if (!isCollidedY) {
            checkCollision();
            player.move(0, gravity);
            return;
        }
        checkCollision();
        player.move(0, gravity);
        if (aIsPressed) {
            checkCollision();
            player.move(-movement, 0);
        }
        if (dIsPressed) {
            checkCollision();
            player.move(movement, 0);
        }
        if (spaceIsPressed && isCollidedY) {
        	velocity++;
            checkCollision();
            if (velocity >= 50) {
                player.move(0, -50);
                spaceTimer.stop();
                velocity = 0;
            }
            System.out.println(velocity);
        }   
    }

    public void checkCollision() {
        if (getElementAt(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight()) instanceof GLine || getElementAt(player.getX() + player.getWidth() / 2, player.getY()) instanceof GLine) {
            gravity *= 0;
            isCollidedY = true;
        } else {
            isCollidedY = false;
        }
        if (getElementAt(player.getX(), player.getY() + player.getHeight() / 2) instanceof GLine || getElementAt(player.getX() + player.getWidth(), player.getY() + player.getHeight() / 2) instanceof GLine) {
            movement *= 0;
            isCollidedX = true;
        } else {
            isCollidedX = false;
        }
    }

    public void resetMovement() {
        gravity = 2;
        movement = 3;
    }

    public void createPlayer() {
        player = new GOval(PROGRAM_WIDTH / 2,  300 - PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
        add(player);
    }

    public void createPlatforms() {
        // GRect platform0 = new GRect(0, PROGRAM_HEIGHT - 200, 100, 200);
        // platforms.add(platform0);
        // add(platform0);
        // GRect platform1 = new GRect(0, PROGRAM_HEIGHT, PROGRAM_WIDTH, PROGRAM_HEIGHT);
        // platforms.add(platform1);
        // add(platform1);
        // GRect platform2 = new GRect(PROGRAM_WIDTH, 0, PROGRAM_WIDTH, PROGRAM_HEIGHT);
        // platforms.add(platform2);
        // add(platform2);
        GLine left = new GLine(0, 0, 0, PROGRAM_HEIGHT);
        GLine top = new GLine(0, 0, PROGRAM_WIDTH, 0);
        GLine right = new GLine(PROGRAM_WIDTH, 0, PROGRAM_WIDTH, PROGRAM_HEIGHT);
        GLine bottom = new GLine(0, PROGRAM_HEIGHT, PROGRAM_WIDTH, PROGRAM_HEIGHT);
        add(left);
        add(top);
        add(right);
        add(bottom);
    }

    public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

    private class MovementKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                System.out.println("Key 'A' has been pressed!");
                aIsPressed = true;
            }
            if (keyCode == KeyEvent.VK_D) {
                System.out.println("Key 'D' has been pressed!");
            dIsPressed = true;
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                System.out.println("Key 'Space' has been pressed!");
                spaceIsPressed = true;
                spaceTimer.start();
                
            }
            //System.out.println(velocity);
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                System.out.println("Key 'A' has been released!");
                aIsPressed = false;
                resetMovement();
                player.move(3, 0);
            }
            if (keyCode == KeyEvent.VK_D) {
                System.out.println("Key 'D' has been released!");
                dIsPressed = false;
                resetMovement();
                player.move(-3, 0);
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                System.out.println("Key 'Space' has been released!");
                spaceIsPressed = false;
                resetMovement();
                
            }
        }
    
        @Override
        public void keyTyped(KeyEvent e) {
            // if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            //     System.out.println("Key 'Space' has been clicked");
            //     spaceIsPressed = true;
            // }
        }
    }

    public static void main(String[] args) {
        new App().start();
    }
}
