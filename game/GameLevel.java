package game;

import collision.listeners.BallRemover;
import visuals.Animation;
import visuals.AnimationRunner;
import visuals.ingameInformation.LevelIndicator;
import visuals.ingameInformation.LivesIndicator;
import visuals.ingameInformation.ScoreIndicator;
import visuals.sprites.SpriteCollection;
import visuals.PauseScreen;
import visuals.CountdownAnimation;
import visuals.sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Ball;
import geometry.Rectangle;
import collision.Block;
import collision.Paddle;
import collision.Collidable;

import java.awt.Color;

/**
 * The class represents the two top actions that are performed to run the game. Namely to initialize the game and to
 * run it.
 */
public class GameLevel implements Animation {
    private int windowWidth;
    private int windowHeight;
    private static final int BORDER_WIDTH = 15;
    private static final int INFO_WIDTH = 20;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks = new Counter(0);
    private Counter remainingBalls = new Counter(0);
    private Counter score;
    private Counter numberOfLives;
    private AnimationRunner runner;
    private boolean running;

    /**
     * A constructor function that set values for the "GameLevel" variable.
     *
     * @param keyboardSensor  the variable to be set as the "KeyboardSensor".
     * @param animationRunner the variable to be set as the "AnimationRunner".
     * @param width           the numeric value to be set as the width of the game's window.
     * @param height          the numeric value to be set as the height of the game's window.
     * @param gameGui         gameGui the variable to best as the "GUI".
     * @param gameScore       the variable to be set as the game's score "Counter".
     * @param livesLeft       the variable to be set as the game's lives "Counter".
     */
    public GameLevel(KeyboardSensor keyboardSensor, AnimationRunner animationRunner, int width, int height, GUI
            gameGui, Counter gameScore, Counter livesLeft) {
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.windowWidth = width;
        this.windowHeight = height;
        this.gui = gameGui;
        this.score = gameScore;
        this.numberOfLives = livesLeft;
    }

    /**
     * The function returns the game's window height.
     *
     * @return the game's window height.
     */
    public int getWindowHeight() {
        return this.windowHeight;
    }

    /**
     * The function returns the game's window width.
     *
     * @return the game's window width.
     */
    public int getWindowWidth() {
        return this.windowWidth;
    }

    /**
     * The function returns the game's borders width.
     *
     * @return the game's borders width.
     */
    public int getBorderWidth() {
        return BORDER_WIDTH;
    }

    /**
     * The function returns the game's information tab's width.
     *
     * @return the game's information tab's width.
     */
    public int getInfoWidth() {
        return this.INFO_WIDTH;
    }

    /**
     * The function returns the "GameEnvironment" variable.
     *
     * @return the "GameEnvironment" variable.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * The function return the "Counter" variable for the remaining block.
     *
     * @return the "Counter" variable for the remaining block variable.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * The function return the "Counter" variable for the remaining balls.
     *
     * @return the "Counter" variable for the remaining balls variable.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * The function returns the "Counter" variable for the game's score.
     *
     * @return the "Counter" variable for the game's score.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * The function return the "Counter" variable for the remaining lives.
     *
     * @return the "Counter" variable for the remaining lives.
     */
    public Counter getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * The function returns the "visuals.sprites.SpriteCollection" member of the "game.GameLevel" class.
     *
     * @return the "visuals.sprites.SpriteCollection" member of the "game.GameLevel" class.
     */
    public SpriteCollection getSpriteCollection() {
        return this.sprites;
    }

