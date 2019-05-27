package visuals;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class is used to make sure the consecutive animation that end with the same key press require the key to be
 * pressed twice to stop.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String stoppingKey;
    private Animation animationRunning;
    private boolean isAlreadyPressed;

    /**
     * A constrictor function for the class.
     *
     * @param sensor a keyboard sensor to sense key presses.
     * @param key the key on the keyboard that stops the animation.
     * @param animation the animation the run until the "key" is pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.stoppingKey = key;
        this.animationRunning = animation;
        this.isAlreadyPressed = false;
    }

    /**
     * The function does a single frame of the animation.
     *
     * @param d a "DrawSurface" variable used to draw the frame.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (!keyboardSensor.isPressed(stoppingKey)) {
            isAlreadyPressed = false;
        } else {
            isAlreadyPressed = true;
        }
        animationRunning.doOneFrame(d, dt);
    }

    /**
     * The function returns true or false depending if the animation should stop running.
     *
     * @return true or false depending if the animation should stop running.
     */
    public boolean shouldStop() {
        if (!isAlreadyPressed) {
            return animationRunning.shouldStop();
        } else {
            return !animationRunning.shouldStop();
        }
    }
}
