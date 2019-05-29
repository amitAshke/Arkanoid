package game;

import collision.listeners.BlockRemover;
import collision.listeners.PrintHitListener;
import collision.listeners.ScoreTrackingListener;
import visuals.AnimationRunner;
import visuals.EndgameScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DialogManager;
import visuals.HighScoresAnimation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is the one that's in control of the flow of the game from one level to the other.
 */
public class GameFlow {
    private static final int BORDER_WIDTH = 15;
    private static final int INFO_WIDTH = 15;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private GUI gui;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> allGameLevels = new ArrayList<>();
    private HighScoresTable highscores;

    /**
     * A constructor function that sets the values for the "GameFlow" variable.
     *
     * @param ar the variable to be set as the "AnimationRunner".
     * @param ks the variable to be set as the "KeyboardSensor".
     * @param highscore the variable to be set as the "HighScoresTable".
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable highscore) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.highscores = highscore;
        this.gui = ar.getGui();
    }

    /**
     * The function receives a list of levels and set's it as the "GameFlow" variable's list of levels.
     *
     * @param list a list of levels for the "GameFlow" variable.
     */
    public void setLevels(List<LevelInformation> list) {
        this.allGameLevels = list;
    }

    /**
     * The function returns the list of levels the game will run.
     *
     * @return the list of levels the game will run.
     */
    public List<LevelInformation> getAllGameLevels() { return this.allGameLevels; }

    /**
     * The function had level 1 to the list of levels the game will run.
     */
    /*public void addLevel1() {
        Level1 lvl1 = new Level1(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl1);
    }*/

    /**
     * The function had level 2 to the list of levels the game will run.
     */
    /*public void addLevel2() {
        Level2 lvl2 = new Level2(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl2);
    }*/

    /**
     * The function had level 3 to the list of levels the game will run.
     */
    /*public void addLevel3() {
        Level3 lvl3 = new Level3(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl3);
    }*/

    /**
     * The function had level 4 to the list of levels the game will run.
     */
    /*public void addLevel4() {
        Level4 lvl4 = new Level4(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl4);
    }*/

    /**
     * Add the levels to the list of levels the game wll run in the default order.
     */
    /*public void createLevels() {
        Level1 lvl1 = new Level1(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl1);
        Level2 lvl2 = new Level2(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl2);
        Level3 lvl3 = new Level3(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl3);
        Level4 lvl4 = new Level4(new Point(this.BORDER_WIDTH, this.BORDER_WIDTH + this.INFO_WIDTH), WINDOW_WIDTH
                - 2 * this.BORDER_WIDTH, WINDOW_HEIGHT - this.INFO_WIDTH - this.BORDER_WIDTH);
        this.allGameLevels.add(lvl4);
    }*/

    /**
     * The function runs the list of levels.
     *
     * @param levels the list of levels the game will run.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        Counter numberOfLives = new Counter(7);
        for (LevelInformation levelInfo : levels) {
            GameLevel gameLevel = new GameLevel(this.keyboardSensor, this.animationRunner, WINDOW_WIDTH,
                    WINDOW_HEIGHT, this.gui, score, numberOfLives);
            final ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
            final PrintHitListener printer = new PrintHitListener();
            final BlockRemover blockRemover = new BlockRemover(gameLevel, new Counter(levelInfo
                    .numberOfBlocksToRemove()));
            levelInfo.initialBallVelocities();
            //levelInfo.initializeBackground();
            gameLevel.getSpriteCollection().addSprite(levelInfo.getBackground());
            //levelInfo.initializeBlocks();
            for (int i = 0; i < levelInfo.blocks().size(); i++) {
                levelInfo.blocks().get(i).addHitListener(printer);
                levelInfo.blocks().get(i).addHitListener(scoreTracker);
                levelInfo.blocks().get(i).addHitListener(blockRemover);
                gameLevel.getEnvironment().addCollidable(levelInfo.blocks().get(i));
                gameLevel.getSpriteCollection().addSprite(levelInfo.blocks().get(i));
            }
            gameLevel.initialize();
            gameLevel.getRemainingBlocks().increase(levelInfo.blocks().size());
            while (gameLevel.getNumberOfLives().getValue() > 0 && gameLevel.getGameEnvironment().getCollidables()
                    .size() >= 5) {
                gameLevel.playOneTurn(levelInfo);
                gameLevel.getGameEnvironment().getCollidables().remove(gameLevel.getGameEnvironment().getCollidables().
                        size() - 1);
                gameLevel.getSpriteCollection().getSprites().remove(gameLevel.getSpriteCollection().getSprites()
                        .size() - 1);
                if (gameLevel.getRemainingBalls().getValue() == 0) {
                    numberOfLives.decrease(1);
                }
                if (gameLevel.getEnvironment().getCollidables().size() == 4) {
                    score.increase(100);
                }
            }
            if (gameLevel.getNumberOfLives().getValue() <= 0) {
                break;
            }
        }
        this.animationRunner.run(60, new EndgameScreen(this.keyboardSensor, numberOfLives, score),
                keyboardSensor, keyboardSensor.SPACE_KEY);
        this.allGameLevels = null;
        if (isScoreHigh(score)) {
            insertNewHighscore(score);
        }
        this.animationRunner.run(60, new HighScoresAnimation(highscores, keyboardSensor), keyboardSensor, keyboardSensor
                .SPACE_KEY);
    }

    /**
     * The function checks if the score at the end of the game is high enough to enter the highscore table.
     *
     * @param score the score at the end of the game.
     * @return true or false depending on whether or not the score is high enough to enter the highscore table.
     */
    public boolean isScoreHigh(Counter score) {
        return highscores.getRank(score.getValue()) != -1;
    }

    /**
     * The function give the player the option to write his name and insert his score allong with his name into the
     * highscore table.
     *
     * @param score the player's score.
     */
    public void insertNewHighscore(Counter score) {
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        System.out.println(name);
        ScoreInfo newHighScore = new ScoreInfo(name, score.getValue());
        highscores.add(newHighScore);
        try {
            highscores.save(new File("highscore"));
        } catch (IOException saveError) {
            System.out.println("An error occurred while saving the file.");
        }
    }
}
