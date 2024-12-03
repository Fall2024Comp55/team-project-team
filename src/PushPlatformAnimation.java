import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PushPlatformAnimation extends JPanel {
    private ArrayList<BufferedImage> frames; // Store animation frames
    private int currentFrame = 0;           // Track the current frame
    private Timer animationTimer;          // Timer for the animation

    public PushPlatformAnimation() {
        frames = new ArrayList<>();
        loadImages("media"); // Specify the folder containing your images

        // Set up a timer to update the frame index every 100ms
        animationTimer = new Timer(130, e -> {
            currentFrame = (currentFrame + 1) % frames.size(); // Loop through frames
            repaint(); // Repaint to show the next frame
        });
        animationTimer.start();
    }

    // Load FrogAvatarHop images from the media folder
    private void loadImages(String folderPath) {
        try {
            for (int i = 1; i <= 8; i++) { 
                String fileName = String.format("%s/PushPlatform#%d.png", folderPath, i);
                frames.add(ImageIO.read(new File(fileName)));
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        if (frames.isEmpty()) {
            throw new RuntimeException("No images loaded! Check your file paths.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!frames.isEmpty()) {
            BufferedImage currentImage = frames.get(currentFrame);

            // Define scaling factors
            double scaleX = 4.0; 
            double scaleY = 4.0; 

            // Calculate the new size
            int newWidth = (int) (currentImage.getWidth() * scaleX);
            int newHeight = (int) (currentImage.getHeight() * scaleY);

            // Calculate position to center the scaled image
            int x = (getWidth() - newWidth) / 2;
            int y = (getHeight() - newHeight) / 2;

            // Draw the scaled image
            g.drawImage(currentImage, x, y, newWidth, newHeight, null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Push Platform Animation");
        PushPlatformAnimation animationPanel = new PushPlatformAnimation();

        frame.add(animationPanel);
        frame.setSize(800, 600); // Adjust size as needed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}