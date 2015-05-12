package fr.esiea.glpoo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;
    private Graphics2D g2d;

    public ImagePanel(String path) {
       try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            System.out.println("Pas d'image");
       }
    }
    
    public void rotateComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
            float x = this.getWidth() / 2.0f;
            float y = this.getHeight() / 2.0f;
            g2d.rotate(1 / 180.0 * Math.PI, x, y);
    }
    
    @Override
    public Dimension getPreferredSize(){
    	return new Dimension(image.getWidth(),image.getHeight());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
       
    }

}
