import java.awt.Color;

import acm.graphics.GRect;

public class Platforms {
private double width;
private double height;
private GRect platform;
//private GImage platform;


public Platforms(double x, double y, double width, double height) {// rectangle constructor for platforms
	this.width = width;
	this.height = height;
	this.platform = new GRect(x,y,width,height);
	this.platform.setFilled(true);
	Color transparent = new Color(0,0,0,0);// Makes platform transparent
    this.platform.setColor(transparent); // Make platforms visible
}

/*
public Platforms(double x, double y, double width, double height, String imagePath) {// this is if we want to make the platforms with images
    this.width = width;
    this.height = height;
    this.platform = new GImage(imagePath, x, y);
    platform.setSize(width, height);
}
*/
public double getWidth() {
	return width;
}

public double getHeight() {
	return height;
}

public GRect getplatform() {
	return platform;
}

}
