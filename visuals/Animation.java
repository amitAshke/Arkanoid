package visuals;

import biuoop.DrawSurface;

/**
 * The interface is used for classes that represents a whole animation sequence.
 */
public interface Animation {
    /**
     * The function does a whatever is needed for a single frame.
     *
     * @param d a "DrawSurface" variable used to draw the frame.
     * @param dt a unit of time.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * The function is used to determine whether or not to stop the the animation.
     *
     * @return a boolean variable used to stop the animation.
     */
    boolean shouldStop();
}
