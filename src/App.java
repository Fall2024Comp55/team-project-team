import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;

public class App extends GraphicsProgram implements KeyListener {
    public static final int PROGRAM_WIDTH = 500;
    public static final int PROGRAM_HEIGHT = 500;
    public static final int PLAYER_SIZE = 50;

    private ArrayList<GRect> platforms;
    private GOval player;
    private Timer time;
    private Grid grid; // Reference to the game grid
    private int gravity = 2;
    private int velocity = 0;
    private int jumpPower = 10; // Tracks how long the space key is held
    private final int MAX_JUMP_POWER = 30; // Maximum jump power
    private final int JUMP_INCREMENT = 5; // Power increase per frame
    private boolean isCollidedY = false;

    private boolean aIsPressed = false;
    private boolean dIsPressed = false;
    private boolean spaceIsPressed = false;

    public void run() {
        grid = new Grid(10, 10); // Initialize a 10x10 grid
        platforms = new ArrayList<>();
        time = new Timer(10, this);
        time.start();
        createPlayer();
        createLevel();
        addKeyListeners(new MovementKeyListener());
    }

    public void actionPerformed(ActionEvent e) {
    	

    	// Apply gravity and update position
    	player.move(0, velocity);
    	velocity += gravity; // Increase downward velocity over time
    	
    	checkCollision();

    	// Prevent falling through the bottom of the screen
    	if (player.getY() > PROGRAM_HEIGHT - PLAYER_SIZE) {
    	    player.setLocation(player.getX(), PROGRAM_HEIGHT - PLAYER_SIZE);
    	    velocity = 0;
    	    isCollidedY = true; // Reset to grounded state
    	} // Check for collisions with platforms

        // Handle horizontal movement
        if (aIsPressed) {
            player.move(-3, 0);
        }
        if (dIsPressed) {
            player.move(3, 0);
        }
    }

    public void checkCollision() {
        isCollidedY = false; // Reset collision state

        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCols(); j++) {
                if (grid.getSpace(i, j).hasPlatform()) {
                    Platforms platform = grid.getSpace(i, j).getPlatform();
/*
                    // Debug: Log positions
                    System.out.println("Player: x=" + player.getX() + ", y=" + player.getY() + 
                                       ", width=" + PLAYER_SIZE + ", height=" + PLAYER_SIZE);
                    System.out.println("Platform: x=" + platform.getplatform().getX() + 
                                       ", y=" + platform.getplatform().getY() + 
                                       ", width=" + platform.getplatform().getWidth() + 
                                       ", height=" + platform.getplatform().getHeight());
*/
                    // Check if the player's bottom edge intersects the platform's top edge
                    if (player.getBounds().intersects(platform.getplatform().getBounds())) {
                        platform.getplatform().setColor(java.awt.Color.GREEN); // Debug: successful collision
                        player.setLocation(player.getX(), platform.getplatform().getY() - PLAYER_SIZE);
                        velocity = 0;
                        isCollidedY = true;
                        return;
                    }
                }
            }
        }
      }

    public void createPlayer() {
        player = new GOval(PROGRAM_WIDTH / 2, PROGRAM_HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
        player.setFilled(true);
        player.setColor(java.awt.Color.RED); // Player's color
        add(player);
    }

    public void createLevel() {
        // Define platforms in the grid
        grid.setPlatform(7, 2, 100, 20);
        grid.setPlatform(5, 4, 100, 20);
        grid.setPlatform(3, 6, 100, 20);
        grid.setPlatform(1, 8, 100, 20); // Topmost platform

        // Add graphical representation of platforms
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCols(); j++) {
                if (grid.getSpace(i, j).hasPlatform()) {
                    Platforms platform = grid.getSpace(i, j).getPlatform();
                    add(platform.getplatform());
                }
            }
        }
        remove(player); // Remove the player
        add(player);    // Re-add the player to the canvas
    }

    public void init() {
        setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
    }

    private class MovementKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                aIsPressed = true;
            }
            if (keyCode == KeyEvent.VK_D) {
                dIsPressed = true;
            }
            if (keyCode == KeyEvent.VK_SPACE && isCollidedY) {
                spaceIsPressed = true;
                if (jumpPower < MAX_JUMP_POWER) {
    	            jumpPower += JUMP_INCREMENT; // Gradually increase jump power
    	            System.out.println(jumpPower);
    	        }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                aIsPressed = false;
            }
            if (keyCode == KeyEvent.VK_D) {
                dIsPressed = false;
            }
            if (keyCode == KeyEvent.VK_SPACE) {
	            System.out.println("released");
	            velocity = -jumpPower; // Use jumpPower as initial upward velocity
                jumpPower = 10; // Reset jump power when space is released
                spaceIsPressed = false;
            }

        }
        @Override
        public void keyTyped(KeyEvent e) {
            // Unused
        }
    }

    public static void main(String[] args) {
        new App().start();
    }
}
