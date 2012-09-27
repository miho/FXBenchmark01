/*
 * JavaBall.java
 * 
 * License: The code is released under Creative Commons Attribution 2.5 License
 * (http://creativecommons.org/licenses/by/2.5/)
 */

package javaballsopt;

/**
 *
 * @author rbair
 */
public class JavaBall extends Ball {
    private JXImage img;
    
    public JavaBall(JXImage img) {
        this.img = img;
        move();
    }
    
    @Override public void move() {
        super.move();
        this.img.setLocation((int)this._x, (int)this._y);
    }
    
    @Override public JavaBall clone() {
        JXImage newImg = new JXImage(img.img);
        img.getParent().add(newImg);
        return new JavaBall(newImg);
    }
    
    public void remove() {
        img.getParent().remove(img);
    }
}
