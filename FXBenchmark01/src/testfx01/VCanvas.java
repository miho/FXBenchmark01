/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx01;

import javafx.beans.binding.DoubleBinding;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.web.WebView;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class VCanvas extends ZoomableNode {

//    private ScrollPane scrollPane;
//    private Pane contentPane;

    public VCanvas(double w, double h) {

        setMinSize(w, h);
        setPrefSize(w, h);

        setZoomable(true);

        setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black;");


        addSubCanvas();
        addCoseIcon();
    }

    private void addCoseIcon() {
        final Rectangle rect = new Rectangle();
        rect.setX(rect.getStrokeWidth());
        rect.setY(rect.getStrokeWidth());
        rect.setWidth(30);
        rect.setHeight(30);

        rect.xProperty().bind(new DoubleBinding() {
            @Override
            protected double computeValue() {

                return VCanvas.this.getPrefWidth()
                        - rect.getWidth() - rect.getStrokeWidth();
            }
        });

        rect.yProperty().bind(new DoubleBinding() {
            @Override
            protected double computeValue() {

                return rect.getStrokeWidth();
            }
        });

        rect.setStroke(Color.BLACK);

        rect.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {

                VFXNodeUtils.removeFromParent(VCanvas.this);
            }
        });

        Scale rectScale = VFXNodeUtils.getGlobalScale(this);
        rectScale.apply(rect);
        getChildren().add(rect);
    }

    private void addSubCanvas() {
        Rectangle rect = new Rectangle();
        rect.setX(rect.getStrokeWidth());
        rect.setY(rect.getStrokeWidth());
        rect.setWidth(30);
        rect.setHeight(30);

        rect.setStroke(Color.BLACK);

        rect.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {

                VCanvas canvas = new VCanvas(300, 200);
                Scale scale = new Scale(0.4, 0.4, 1);
                scale.apply(canvas);

                addNode(canvas);

                canvas.toFront();
            }
        });

        Scale rectScale = VFXNodeUtils.getGlobalScale(this);
        rectScale.apply(rect);
        getChildren().add(rect);
    }

    public void addNode(Node n) {
        getChildren().add(n);
    }
}
