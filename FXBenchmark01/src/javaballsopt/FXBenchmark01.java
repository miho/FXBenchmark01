package javaballsopt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXBenchmark01 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        try {
            // get the image
            URL url = FXBenchmark01.class.getResource("resources/ball.png");
            Image lImage = new Image(url.openStream());

            // create the ball object
//			final JavaFXBallBitmap lJavaFXBall = new JavaFXBallBitmap(lImage);
            final JavaFXBallVector lJavaFXBall = new JavaFXBallVector();

            // create the JavaFX container to hold the balls and add the first ball
            Group root = new Group();
            root.getChildren().add(lJavaFXBall.img);
            javaFXBalls.add(lJavaFXBall);

            // create scene
            Scene scene = new Scene(root, stageWidth, stageHeight);

            // create and show stage
            stage.setTitle("Bubblemark (Window Size)");
            stage.setScene(scene);
            
            stage.setX(0);
            stage.setY(0);
            
            stage.show();
            
            final int maxBalls = 5000;
            final int ballInc  = 500;
            final int numTests = 40;
            final double windowIncrement = 0.05;
            
            // add more balls by cloning it (this is the way the Swing test does it... clone also adds the ball to the group)
            for (int i = 0; i < ballInc -1; i++) {
                javaFXBalls.add(lJavaFXBall.clone());
            }


            // create the incrementing test scenarion
            // let's be bold and time using nanoseconds; expectations are high
            starttimeNano = System.nanoTime();
            new AnimationTimer() {
                @Override
                public void handle(long arg0) {
                    // for all balls
                    int s = javaFXBalls.size();
                    for (int i = 0; i < s; i++) {
                        // move the ball
                        javaFXBalls.get(i).move();
                    }

//					// for all possible ball interactions
//					for (int i = 0; i < s; i++)
//					{
//						for (int j = i+1; j < s; j++)
//						{
//							// detect collision and change movement accordingly
//							javaFXBalls.get(i).doCollide( javaFXBalls.get(j) );
//						}
//					}

                    // count the frame
                    frameCnt++;

                    // check if a second has passed
                    long currenttimeNano = System.nanoTime();
                    if (currenttimeNano > lasttimeFPS + 1000000000) {
                        
                        String stats = "t: " 
                                + currenttimeNano 
                                + "; balls: " 
                                + javaFXBalls.size() 
                                + "; fps: " + frameCnt 
                                + "; size: " + stageWidth + " : " + stageHeight
                                + "; test-count: " + numberOfFullTests;
                        
                        // print out each FPS on stdout
                        System.out.println(stats);
                        statistics.add(stats);

                        // increase the test counter
                        measurementTest++;

                        // after 5 tests (warm up period) 
                        if (measurementTest == 5) {
                            // print the result on stderr in a Excel readable form
                            System.err.println(javaFXBalls.size() + ";" + frameCnt + ";");

                            // add 1000 balls
                            for (int i = 0; i < ballInc; i++) {
                                javaFXBalls.add(lJavaFXBall.clone());
                            }

                            // clear the test counter
                            measurementTest = 0;

                            // stop at 5000 balls
                            if (javaFXBalls.size() > maxBalls) {
                                numberOfFullTests++;
//                                System.exit(0);

                                for (JavaFXBall javaFXBall : javaFXBalls) {
                                    if (javaFXBall != lJavaFXBall) {
                                        javaFXBall.remove();
                                    }
                                }

                                javaFXBalls.clear();
                                
                                incStageWidth(stage, windowIncrement);

                                // add 1000 balls
                                for (int i = 0; i < ballInc; i++) {
                                    javaFXBalls.add(lJavaFXBall.clone());
                                }
                                
                                if (numberOfFullTests == numTests) {
                                    try {
                                        FileWriter writer = new FileWriter(new File("statistics.txt"));
                                        
                                        for (String st : statistics) {
                                            writer.append(st + "\n");
                                        }
                                        
                                        writer.flush();
                                        writer.close();
                                        
                                        System.exit(0);
                                        
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXBenchmark01.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }

                        // reset frame count and time
                        frameCnt = 0;
                        lasttimeFPS = currenttimeNano;
                    }
                }
            }.start();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    private void incStageWidth(Stage stage, double inc) {
        stageWidth += stageWidth * inc;
        stageHeight += stageHeight * inc;

        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    } 
    
    List<JavaFXBall> javaFXBalls = new ArrayList<JavaFXBall>();
    int frameCnt = 0;
    long starttimeNano = 0;
    long lasttimeFPS = 0;
    int measurementTest = 0;
    double stageWidth = 1920.0 / 5.0;
    double stageHeight = 1200.0 / 5.0;
    int numberOfFullTests = 0;
    List<String> statistics = new ArrayList<String>();
}
