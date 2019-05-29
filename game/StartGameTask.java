package game;

import biuoop.KeyboardSensor;
import game.levels.LevelSpecificationReader;
import visuals.AnimationRunner;

import java.io.Reader;
import java.io.InputStreamReader;

/**
 * The class represents a task of starting to play a set of levels.
 */
public class StartGameTask implements Task<Void> {
    private AnimationRunner runner;
    private KeyboardSensor sensor;
    private HighScoresTable table;
    private String levelSetFile;

    /**
     * a constructor Function.
     *
     * @param animationRunner a "AnimationRunner" variable to create a "GameFlow" variable.
     * @param keyboardSensor a "KeyboardSensor" variable to create a "GameFlow" variable.
     * @param highScoresTable a "HighScoresTable" variable to create a "GameFlow" variable.
     * @param level a "String" variable containing the path to the file that contains the levels' description.
     */
    public StartGameTask(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, HighScoresTable
            highScoresTable, String level) {
        this.runner = animationRunner;
        this.sensor = keyboardSensor;
        this.table = highScoresTable;
        this.levelSetFile = level;
    }

    /**
     * The function creates a "GameFlow" variable and a list of levels, Then the function runs the levels with the
     * "GameFlow" variable it created.
     *
     * @return null;
     */
    public Void run() {
        try {
            Reader reader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetFile));
            GameFlow gameFlow = new GameFlow(runner, sensor, table);
            gameFlow.setLevels(new LevelSpecificationReader().levelReader(reader));
            gameFlow.runLevels(gameFlow.getAllGameLevels());
        } catch (Exception e) {
            System.out.println("Error while reading the file of the sets of levels.");
        }
        return null;
    }
}
