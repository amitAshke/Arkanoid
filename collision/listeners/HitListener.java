package collision.listeners;

import collision.Block;
import geometry.Ball;

/**
 * An interface for different class that do specific things if a collision between a block and a ball occurred.
 */
public interface HitListener {
    /**
     * The function perform a specific task according to the class that implements the "HitListener" interface.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that collided with the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}