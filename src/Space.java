
public class Space {
	
	private int row;
	private int col;
	private Trap trap;// made to be able to place traps in space locations
	
	public void setRow(int x) {
		row = x;
	}
	
	public void setCol(int y) {
		col = y;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public Space(int row, int col) { // space class constructor
		this.row = row;
		this.col = col;
		this.trap = null;
	}
	
	public void setTrap(Trap trap) {// location to set traps
		this.trap = trap;
	}
	
	public boolean hasTrap() {// check is location already has a trap
		return trap != null;
	}
	
	//testing to see if the class worked
	public static void main(String[] args) {
		Space one = new Space(3, 4);
		Space two = new Space(1, 6);
		two.setRow(two.getRow()+1);
		two.setCol(two.getCol()-1);
		System.out.println("one r: " + one.getRow() + ", c: " + one.getCol());
		System.out.println("two r: " + two.getRow() + ", c: " + two.getCol());
		}
	

}
