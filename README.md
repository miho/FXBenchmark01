FXBenchmark01
=============

Webpage: http://mihosoft.eu/?p=280

Simple JavaFX Benchmark Application based on JavaFX 2.0 BubbleMark (also works for JavaFX 8): http://tbeernot.wordpress.com/2011/11/12/javafx-2-0-bubblemark/

**[Update 30.04.2017]:** this benchmark was originally targeted at JavaFX 2.0. Many performance issues have been addressed. But it is still interesting to see how the stage size affects the render performance which can still be noticable today.

## Why? ##
I have created the Visual Programming Environment [VRL-Studio](http://vrl-studio.mihosoft.eu/) using Swing. I think of switching to JavaFX. But before I start with the real implementation I want to be sure that the performance is ok. Unfortunately, JavaFX still has some critical issues that are hopefully resolved in the near future.

The purpose of this benchmark is to investigate these issues.

## Issues ##
Performance seems to heavily depend on the graphics card/driver/OS. Even more important, the framerate of a JavaFX application drops as the size of the application stage increases. This does not only happen if nodes are drawn on the whole application window but also if only a part of the window shows content. If your application runs on a large screen, e.g., a 27" iMac (2880x2560), this does really matter.

## Questions ##

- Does JavaFX use dirty region optimization like Swing?

- Does it use additional image buffers like JCanvas3D in Java3D does?

  > This would be a good explanation for the fps drop. I have the feeling that the delay introduced by increased window size is independent from the number of nodes in the sceenegraph.


## The Benchmark ##
1. perform the bubblemark (uses only wall collision)
2. add more balls
3. increase the window size and start with `1.` again

After several runs the benchmark stops and writes the results to `statistics.txt`.

## How to run the Benchmark?

### Requirements

- Java >= 1.8
- Internet connection (dependencies are downloaded automatically)
- IDE: [Gradle](http://www.gradle.org/) Plugin (not necessary for command line usage)

### IDE

Open the `FXBenchmark01` [Gradle](http://www.gradle.org/) project in your favourite IDE (tested with NetBeans 8.2) and build it
by calling the `run` task.

### Command Line

Navigate to the [Gradle](http://www.gradle.org/) project (e.g., `path/to/FXBenchmark01`) and enter the following command

#### Bash (Linux/OS X/Cygwin/other Unix-like shell)

    bash gradlew run
    
#### Windows (CMD)

    gradlew run
