
public class Grid {
 private int numRows;
 private int numCols;
 private Space[][] grid; //2d array representing the grid
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
 
 

public boolean isValidPosition(int row, int col) {
	return row >= 0 && row <= numRows && col >= 0 && col <= numCols;
}
}
