import acm.graphics.*;

public class Platforms {
private int width;
private int height;
private GImage platformImage;

public Platforms(int width, int height, String imagePath) {
	this.width = width;
	this.height = height;
	this.platformImage = new GImage(imagePath); //set image
	this.platformImage.setSize(width, height); // sets dimensions of image
}

public int getWidth() {
	return width;
}

public int getHeight() {
	return height;
}

public GImage getPlatformImage() {
	return platformImage;
}

}
