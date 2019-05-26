package collision.listeners;

import collision.Block;
import geometry.Ball;

/**
 * The class represents a "HitListener" in order to print information regarding collisions.
 */
public class PrintHitListener implements HitListener {
    /**
     * The function prints information regarding a collision once it occurred.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A BlockDefinitionReader with " + beingHit.getHitPoints() + " points was hit");
    }
}