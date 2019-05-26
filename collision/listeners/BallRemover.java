package collision.listeners;

import collision.Block;
import game.Counter;
import game.GameLevel;
import geometry.Ball;

/**
 * The class represents a "HitListener" in order to remove a ball from the game once it got hit enough times.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * A constructor function that sets the "GameLevel" and "Counter" values.
     *
     * @param g            a "GameLevel" variable to be set to remove the ball from.
     * @param removedBalls a Counter containing the number of balls remaining.
     */
    public BallRemover(GameLevel g, Counter removedBalls) {
        this.gameLevel = g;
        this.remainingBalls = removedBalls;
    }

    /**
     * The function removes the ball that hit the block from the game.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLeft().getY() > this.gameLevel.getWindowHeight()) {
            remainingBalls.decrease(1);
            hitter.removeFromGame(this.gameLevel);
        }
    }
}
