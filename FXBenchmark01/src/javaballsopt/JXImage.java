/*
 * JXImage.java
 * 
 * Created on May 11, 2007, 2:49:18 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javaballsopt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * A simple component that renders an image.
 * @author rbair
 */
class JXImage extends JComponent {
    BufferedImage img;
    
    JXImage(BufferedImage img) {
        this.img = img;
        this.setSize(img.getWidth(), img.getHeight());
    }
    
    @Override protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
