
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
 
 public Space getSpace(int row, int col) { 
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
 
 public void setPlatform(int row, int col, String imagePath) {// creating of platforms in grid space
	 if(isValidPosition(row,col)) {
		 Platforms platform = new Platforms(row, col, imagePath);
		 grid[row][col].setPlatform(platform);
	 }
 }
 

public boolean isValidPosition(int row, int col) {
	return row >= 0 && row <= numRows && col >= 0 && col <= numCols;
}
}
