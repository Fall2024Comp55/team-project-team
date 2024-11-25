
public class Grid {
 private int numRows;
 private int numCols;
 private Space[][] grid; //2d array representing the grid
 private int windPower = 5; //the strength of the wind
 //do a gRect to create platforms
 
 //creating a grid constructor
 public Grid(int row, int col) {
	 this.numRows = row;
	 this.numCols = col;
	 grid = new Space[row][col];
	 
	 for(int i = 0; i < row; i++) {
		 for(int j = 0; j < col; j++) {
			 grid[i][j] = new Space(i , j);
		 }
	 }
 }
 
 
 public int getNumRows() {
	 return this.numRows;
 }
 
 public int getNumCols() {
	 return this.numCols;
 }
 
 public Space getSpace(int row, int col) { // Space location validation
	 if(isValidPosition(row, col)) {
		 return grid[row][col];
	 }
	 else {
		 throw new IllegalArgumentException("Not a valid position");
	 }
 }
 
 public void setPushTrap(int row, int col) {// creation of push traps
	 if(isValidPosition(row,col)) {
		Trap.PushSpring pushTrap = new Trap.PushSpring(row, col);
		grid[row][col].setTrap(pushTrap);
	 }
 }
 
 public String windDirection(int x) {// set the direction for wind to blow
	 if( x == 0) {
		 return "left";
	 }
	 else if(x == 1) {
		 return "right";
	 }
	return null;
 }
 
 public void setWindTrap(int row, int col, int direction) {// creation of wind traps
	 if(isValidPosition(row,col)) {
		 Trap.Wind windTrap = new Trap.Wind(row, col, windDirection(direction), windPower);
		 grid[row][col].setTrap(windTrap);
	 }
 }
 
 public void setPlatform(double row, double col, double width, double height) {
	    double x = col * 50; // Assuming each grid cell is 50x50 pixels
	    double y = row * 50;
	    double w = width * 2;
	    double h = height * 2;
	    if (isValidPosition(row, col)) {
	        Platforms platform = new Platforms(x, y, w, h);
	        grid[(int)row][(int)col].setPlatform(platform);
	    }
	}
 

public boolean isValidPosition(double row, double col) {
	return row >= 0 && row <= numRows && col >= 0 && col <= numCols;
}

public void printPlatforms() {
	for(int i = 0; i < this.numRows; i++ ) {
		for(int j = 0; j < this.numCols; j++) {
			Platforms platform = grid[i][j].getPlatform();
			if(grid[i][j].hasPlatform()) {
				System.out.println("Platform location: x = "+ i +", y = "+ j +". Size of platform: width = "+platform.getWidth()+", "+platform.getHeight()+". ");
			}
			if(grid[i][j].hasPushTrap()) {
				System.out.println("Pushspring location: x = "+ i +", y = "+ j +" ");
			}
			else if(grid[i][j].hasWindTrap()) {
				System.out.println("Wind location: x = "+ i +", y = "+ j +"");

			}
		}
	}
}

public static void main(String[] args) {
	Grid grid = new Grid(10, 10); // Create a 10x10 grid
    grid.setPlatform(2, 3, 2, 1); // Add a platform
    grid.setPlatform(5, 4, 3, 1); // Add another platform

    grid.printPlatforms(); // Print all platform positions
	}


}

