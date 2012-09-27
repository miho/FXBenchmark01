/*
 * BallsTest.java
 *
 * License: The code is released under Creative Commons Attribution 2.5 License
 * (http://creativecommons.org/licenses/by/2.5/)
 */

package javaballsopt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author rbair
 */
public class BallsTest {
    protected double _N;
    public List<JavaBall> _ballsO;
    protected boolean _isRunning;
    protected JXImage _root_ball;
    protected double _F = 0;
    protected double _lastF = 0;
    protected double _lastTime;
    
    private Timer _frameTimer;
    private Timer _fpsTimer;
    
    public ShowFpsCallback _showFPS;
    
    public BallsTest(JXImage root_ball, int N) {
        this._root_ball = root_ball;
        this._N = N; // number of objects
        this._ballsO = new ArrayList<JavaBall>();
        this._isRunning = false;
    }
    
    public void startN(int N) {
        this._N = N;
        this.start();
    }
    
    public void start() {
        if (this._isRunning) return;
        this._isRunning = true;
        
        this._F = 0;  // frames counter for FPS
        this._lastF = 0;
        this._lastTime = System.currentTimeMillis();
        
        ActionListener moveBalls = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (_N > _ballsO.size())
                    return;
                _F++;
                // move balls
                for (int i=0; i<_N; i++) {
                    _ballsO.get(i).move();
                }
                // process collisions
                for (int i=0; i<_N; i++) {
                    for (int j=i+1; j<_N; j++) {
                        _ballsO.get(i).doCollide(_ballsO.get(j));
                    }
                }
            }
        };
        
        ActionListener showFps = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (_F - _lastF < 10) return;
                double currTime = System.currentTimeMillis();
                double delta_t = currTime - _lastTime;
                
                double fps = ((_F - _lastF)/delta_t) * 10000f;
                
                _lastF = _F;
                _lastTime = currTime;
                
                if (_showFPS != null)
                    _showFPS.setFps(Math.round(fps)/10d);
            }
        };
        
        // create all our balls
        this._ballsO.add(new JavaBall(this._root_ball));
        
        for (int i=1; i<this._N; i++) {
            this._ballsO.add(this._ballsO.get(0).clone());
        }
        
        this._frameTimer = new Timer(5, moveBalls);
        this._fpsTimer = new Timer(3000, showFps);
        
        this._frameTimer.start();
        this._fpsTimer.start();
    }
    
    public boolean stop() {
        if (!this._isRunning) return false;
        this._isRunning = false;
        this._frameTimer.stop();
        this._fpsTimer.stop();
        
        for (int i=1; i<this._N; i++) {
            this._ballsO.get(i).remove();
        }
        this._ballsO.clear();
        return true;
    }
    
    static interface ShowFpsCallback {
        void setFps(double fps);
    }
}
