package collision.listeners;

import collision.Block;
import game.Counter;
import geometry.Ball;

/**
 * The class represents a "HitListener" in order to keep track of the player's score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * A constructor function that sets the score.
     *
     * @param scoreCounter the score that's set for the "ScoreTrackingListener" variable.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The function returns the score.
     *
     * @return the score.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * The function adds a number for the score according to whether or not the block is removed because of the
     * collision.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 1) {
            currentScore.increase(5);
        } else {
            currentScore.increase(15);
        }
    }
}
