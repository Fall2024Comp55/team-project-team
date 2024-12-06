import java.util.ArrayList;
import acm.graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public abstract class Trap {
    protected GImage trap;
    protected int posX;
    protected int posY;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public abstract void action(Player player);

    // Inner class for PushSpring trap
    public static class PushSpring extends Trap {
        private int launchPower = 20;  // Adjust as needed for launch strength
        private ArrayList<GImage> springImages; // To store the three images
        private Timer animationTimer;
        private int currentImageIndex = 0;
        
        public PushSpring(int x, int y) {
            this.posX = x;
            this.posY = y;
            springImages = new ArrayList<>();
            
            // Add images to Arraylist
            springImages.add(new GImage("springTrap1.png")); // Default unengaged image
            springImages.add(new GImage("springTrap2.png")); // Mid-engaged image
            springImages.add(new GImage("springTrap3.png")); // Fully engaged image
            
            // Set the initial (default) image
            trap = springImages.get(0);
            trap.setLocation(posX, posY);
            
            animationTimer = new Timer(200, new ActionListener() {  // Adjust the delay as needed
                public void actionPerformed(ActionEvent e) {
                    cycleImages();
                }
            });
        }

        @Override
        public void action(Player player) {
            if (isPlayerOnTrap(player)) { // Check if player is on the PushSpring trap
            	if (!animationTimer.isRunning()) {
                    animationTimer.start();
                }
            player.jump(-launchPower); // Launch player upwards by modifying jump velocity
            }
        }

        private void cycleImages() {
            trap.setImage(springImages.get(currentImageIndex).getImage());
            currentImageIndex = (currentImageIndex + 1) % springImages.size();
        }
        
        private boolean isPlayerOnTrap(Player player) {
            // Check if the player’s position overlaps with the PushSpring’s position
            return player.getHitbox().getY() == this.posY && 
                   player.getHitbox().getX() >= this.posX && player.getHitbox().getX() <= this.posX + trap.getWidth();
        }
    }
    
    

    // Inner class for Wind trap
    public static class Wind extends Trap {
        private int windPower;
        private String direction; 
        private ArrayList<GImage> windImages;
        private Timer animationTimer;
        private int currentImageIndex = 0;

        public Wind(int x, int y, String direction, int power) {
        	this.posX = x;
            this.posY = y;
            this.direction = direction;
            this.windPower = power;
            windImages = new ArrayList<>();
            
            // Add images to Arraylist
            windImages.add(new GImage("windTrap1.png")); // Default unengaged image
            windImages.add(new GImage("windTrap2.png")); // Mid-engaged image
            windImages.add(new GImage("windTrap3.png")); // Fully engaged image
            
            trap = windImages.get(0);
            trap.setLocation(posX, posY);

            animationTimer = new Timer(100, new ActionListener() {  // Faster rate for wind
                public void actionPerformed(ActionEvent e) {
                    cycleImages1();
                }
            });
            animationTimer.start();  // Wind animates continuously
        }
        
        private void cycleImages1() {
    		trap.setImage(windImages.get(currentImageIndex).getImage());
    		currentImageIndex = (currentImageIndex + 1) % windImages.size();
    }
    
        
        @Override
        public void action(Player player) {
            // Apply wind effect regardless of whether the player is on the ground or in midair
            if (direction.equals("right")) {
                player.move(windPower,0); // Push player right
            } else if (direction.equals("left")) {
                player.move(-windPower,0); // Push player left
            }
        }
    }
}