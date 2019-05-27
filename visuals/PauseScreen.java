package visuals;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class represents The pause screen that is accessible during the game.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * A constructor function that sets the "KeyboardSensor" variable and the "boolean" variable.
     *
     * @param sensor a "KeyboardSensor" variable to be set to "keyboardSensor".
     */
    public PauseScreen(KeyboardSensor sensor) {
        this.keyboardSensor = sensor;
        this.stop = false;
    }

    /**
     * The function draw the message of the pause screen and changes the boolean variable to "true" if the space key
     * was pressed.
     *
     * @param d a "DrawSurface" variable used to draw the message of the pause screen.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The function is used to determine whether or not to stop the the animation.
     *
     * @return a boolean variable used to stop the animation.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
