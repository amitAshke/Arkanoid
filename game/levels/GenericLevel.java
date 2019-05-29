package game.levels;

import collision.Block;
import collision.Velocity;
import game.LevelInformation;
import visuals.sprites.Sprite;

import java.util.List;

/**
 * The class represents a generic level class.
 */
public class GenericLevel implements LevelInformation {
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private int numberOfBalls;
    private Sprite background;
    private List<Velocity> initialBallVelocities;
    private List<Block> blocks;

    /**
     * The function sets the level's name by using a "String" variable.
     *
     * @param name a "String" variable containing the name.
     */
    public void setName(String name) {
        this.levelName = name;
    }

    /**
     * The function sets the level's paddle speed by using a "int" variable.
     *
     * @param speedOfPaddle an "int" variable containing the paddle's speed.
     */
    public void setPaddleSpeed(int speedOfPaddle) {
        this.paddleSpeed = speedOfPaddle;
    }

    /**
     * The function sets the level's paddle width by using a "int" variable.
     *
     * @param widthOfPaddle an "int" variable containing the paddle's width.
     */
    public void setPaddleWidth(int widthOfPaddle) {
        this.paddleWidth = widthOfPaddle;
    }

    /**
     * the function sets the number of block in the level by using an "int" variable.
     *
     * @param numOfBlocks an "int" variable containing the number of blocks in the level.
     */
    public void setNumberOfBlocksToRemove(int numOfBlocks) {
        this.numberOfBlocksToRemove = numOfBlocks;
    }

    /**
     * The function sets the number of balls in the level by using an "int" variable.
     *
     * @param numOfBalls an "int" variable containing the number of blocks in the level.
     */
    public void setNumberOfBalls(int numOfBalls) {
        this.numberOfBalls = numOfBalls;
    }

    /**
     * The function sets the background in the level by using a "Sprite" variable.
     *
     * @param levelBackground a "sprite" variable containing the background of the level.
     */
    public void setBackground(Sprite levelBackground) {
        this.background = levelBackground;
    }

    /**
     * The function sets the ball's initial velocities by using a "List" variable of "Velocity" variables.
     *
     * @param initialVelocities a "List" variable of "Velocity" variables.
     */
    public void setInitialBallVelocities(List<Velocity> initialVelocities) {
        this.initialBallVelocities = initialVelocities;
    }

    /**
     * The function set the level's blocks by using a "List" variable of "Block" variables.
     *
     * @param levelBlocks a "List" variable of "Block" variables.
     */
    public void setBlocks(List<Block> levelBlocks) {
        this.blocks = levelBlocks;
    }

    /**
     * The function returns the number of balls in the level.
     *
     * @return the number of balls in the level.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The function returns the initial velocities of the level's balls.
     *
     * @return the initial velocities of the level's balls.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * The function returns the paddle's speed.
     *
     * @return the paddle's speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function returns the paddle's width.
     *
     * @return the paddle's width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The function returns the level's name.
     *
     * @return the level's name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * The function returns the level's background.
     *
     * @return the level's background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The function returns the level's blocks.
     *
     * @return the level's blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * The function return the number of block needed to be removes in the level.
     *
     * @return the number of block needed to be removes in the level.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
