import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class App extends GraphicsProgram implements KeyListener {
    //public static final int PROGRAM_WIDTH = 500;
    //public static final int PROGRAM_HEIGHT = 500;
    public static final int PLAYER_SIZE = 50;

    private ArrayList<GRect> platforms;
    private GRect player;
    private Timer time;
    private Grid grid; // Reference to the game grid
    private int gravity = 2;
    private int velocity = 0;
    private int jumpPower = 0; // Tracks how long the space key is held
    private final int MAX_JUMP_POWER = 45; // Maximum jump power
    private final int JUMP_INCREMENT = 5; // Power increase per frame
    private boolean isCollidedY = false;
    private boolean isCollidedX = false;
    private boolean aIsPressed = false;
    private boolean dIsPressed = false;
    private boolean spaceIsPressed = false;

    public void run() {
        GImage backGround = new GImage("media/JumpItBackground#1.png");
        backGround.setSize(getWidth(), getHeight());
        add(backGround);
        
        grid = new Grid(25, 25); // Initialize a 10x10 grid
        platforms = new ArrayList<>();
        time = new Timer(10, this);
        time.start();
        createLevel();
        createPlayer();
        addKeyListeners(new MovementKeyListener());
    }

    public void actionPerformed(ActionEvent e) {
    	

    	// Apply gravity and update position
    	player.move(0, velocity);
    	velocity += gravity; // Increase downward velocity over time
    	
    	checkCollision();

    	// Prevent falling through the bottom of the screen
    	if (player.getY() > getHeight() - PLAYER_SIZE) {
    	    player.setLocation(player.getX(), getHeight() - PLAYER_SIZE + 1);
    	    velocity = 0;
    	    isCollidedY = true; // Reset to grounded state
    	} // Check for collisions with platforms

        // Handle horizontal movement
        if (aIsPressed) {
        	if(player.getX() > 0) {
            player.move(-5, 0);
        	}
        }
        if (dIsPressed) {
        	if(player.getX() + PLAYER_SIZE/2 < getWidth() - PLAYER_SIZE)
            player.move(5, 0);
        }
    }

    public void checkCollision() {
        isCollidedY = false; // Reset collision state
        isCollidedX = false;
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCols(); j++) {
                if (grid.getSpace(i, j).hasPlatform()) {
                    Platforms platform = grid.getSpace(i, j).getPlatform();

                 /*   // Debug: Log positions
                    System.out.println("Player: x=" + player.getX() + ", y=" + player.getY() + 
                                       ", width=" + PLAYER_SIZE + ", height=" + PLAYER_SIZE);
                    System.out.println("Platform: x=" + platform.getplatform().getX() + 
                                       ", y=" + platform.getplatform().getY());
                    */
                    //System.out.println(platform.getplatform().getX());
                    //System.out.println(player.getX());
                    
                    //Check for x-axis collisions (left or right of platform)
                    if (player.getBounds().intersects(platform.getplatform().getBounds())) {
                        //platform.getplatform().setColor(java.awt.Color.BLUE); // Debug: successful collision

                        // Check if the player is roughly at the same vertical level as the platform
                        if (player.getY() + PLAYER_SIZE > platform.getplatform().getY()) {

                            if (player.getX()  < platform.getplatform().getX() + PLAYER_SIZE) { // Player hits platform from the left
                                player.setLocation(player.getX() - 10, player.getY());
                                isCollidedX = true;
                              
                            } else if (player.getX() <= platform.getplatform().getX() + platform.getWidth()) { // Player hits platform from the right
                                player.setLocation(player.getX() + 10, player.getY());
                                isCollidedX = true;
                               
                            }
                        }
                    }
                    // Check if the player's bottom edge intersects the platform's top edge

                    if (player.getBounds().intersects(platform.getplatform().getBounds())) {
                        //platform.getplatform().setColor(java.awt.Color.GREEN); // Debug: successful collision
                        
                     if(player.getBounds().getY() > platform.getplatform().getBounds().getY()) { // if player is below collide with bottom
                        player.setLocation(player.getX(), platform.getplatform().getY() + PLAYER_SIZE);
                        velocity = 0;
                        isCollidedY = true;
                        return;
                     }else { // player on top of platform
                        player.setLocation(player.getX(), platform.getplatform().getY() - PLAYER_SIZE);
                        velocity = 0;
                        isCollidedY = true;
                        return;
                     }
                    
                    }
                    
                 
                }
            }
        }
     }

    public void createPlayer() {
        player = new GRect(getWidth() / 2 - PLAYER_SIZE, getHeight()/2 - PLAYER_SIZE , PLAYER_SIZE, PLAYER_SIZE );
        player.setFilled(true);
        player.setColor(java.awt.Color.RED); // Player's color
        //System.out.println(player.getBounds().getHeight());
        //System.out.println(player.getBounds().getWidth());
        //System.out.println(player.getBounds().getX());
        //System.out.println(player.getBounds().getY());



        add(player);
    }

    public void createLevel() {
        // Define platforms in the grid
        grid.setPlatform(15.45, 7, 430, 22); // floor
        grid.setPlatform(8.7, 22.7, 199, 175);// right platform
        grid.setPlatform(8.7, 0, 207, 175);// left platform
        grid.setPlatform(1.8, 11.9, 179, 58);// top platform
        

        // Add graphical representation of platforms
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCols(); j++) {
                if (grid.getSpace(i, j).hasPlatform()) {
                	Platforms platform = grid.getSpace(i, j).getPlatform();
                    add(platform.getplatform());
                }
            }
        }
    }

    public void init() {
    	// Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight() - 50;

        // Set the program width and height to the screen size
        setSize(screenWidth, screenHeight);
    }

    private class MovementKeyListener implements KeyListener {
    	private long jumpStartTime;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                aIsPressed = true;
            }
            if (keyCode == KeyEvent.VK_D && !isCollidedX) {
                dIsPressed = true;
            }
            if (keyCode == KeyEvent.VK_SPACE && isCollidedY && !spaceIsPressed) {
            	spaceIsPressed = true;
            	jumpStartTime = System.currentTimeMillis();
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
            if (keyCode == KeyEvent.VK_SPACE && isCollidedY) {
            	long heldTime = (System.currentTimeMillis() - jumpStartTime)/10;// current time - time the user held space button
            	//System.out.println(heldTime);
            	jumpPower = Math.min((int)heldTime, MAX_JUMP_POWER);// gain the maximum jump if space button was held since it will be min
                velocity = -jumpPower; // Set the initial upward velocity
                jumpPower = 0;
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
