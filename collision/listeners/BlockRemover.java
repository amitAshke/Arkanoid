package collision.listeners;

import collision.Block;
import game.Counter;
import game.GameLevel;
import geometry.Ball;

/**
 * A class the represents a "HitListener" that remove a block from the game.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * A constrictor function.
     *
     * @param g             a "GameLevel" variable from which the block will be removed.
     * @param removedBlocks a "Counter" variable representing the number of blocks remaining in the level.
     */
    public BlockRemover(GameLevel g, Counter removedBlocks) {
        this.gameLevel = g;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * A function that activates when notified the a collision with a block that has the "BlockRemover" as a
     * listener, It removes the block from the game if the block ran out of hit points.
     *
     * @param beingHit the block that being hit.
     * @param hitter   the ball that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.reduceHitPoints();
        if (beingHit.getHitPoints() < 1) {
            remainingBlocks.decrease(1);
            beingHit.removeFromGame(this.gameLevel);
        }
    }
}
