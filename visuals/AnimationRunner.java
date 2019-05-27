package visuals;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * The class is used to run whole animation sequence.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper = new Sleeper();

    /**
     * The function sets the gui.
     *
     * @param gu the "GUI" to be set for the "AnimationRunner" variable.
     */
    public AnimationRunner(GUI gu) {
        this.gui = gu;
    }

    /**
     * The function runs the animation sequence.
     *
     * @param framesPerSecond the number of frames presented per second.
     * @param animation The animation that is meant to run.
     * @param sensor a keyboard sensor used to stop the animation.
     * @param stop a specific key that is needed to be pressed to stop the animation.
     */
    public void run(int framesPerSecond, Animation animation, KeyboardSensor sensor, String stop) {
        double dt = 1.0 / framesPerSecond;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        if (stop != null) {
            KeyPressStoppableAnimation stoppableAnimation = new KeyPressStoppableAnimation(sensor, stop, animation);
            while (!stoppableAnimation.shouldStop()) {
                long startTime = System.currentTimeMillis();
                DrawSurface d = gui.getDrawSurface();
                stoppableAnimation.doOneFrame(d, dt);
                gui.show(d);
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        } else {
            while (!animation.shouldStop()) {
                long startTime = System.currentTimeMillis();
                DrawSurface d = gui.getDrawSurface();
                animation.doOneFrame(d, dt);
                gui.show(d);
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        }
    }

    /**
     * The function returns the game's gui.
     *
     * @return the game's gui.
     */
    public GUI getGui() {
        return gui;
    }
}
