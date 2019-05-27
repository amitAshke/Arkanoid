package visuals;

import biuoop.DrawSurface;
import visuals.sprites.SpriteCollection;

/**
 * the class represents a countdown animation sequence.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection background;
    private boolean stop;

    /**
     * A constructor function that sets the values for the "CountdownAnimation" variable.
     *
     * @param seconds   the number of seconds the count down will occur for.
     * @param from      the number from which the countdown starts.
     * @param backImage the background that is shown during the countdown.
     */
    public CountdownAnimation(double seconds, int from, SpriteCollection backImage) {
        this.numOfSeconds = seconds;
        this.countFrom = from;
        this.background = backImage;
        this.stop = false;
    }

    /**
     * The function draw the background and the countdown.
     *
     * @param d the "DrawSurface" variable used to draw the sprites.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.background.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(this.countFrom), 64);
        this.countFrom--;
        this.numOfSeconds--;
        if (this.numOfSeconds <= 0) {
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