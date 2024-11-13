
public class Space {
	
	private int row;
	private int col;
	private Trap.Wind windTrap;
	private Trap.PushSpring pushTrap;
	private Platforms platform;
	
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
		this.windTrap = null;
		this.pushTrap = null;
		this.platform = null;
	}
	
	public void setTrap(Trap.PushSpring trap) {
		this.pushTrap = trap;
	}
	
	public void setTrap(Trap.Wind trap) {
		this.windTrap = trap;
	}
	
	public void setPlatform(Platforms platform) {
		this.platform = platform;
	}
	
	public boolean hasPlatform() {
		return platform != null;
	}
	
	public Platforms getPlatform() {
		return platform;
	}
	
	public boolean hasWindTrap() {// check is location already has a trap
		return windTrap != null;
	}
	
	public boolean hasPushTrap() {
		return pushTrap != null;
	}
	
	public Trap getPushTrap() {
		return this.pushTrap;
	}
	
	public Trap getWindTrap() {// location to set traps
		return this.windTrap;
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
