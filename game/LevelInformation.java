package game;

import collision.Block;
import collision.Velocity;
import visuals.sprites.Sprite;

import java.util.List;

/**
 * The interface is used for all of the levels.
 */
public interface LevelInformation {
    /**
     * The function returns the number of balls the level starts with.
     *
     * @return the number of balls the level starts with.
     */
    int numberOfBalls();

    /**
     * The function returns the initial velocities of the level's balls.
     *
     * @return the initial velocities of the level's balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The function returns the paddle's speed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * The function returns the paddle's width.
     *
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * The function returns the level's name.
     *
     * @return the level's name.
     */
    String levelName();

    /**
     * The function returns the level's background.
     *
     * @return the level's background.
     */
    Sprite getBackground();

    /**
     * The function returns the level's blocks.
     *
     * @return the level's blocks.
     */
    List<Block> blocks();

    /**
     * The function return the number of block needed to be removes in the level.
     *
     * @return the number of block needed to be removes in the level.
     */
    int numberOfBlocksToRemove();
}