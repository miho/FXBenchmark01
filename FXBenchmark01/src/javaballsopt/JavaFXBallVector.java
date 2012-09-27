/*
 * JavaBall.java
 * 
 * License: The code is released under Creative Commons Attribution 2.5 License
 * (http://creativecommons.org/licenses/by/2.5/)
 */

package javaballsopt;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.StopBuilder;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.transform.Translate;

/**
 *
 * @author rbair
 */
public class JavaFXBallVector extends JavaFXBall {
    public Circle img = CircleBuilder.create()
    .centerX(20).centerY(20).radius(20)
    .fill(RadialGradientBuilder.create().centerX(0.3).centerY(0.3).stops(
    		StopBuilder.create().offset(0.2).color(Color.RED).build(),
    		StopBuilder.create().offset(0.8).color(Color.RED.darker().darker()).build(),
    		StopBuilder.create().offset(1.0).color(Color.BLACK).build()
    		).build())
    .build();

    public JavaFXBallVector() {
        move();
    }
    
    @Override public void move() {
        super.move();
        
        img.setTranslateX(this._x);
        img.setTranslateY(this._y);
    }
    
    @Override public JavaFXBallVector clone() {
    	JavaFXBallVector lJavaFXBall = new JavaFXBallVector();
        ((Group)img.getParent()).getChildren().add(lJavaFXBall.img);
        return lJavaFXBall;
    }
    
    public void remove() {
    	((Group)img.getParent()).getChildren().remove(img);
    }
}
