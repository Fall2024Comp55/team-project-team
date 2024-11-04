import javax.swing.Timer;
import java.awt.event.*;

import acm.graphics.*;

public class Player {
	private GImage player;
	private Skin skin;
	private int posX;
	private int posY;
	private boolean isFacingLeft;
	private int gravity = 2;
    private int runSpeed = 3;
    private int jumpVelocity = 0;
}
