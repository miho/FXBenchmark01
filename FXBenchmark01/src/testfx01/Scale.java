/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx01;

import javafx.scene.Node;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class Scale {
    private double scaleX = 1;
    private double scaleY = 1;
    private double scaleZ = 1;

    public Scale() {
    }
    
    

    public Scale(double scaleX, double scaleY, double scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }
    
    public Scale(Node n) {
        this.scaleX = n.getScaleX();
        this.scaleY = n.getScaleY();
        this.scaleZ = n.getScaleZ();
    }

    /**
     * @return the scaleX
     */
    public double getScaleX() {
        return scaleX;
    }

    /**
     * @return the scaleY
     */
    public double getScaleY() {
        return scaleY;
    }

    /**
     * @return the scaleZ
     */
    public double getScaleZ() {
        return scaleZ;
    }

    
    public void apply(Node n) {
        n.setScaleX(scaleX);
        n.setScaleY(scaleY);
        n.setScaleZ(scaleZ);
    }
    
    public void mult(Scale s) {
        scaleX*=s.scaleX;
        scaleY*=s.scaleY;
        scaleZ*=s.scaleZ;
    }
    
}
