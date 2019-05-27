package visuals;

import game.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * the class represents the endgame screen sequence.
 */
public class EndgameScreen implements Animation {
    private KeyboardSensor keyboardSensor;
    private Counter numberOfLives;
    private Counter score;
    private boolean stop;

    /**
     * A constructor function that sets the values for the "EndgameScreen" variable.
     *
     * @param sensor a variable to be set as the "keyboardSensor".
     * @param numOfLives a variable to be set as the "numberOfLives".
     * @param totalScore a variable to be set as the "score".
     */
    public EndgameScreen(KeyboardSensor sensor, Counter numOfLives, Counter totalScore) {
        this.keyboardSensor = sensor;
        this.numberOfLives = numOfLives;
        this.score = totalScore;
        this.stop = false;
    }

    /**
     * The function draw a message depending on the circumstances. Rhe messages that the function can draw refer to
     * either situations in which the player have lost or won the game.
     *
     * @param d a "DrawSurface" variable used to draw the right message.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.numberOfLives.getValue() >= 1) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + Integer.toString(this.score.getValue()),
                    32);
        } else {
            d.drawText(10, d.getHeight() / 2, "game Over. Your score is " + Integer.toString(this.score.getValue()
            ), 32);
        }
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
