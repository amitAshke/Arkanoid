package game.levels;

import biuoop.KeyboardSensor;
import game.HighScoresTable;
import game.Option;
import game.StartGameTask;
import visuals.AnimationRunner;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is used to define sets levels using a file.
 */
public class SetsOfLevelsReader {
    private List<Option> options = new ArrayList<>();

    /**
     * The function uses a "Reader" variable to go over a file and return a list of "Option" variables that will
     * contain the a "StartGameTask" variable.
     *
     * @param reader a "Reader" variable used to read lines from a file.
     * @param runner a "AnimationRunner" variable to be added to every "StartGameTask" variable of each "Option"
     *               variable created by the function.
     * @param sensor a "KeyboardSensor" variable to be added to every "StartGameTask" variable of each "Option"
     *               variable created by the function.
     * @param table a "HighSvoresTable" variable to be added to every "StartGameTask" variable of each "Option"
     *               variable created by the function.
     * @return a list of "Option" variables that will contain the a "StartGameTask" variable.
     */
    public List<Option> levelSetsReader(Reader reader, AnimationRunner runner, KeyboardSensor sensor, HighScoresTable
            table) {
        BufferedReader lineReader = new BufferedReader(reader);
        try {
            int lineNum = 0;
            String line = lineReader.readLine();
            while (line != null) {
                if (lineNum % 2 == 0) {
                    StringBuilder contentBuffer = new StringBuilder();
                    contentBuffer.append(line.trim());
                    String[] parts = contentBuffer.toString().split(":");
                    options.add(new Option(parts[0], parts[1]));
                } else {
                    options.get(options.size() - 1).setStatus(new StartGameTask(runner, sensor, table, line));
                }
                lineNum++;
                line = lineReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("wrong");
        }
        return options;
    }
}