    /**
     * The function returns the "game.GameEnvironment" member of the "game.GameLevel" class.
     *
     * @return the "game.GameEnvironment" member of the "game.GameLevel" class.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * The function return the opposite value of the boolean value of the "GameLevel" variable.
     *
     * @return the opposite value of the boolean value of the "GameLevel" variable.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The function perform everything is needed in one frame, namely it pauses the game if the player presses 'p',
     * notify all of the sprites that a unit of time has passed, draw all of the sprites and show the image.
     *
     * @param surface a "DrawSurface" variable used to draw and show all of the sprites.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface surface, double dt) {
        final int framesPerSecond = 60;
        if (this.keyboard.isPressed("p")) {
            this.runner.run(framesPerSecond, new PauseScreen(keyboard), this.keyboard, this.keyboard.SPACE_KEY);
        }
        this.sprites.notifyAllTimePassed(dt);
        this.sprites.drawAllOn(surface);
        if (remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0) {
            this.running = false;
        }
        gui.show(surface);
    }

    /**
     * The function initializes the game's objects and their properties.
     */
    public void initialize() {
        final Color borderColor = Color.GRAY;
        final Point livesPosition = new Point(windowWidth / 4, BORDER_WIDTH * 2 / 3);
        final LivesIndicator livesInfo = new LivesIndicator(livesPosition, this.numberOfLives);
        final Point scorePosition = new Point(this.windowWidth * 2 / 4, BORDER_WIDTH * 2 / 3);
        final ScoreIndicator scoreInfo = new ScoreIndicator(scorePosition, this.score);
        // Next 4 rows is for creating blocks that are used as borders to lock the ball in the window.
        Rectangle top = new Rectangle(new Point(0, INFO_WIDTH), windowWidth, BORDER_WIDTH, borderColor);
        Rectangle right = new Rectangle(new Point(windowWidth - BORDER_WIDTH, INFO_WIDTH), BORDER_WIDTH,
                windowHeight, borderColor);
        Rectangle bottom = new Rectangle(new Point(0, windowHeight + BORDER_WIDTH), windowWidth, BORDER_WIDTH,
                borderColor);
        Rectangle left = new Rectangle(new Point(0, INFO_WIDTH), BORDER_WIDTH, windowHeight, borderColor);
        // Next 4 rows is to add the borders to the collidables list.
        this.environment.addCollidable(new Block(top));
        this.sprites.addSprite(new Block(top));
        this.environment.addCollidable(new Block(right));
        this.sprites.addSprite(new Block(right));
        this.environment.addCollidable(new Block(bottom));
        this.sprites.addSprite(new Block(bottom));
        this.environment.addCollidable(new Block(left));
        this.sprites.addSprite(new Block(left));
        this.sprites.addSprite(new Rectangle(new Point(0, 0), windowWidth, INFO_WIDTH, Color.WHITE));
        this.sprites.addSprite(scoreInfo);
        this.sprites.addSprite(livesInfo);
    }

    /**
     * The function initializes the paddle and it's properties. Also, it runs the game itself on an infinite "while"
     * loop.
     *
     * @param level the level which the player play in the turn.
     */
    public void playOneTurn(LevelInformation level) {
        final int framesPerSecond = 60;
        final double dt = 1.0 / framesPerSecond;
        final BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        final Point paddleStartingPoint = new Point(windowWidth / 2 - level.paddleWidth() / 2, windowHeight
                - BORDER_WIDTH);
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        this.running = true;
        Sleeper sleeper = new Sleeper();
        this.remainingBalls.increase(level.numberOfBalls());
        final Point levelNamePosition = new Point(this.windowWidth * 3 / 4, BORDER_WIDTH * 2 / 3);
        final LevelIndicator levelInfo = new LevelIndicator(levelNamePosition, level.levelName());
        this.getSpriteCollection().addSprite(levelInfo);
        for (int i = 1; i <= level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(paddleStartingPoint.getX() + level.paddleWidth() / 2,
                    paddleStartingPoint.getY() - 5));
            this.sprites.addSprite(ball);
            ball.setGameEnvironment(this.environment);
            ball.addHitListener(ballRemover);
            ball.setVelocity(level.initialBallVelocities().get(i - 1), dt);
        }
        Paddle player = new Paddle(paddleStartingPoint, level.paddleWidth(), BORDER_WIDTH, Color.BLUE, level
                .paddleSpeed());
        player.setMovementLimits(BORDER_WIDTH, windowWidth - BORDER_WIDTH);
        player.addToGame(this);
        player.setGui(this.gui);
        CountdownAnimation countdown = new CountdownAnimation(3, 3, this.sprites);
        this.runner.run(1, countdown, this.keyboard, null);
        while (!shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface surface = gui.getDrawSurface();
            doOneFrame(surface, dt);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.environment.getCollidables().size() <= 5) {
                break;
            }
        }
    }

    /**
     * The function remove a collidable from the list of collidables in the "GameLevel" variable's "GameEnvironment"
     * value.
     *
     * @param c a collidable to be removed from the list of collidables in the "GameLevel" variable's "GameEnvironment"
     *          value.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * The function remove a sprite from the list of sprites in the "GameLevel" variable's "SpriteCollevtion" value.
     *
     * @param s a sprite to be removed from the list of sprites in the "GameLevel" variable's "SpriteCollevtion" value.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }
}
