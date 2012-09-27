/*
 * GraphicsUtilities.java
 * 
 * Created on May 11, 2007, 3:58:51 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javaballsopt;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 *
 * @author rbair
 */
public class GraphicsUtilities {
    private static final GraphicsConfiguration CONFIGURATION =
            GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration();
    
    private GraphicsUtilities() {}
    
    /**
     * <p>Returns a new translucent compatible image of the specified width
     * and height.</p>
     *
     * @see #createCompatibleImage(java.awt.image.BufferedImage)
     * @see #createCompatibleImage(java.awt.image.BufferedImage, int, int)
     * @see #createCompatibleImage(int, int)
     * @see #loadCompatibleImage(java.net.URL)
     * @see #toCompatibleImage(java.awt.image.BufferedImage)
     * @param width the width of the new image
     * @param height the height of the new image
     * @return a new translucent compatible <code>BufferedImage</code> of the
     *   specified width and height
     */
    public static BufferedImage createTranslucentCompatibleImage(int width,
                                                                 int height) {
        return CONFIGURATION.createCompatibleImage(width, height,
                                                   Transparency.TRANSLUCENT);
    }
    
    public static BufferedImage toCompatibleImage(BufferedImage img) {
        BufferedImage cimg = createTranslucentCompatibleImage(img.getWidth(), img.getHeight());
        Graphics2D gfx = cimg.createGraphics();
        gfx.drawImage(img, null, 0, 0);
        gfx.dispose();
        return cimg;
    }
}
