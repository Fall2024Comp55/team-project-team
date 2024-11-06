
public class Space {
private int row;
private int col;


public void setRow(int x) {
	row = x;
}

public void setCol(int y) {
	col = y;
}

public Space(int row, int col) {
	this.row = row;
	this.col = col;
}


public int getRow() {
	return row;
}

public int getCol() {
	return col;
}


public static void main(String[] args) {
	Space one = new Space(3, 4);
	Space two = new Space(1, 6);
	two.setRow(two.getRow()+1);
	two.setCol(two.getCol()-1);
	System.out.println("one r: " + one.getRow() + ", c: " + one.getCol());
	System.out.println("two r: " + two.getRow() + ", c: " + two.getCol());
	}



}
