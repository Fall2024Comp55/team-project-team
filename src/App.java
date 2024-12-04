import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class App extends GraphicsProgram implements KeyListener {
    public static final double PROGRAM_WIDTH = 1550.0;
    public static final double PROGRAM_HEIGHT = 851.0;
    public static final int PLAYER_SIZE = 50;

    private ArrayList<GRect> platforms;
    private Player player;
    private Timer time;
    private Grid grid; // Reference to the game grid
    private int gravity = 2;
    private int velocity = 0;
    private int jumpPower = 0; // Tracks how long the space key is held
    private final int MAX_JUMP_POWER = 40; // Maximum jump power
    private final int JUMP_INCREMENT = 5; // Power increase per frame
    private boolean isCollidedY = false;
    private boolean isCollidedX = false;
    private boolean aIsPressed = false;
    private boolean dIsPressed = false;
    private boolean spaceIsPressed = false;
    private String currentBackground = "media/JumpItBackground#1.png";
    private String previousBackground = "media/JumpItBackground#1.png";
    private int backgroundNumber = 1;
    private int lives = 3; // Default number of lives
    private ArrayList<GImage> lifeIcons = new ArrayList<>();
    private int jumpCounter = 20; 
    private ArrayList<GLabel> jumpCounterLabels = new ArrayList<>(); 
    private boolean fellDown = false;

    public void run() {
        GImage backGround = new GImage(currentBackground);
        
        backGround.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        //System.out.println("Static width"+getWidth());
        //System.out.println("Static height" + getHeight());

        add(backGround);
        
        grid = new Grid(25, 100); // Initialize a 10x10 grid
        platforms = new ArrayList<>();
        time = new Timer(10, this);
        time.start();
        createLevel(backgroundNumber);
        createPlayer();
        displayLives();
        updateJumpCounterDisplay();
        addKeyListeners(new MovementKeyListener());
    }

    public void actionPerformed(ActionEvent e) {
    	   if (player == null || player.getSkin() == null) {
    	        return; // Skip update if the player is not initialized
    	    }

    	    // Apply gravity and update position
    	    player.move(0, velocity);
    	    velocity += gravity;

    	    checkCollision();
    	    
    	    if (jumpCounter <= 0) {
    	        loseLife();
    	    }
    	    
    	    // Prevent falling through the bottom of the screen
    	    if (player.getHitbox().getY() > getHeight() - PLAYER_SIZE) {
    	        player.setLocation(player.getHitbox().getX(), getHeight() - PLAYER_SIZE + 1);
    	        velocity = 0;
    	        isCollidedY = true; // Reset to grounded state
    	        System.out.println("Player Y pos" + player.getHitbox().getY());
    	        System.out.println("Height" + getHeight());
    	        loseLife(); //Disable this when testing 
    	    }
    	    
    	    // Handle horizontal movement
    	    if (aIsPressed) {
    	        if (player.getHitbox().getX() > 0) {
    	            player.moveLeft();
    	        }
    	    }
    	    if (dIsPressed) {
    	        if (player.getHitbox().getX() + PLAYER_SIZE / 2 < getWidth() - PLAYER_SIZE) {
    	            player.moveRight();
    	        }
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
                    if (player.getHitbox().getBounds().intersects(platform.getplatform().getBounds())) {
                        //platform.getplatform().setColor(java.awt.Color.BLUE); // Debug: successful collision

                        // Check if the player is roughly at the same vertical level as the platform
                        if (player.getHitbox().getY() + PLAYER_SIZE > platform.getplatform().getY()) {

                            if (player.getHitbox().getX()  < platform.getplatform().getX() + PLAYER_SIZE) { // Player hits platform from the left
                                player.setLocation(player.getHitbox().getX() - 10, player.getHitbox().getY());
                                isCollidedX = true;
                              
                            } else if (player.getHitbox().getX() <= platform.getplatform().getX() + platform.getWidth()) { // Player hits platform from the right
                                player.setLocation(player.getHitbox().getX() + 10, player.getHitbox().getY());
                                isCollidedX = true;
                               
                            }
                        }
                    }
                    // Check if the player's bottom edge intersects the platform's top edge

                    if (player.getHitbox().getBounds().intersects(platform.getplatform().getBounds())) {
                        //platform.getplatform().setColor(java.awt.Color.GREEN); // Debug: successful collision
                     if(player.getHitbox().getBounds().getY() > platform.getplatform().getBounds().getY()) { // if player is below collide with bottom
                        player.setLocation(player.getHitbox().getX(), platform.getplatform().getY() + PLAYER_SIZE);
                        velocity = 0;
                        isCollidedY = true;
                        return;
                     }else { // player on top of platform
                        player.setLocation(player.getHitbox().getX(), platform.getplatform().getY() - PLAYER_SIZE);
                        velocity = 0;
                        isCollidedY = true;
                        fellDown = false;
                        return;
                     }
                    
                    }
                    
                 
                }
            }
        }
     }

    public void createPlayer() {
    	 player = new Player(App.PROG_WIDTH, App.PROG_HEIGHT);
    	 add(player.getSkin()); // Add the visual representation
    	 add(player.getHitbox()); // Add the hitbox (invisible)
    	 
    }

    public void createLevel(int num) {
        // Define platforms in the grid
    	if(num == 1) {
        grid.setPlatform(15.45, 7, 430, 22); // floor
        grid.setPlatform(8.7, 22.7, 199, 175);// right platform
        grid.setPlatform(8.7, 0, 207, 175);// left platform
        grid.setPlatform(1.8, 11.9, 179, 58);// top platform
    	}
    	else if(num == 2) {
    		grid.setPlatform(3.8, 0, 125, 120);// left platform
    		grid.setPlatform(5, 7.6, 120, 80);// left platform
    		grid.setPlatform(9.5, 16.4, 120, 30);// top platform
    		grid.setPlatform(9.4, 26.5, 100, 40);// right platform
    		grid.setPlatform(14, 19, 158, 40);
    		
    	}
        

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
    
    public void mapTransition() {
    	int curr = backgroundNumber + 1;
    	previousBackground = "media/JumpItBackground#"+backgroundNumber+".png";
    	currentBackground = "media/JumpItBackground#"+curr+".png";
        
         if (player.getHitbox().getY() < 50) {
            // Jumping out of the top of the screen
            System.out.println(curr);
            System.out.println(currentBackground);
            backgroundNumber += 1;
            loadMap(currentBackground);
            
        }
       
    }
    
    public void resetGrid() {
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCols(); j++) {
                grid.getSpace(i, j).clearPlatform(); // Assuming `clearPlatform()` removes any platform in the space
            }
        }
    }

    private void loadMap(String backgroundPath) {
        removeAll(); // Clear the screen

        // Set the new background
        GImage background = new GImage(backgroundPath);
        background.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        add(background);

        // Redraw platforms and player
        //System.out.println(backgroundNumber);
        //System.out.println(player.getHitbox().getY());
        resetGrid(); // Clear existing platforms from the gridgrid = new Grid(25, 100); // Initialize a 10x10 grid
        createLevel(backgroundNumber); // Add platforms for the new map
        createPlayer();
        displayLives();
        updateJumpCounterDisplay();
    }

    public void init() {
    	// Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight() - 50;

        // Set the program width and height to the screen size
        setSize(screenWidth, screenHeight);
    }
    
    private void displayLives() {
        for (GImage icon : lifeIcons) {
            remove(icon);
        }
        lifeIcons.clear();

        // Add life icons based on the number of lives
        for (int i = 0; i < lives; i++) {
            GImage lifeIcon = new GImage("media/LifeIcon.png");
            lifeIcon.setSize(40, 40); // Set a consistent size for the life icon
            lifeIcon.setLocation(10 + (i * 50), 10); // Arrange icons horizontally
            add(lifeIcon);
            lifeIcons.add(lifeIcon);
        }
    }
    
    private void resetPlayerPosition() {
        double startX = PROGRAM_WIDTH / 2 - PLAYER_SIZE / 2; // Centered horizontally
        double startY = getHeight() - PLAYER_SIZE - 50;      // Near the bottom of the screen
        player.setLocation(startX, startY);
        velocity = 0; 
    }

    private void loseLife() {
        if (jumpCounter <= 0 ||lives <= 0) {
            gameOver(); 
        }
        else if (!fellDown){
        	lives--;
            displayLives();
            fellDown = true;
        }
    }
    
    
   private void gameOver() {
        removeAll();
        GLabel gameOverLabel = new GLabel("Game Over");
        gameOverLabel.setFont("SansSerif-bold-36");
        gameOverLabel.setColor(Color.RED);
        double xPosition = PROGRAM_WIDTH / 2 - gameOverLabel.getWidth() / 2 - 50; 
        double yPosition = PROGRAM_HEIGHT / 2;
        gameOverLabel.setLocation(xPosition, yPosition);
        add(gameOverLabel);
    }
   
   private void updateJumpCounterDisplay() {
	    for (GLabel label : jumpCounterLabels) {
	        remove(label); 
	    }
	    jumpCounterLabels.clear();

	    GLabel jumpLabel = new GLabel("Jumps left: " + jumpCounter);
	    jumpLabel.setFont("SansSerif-bold-24");
	    jumpLabel.setColor(Color.WHITE);

	    int labelX = 10;
	    int labelY = 80; 

	    jumpLabel.setLocation(labelX, labelY);
	    add(jumpLabel);
	    jumpCounterLabels.add(jumpLabel);
	}

    private class MovementKeyListener implements KeyListener {
    	private long jumpStartTime;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A && keyCode != KeyEvent.VK_SPACE) {
                aIsPressed = true;
                player.moveLeft();
            }
            
            if (keyCode == KeyEvent.VK_D && !isCollidedX) {
                dIsPressed = true;
                player.moveRight();

            }
            if (keyCode == KeyEvent.VK_SPACE && isCollidedY && !spaceIsPressed) {
            	spaceIsPressed = true;
            	jumpStartTime = System.currentTimeMillis();
            	jumpCounter--; 
                updateJumpCounterDisplay(); 
                
            
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                aIsPressed = false;
                player.stopMoving();
                
            }
            if (keyCode == KeyEvent.VK_D) {
                dIsPressed = false;
                player.stopMoving();
                
            }
            if (keyCode == KeyEvent.VK_SPACE && isCollidedY) {
            	long heldTime = (System.currentTimeMillis() - jumpStartTime)/10;// current time - time the user held space button
            	//System.out.println(heldTime);
            	jumpPower = Math.min((int)heldTime, MAX_JUMP_POWER);// gain the maximum jump if space button was held since it will be min
                velocity = -jumpPower; // Set the initial upward velocity
                player.jump(velocity);
                jumpPower = 0;
                mapTransition();
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
