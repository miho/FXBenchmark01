/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx01;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class ZoomableNode extends Pane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mousex = 0;
    private double mousey = 0;
    private Node view;
    private boolean dragging = false;
    private boolean moveToFront = true;
    private boolean zoomable;
    
    private double minScale = 0.1;
    private double maxScale = 10;
    private double scaleIncrement = 0.001;
    
    public ZoomableNode() {
//        this.view = view;
//
//        getChildren().add(view);
        init();
        
        
    }

    public ZoomableNode(Node view) {
        this.view = view;

        getChildren().add(view);
        init();
        
        
    }

    private void init() {
        onMousePressedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // record the current mouse X and Y position on Node
                mousex = event.getSceneX();
                mousey = event.getSceneY();

                // get the x and y position measure from Left-Top                
                Scale scale = VFXNodeUtils.getGlobalParentScale(ZoomableNode.this);
                
                x = getLayoutX() * scale.getScaleX();
                y = getLayoutY() * scale.getScaleY();
                
                if (isMoveToFront()) {
                    toFront();
                }
            }
        });

        //Event Listener for MouseDragged
        onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // Get the exact moved X and Y

                x += event.getSceneX() - mousex;
                y += event.getSceneY() - mousey;


                // set the positon of Node after calculation
                Scale scale = VFXNodeUtils.getGlobalParentScale(ZoomableNode.this);

                setLayoutX(x * 1 / scale.getScaleX());
                setLayoutY(y * 1 / scale.getScaleY());


                // again set current Mouse x AND y position
                mousex = event.getSceneX();
                mousey = event.getSceneY();

                dragging = true;
                
                event.consume();

            }
        });

        onMouseClickedProperty().set(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                dragging = false;
            }
        });


        setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {

                if (!isZoomable()) {
                    return;
                }

                double scaleValue = 
                        getScaleY() + event.getDeltaY() * getScaleIncrement();

                scaleValue = Math.max(scaleValue, getMinScale());
                scaleValue = Math.min(scaleValue, getMaxScale());

                setScaleX(scaleValue);
                setScaleY(scaleValue);
                
                event.consume();
            }
        });
        
    }

    /**
     * @return the dragging
     */
    protected boolean isDragging() {
        return dragging;
    }

    /**
     * @return the zoomable
     */
    public boolean isZoomable() {
        return zoomable;
    }

    /**
     * @param zoomable the zoomable to set
     */
    public void setZoomable(boolean zoomable) {
        this.zoomable = zoomable;
    }

    /**
     * @return the view
     */
    public Node getView() {
        return view;
    }

    /**
     * @return the minScale
     */
    public double getMinScale() {
        return minScale;
    }

    /**
     * @param minScale the minScale to set
     */
    public void setMinScale(double minScale) {
        this.minScale = minScale;
    }

    /**
     * @return the maxScale
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * @param maxScale the maxScale to set
     */
    public void setMaxScale(double maxScale) {
        this.maxScale = maxScale;
    }

    /**
     * @return the scaleIncrement
     */
    public double getScaleIncrement() {
        return scaleIncrement;
    }

    /**
     * @param scaleIncrement the scaleIncrement to set
     */
    public void setScaleIncrement(double scaleIncrement) {
        this.scaleIncrement = scaleIncrement;
    }

    /**
     * @return the moveToFront
     */
    public boolean isMoveToFront() {
        return moveToFront;
    }

    /**
     * @param moveToFront the moveToFront to set
     */
    public void setMoveToFront(boolean moveToFront) {
        this.moveToFront = moveToFront;
    }
    
    public void removeNode(Node n) {
        getChildren().remove(n);
    }
}
