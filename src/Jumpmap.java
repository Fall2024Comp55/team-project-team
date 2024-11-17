import java.util.ArrayList;

public class Jumpmap {
    private Grid grid;
    private ArrayList<Platforms> platforms;
    private ArrayList<Trap> traps;
    private Player player;

    // Constructor to initialize grid and player
    public Jumpmap(int rows, int cols, Player player) {
        this.grid = new Grid(rows, cols);
        this.platforms = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.player = player;
    }

    // Method to add a platform to the grid
    public void addPlatform(int row, int col, double width, double height) {
        Platforms platform = new Platforms(row, col, width, height);
        platforms.add(platform);
        grid.getSpace(row, col).setPlatform(platform);
    }

    // Method to add a trap to the grid
    public void addTrap(int row, int col, Trap trap) {
        traps.add(trap);
        if (trap instanceof Trap.PushSpring) {
            grid.getSpace(row, col).setTrap((Trap.PushSpring) trap);
        } else if (trap instanceof Trap.Wind) {
            grid.getSpace(row, col).setTrap((Trap.Wind) trap);
        }
    }

    // Method to move the player
    public void movePlayer(int dx, int dy) {
        int newRow = player.getPosX() + dx;
        int newCol = player.getPosY() + dy;

        if (grid.isValidPosition(newRow, newCol)) {
            player.setRow(newRow);
            player.setCol(newCol);

            // Collision detection
            Space currentSpace = grid.getSpace(newRow, newCol);
            if (currentSpace.hasPlatform()) {
                System.out.println("Player landed on a platform!");
            }
            if (currentSpace.hasPushTrap()) {
                System.out.println("Player hit a PushSpring trap!");
            }
            if (currentSpace.hasWindTrap()) {
                System.out.println("Player encountered a Wind trap!");
            }
        } else {
            System.out.println("Invalid move: Out of bounds.");
        }
    }

    // Render the grid and components
    public void render() {
        System.out.println("Rendering Jumpmap...");
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                Space space = grid.getSpace(row, col);
                if (space.hasPlatform()) {
                    System.out.print("[P]"); // Platform
                } else if (space.hasPushTrap()) {
                    System.out.print("[T]"); // PushSpring Trap
                } else if (space.hasWindTrap()) {
                    System.out.print("[W]"); // Wind Trap
                } else {
                    System.out.print("[ ]"); // Empty Space
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Player player = new Player();
        Jumpmap jumpmap = new Jumpmap(10, 10, player);

        jumpmap.addPlatform(2, 3, 100, 20);
        jumpmap.addTrap(4, 4, new Trap.PushSpring(4, 4));
        jumpmap.addTrap(6, 5, new Trap.Wind(6, 5, "left", 0));

        jumpmap.render();

        // Move player
        jumpmap.movePlayer(2, 3);
        jumpmap.movePlayer(2, 1);
    }
}
