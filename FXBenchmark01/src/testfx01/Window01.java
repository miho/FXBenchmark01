/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx01;

import com.sun.javafx.perf.PerformanceTracker;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class Window01 extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Pane root = new Pane();

        final Scene scene = new Scene(root, 1024, 768, Color.rgb(120, 120, 120));


//        root.setManaged(true);

//        VCanvas outerCanvas = new VCanvas(300, 200);
        
//        root.getChildren().add(outerCanvas);<
        
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("FPS: " + PerformanceTracker.getSceneTracker(scene).getInstantFPS());
            }
        };
        
        timer.schedule(task, 0, 1000);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
