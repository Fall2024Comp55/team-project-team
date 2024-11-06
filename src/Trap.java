public abstract class Trap {
 // protected GImage trap; 
 protected int posX;
 protected int posY;

 // Constructor to initialize position and trap image
 public Trap(int posX, int posY) {           // change to public Trap(int posX, int posY, GImage trap) later
     this.posX = posX;
     this.posY = posY;
     // this.trap = trap;
 }

 // Getter for posX
 public int getPosX() {
     return posX;
 }

 // Getter for posY
 public int getPosY() {
     return posY;
 }

 // Abstract method for the specific trap action
 public abstract void action();
}


