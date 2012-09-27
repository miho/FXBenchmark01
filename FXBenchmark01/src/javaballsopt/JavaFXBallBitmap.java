/*
 * JavaBall.java
 * 
 * License: The code is released under Creative Commons Attribution 2.5 License
 * (http://creativecommons.org/licenses/by/2.5/)
 */

package javaballsopt;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rbair
 */
public class JavaFXBallBitmap extends JavaFXBall {
    public ImageView img;

    public JavaFXBallBitmap(Image img) {
        this.img = new ImageView(img);
        move();
    }
    
    @Override public void move() {
        super.move();
        this.img.setX(this._x);
        this.img.setY(this._y);
    }
    
    @Override public JavaFXBallBitmap clone() {
    	JavaFXBallBitmap lJavaFXBall = new JavaFXBallBitmap(img.getImage());
        ((Group)img.getParent()).getChildren().add(lJavaFXBall.img);
        return lJavaFXBall;
    }
    
    public void remove() {
    	((Group)img.getParent()).getChildren().remove(img);
    }
}
