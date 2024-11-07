import java.util.ArrayList;
import acm.graphics.*;

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
    public class PushSpring extends Trap {
        private int launchPower = 20;  // Adjust as needed for launch strength

        public PushSpring(int x, int y) {
            this.posX = x;
            this.posY = y;
            //Add trap image here 
        }

        @Override
        public void action(Player player) {
            if (isPlayerOnTrap(player)) { // Check if player is on the PushSpring trap
                player.setJumpVelocity(-launchPower); // Launch player upwards by modifying jump velocity
            }
        }

        private boolean isPlayerOnTrap(Player player) {
            // Check if the player’s position overlaps with the PushSpring’s position
            return player.getPosY() == this.posY && 
                   player.getPosX() >= this.posX && player.getPosX() <= this.posX + trap.getWidth();
        }
    }

    // Inner class for Wind trap
    public class Wind extends Trap {
        private int windPower;
        private String direction;  

        public Wind(int x, int y, String direction, int power) {
            this.posX = x;
            this.posY = y;
            this.direction = direction;
            this.windPower = power;
            // Initialize trap image here
        }

        @Override
        public void action(Player player) {
            // Apply wind effect regardless of whether the player is on the ground or in midair
            if (direction.equals("right")) {
                player.setRunDirection(windPower); // Push player right
            } else if (direction.equals("left")) {
                player.setRunDirection(-windPower); // Push player left
            }
        }
    }

    // Abstract Boss class to serve as a base for different boss types
    public abstract class Boss extends Trap {
        protected ArrayList<GImage> bossArray;

        public Boss(int x, int y) {
            this.posX = x;
            this.posY = y;
            bossArray = new ArrayList<>();
            // Populate bossArray with images to create boss animations/appearances
        }

        @Override
        public abstract void action(Player player); // Each boss will have unique behavior
    }

    // Specific boss implementations
    public class Boss1 extends Boss {
        public Boss1(int x, int y) {
            super(x, y);
            // Initialize Boss1-specific properties here 
        }

        @Override
        public void action(Player player) {
            // Define Boss1-specific actions 
        }
    }

    public class Boss2 extends Boss {
        public Boss2(int x, int y) {
            super(x, y);
            // Initialize Boss2-specific properties here 
        }

        @Override
        public void action(Player player) {
            // Define Boss2-specific actions
        }
    }

    // Additional Boss classes (e.g., Boss3, Boss4)
}
