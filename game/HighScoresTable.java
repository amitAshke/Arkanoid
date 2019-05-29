package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is used to manage the game's highscore list as a file.
 */
public class HighScoresTable {
    private List<ScoreInfo> highScores;
    private final String separator = ";";

    /**
     * A constructor function that initializes a "HighScoresTable" variable.
     *
     * @param highscoresList a list of "ScoreInfo" to be set as the "HighScoreTable" variable's list.
     */
    public HighScoresTable(List<ScoreInfo> highscoresList) {
        highScores = highscoresList;
    }

    /**
     * The function initialize a list of "ScoreInfo" variables. The size of the list is according to the numeric value
     * of the "size" variable.
     *
     * @param size the size of the list that the function initializes.
     */
    public HighScoresTable(int size) {
        highScores = new ArrayList<>(size);
    }

    /**
     * The function adds a score to the game's highscore.
     *
     * @param score the score to be inserted to the game's highscore.
     */
    public void add(ScoreInfo score) {
        int position = this.getRank(score.getScore());
        if (position != -1) {
            if (highScores.size() == 10) {
                if (position == highScores.size() - 1) {
                    highScores.add(position, score);
                } else {
                    for (int j = position; j < highScores.size() - 2; j++) {
                        highScores.add(j + 1, highScores.get(j));
                    }
                    highScores.add(position, score);
                }
            } else {
                if (highScores.size() == 0) {
                    highScores.add(score);
                } else if (position == highScores.size() - 1) {
                    highScores.add(position, score);
                } else {
                    int temp = highScores.size();
                    highScores.add(highScores.get(highScores.size() - 1));
                    for (int j = position; j < temp - 2; j++) {
                        highScores.add(j + 1, highScores.get(j));
                    }
                    highScores.add(position, score);
                }
            }
        }
    }

    /**
     * The function returns the size of the list of scores.
     *
     * @return the size of the list of scores.
     */
    public int size() {
        return highScores.size();
    }

    /**
     * The function returns the game's list of highscores.
     *
     * @return the game's list of highscores.
     */
    public List<ScoreInfo> getHighScores() {
        return highScores;
    }

    /**
     * The function returns the position a score will get in the highscore. if the score is not high enough to enter
     * the highscore then the function return -1.
     *
     * @param score the score that is compared to the other scores on the list of highscores.
     * @return the rank of the score the function received.
     */
    public int getRank(int score) {
        if (highScores.size() == 0) {
            return 0;
        } else {
            for (int i = 0; i < highScores.size(); i++) {
                if (score > highScores.get(i).getScore() || highScores.get(i) == null) {
                    return i;
                }
            }
            if (highScores.size() < 10) {
                return highScores.size();
            } else {
                return -1;
            }
        }
    }

    /**
     * The function clears al of the scores from the list.
     */
    public void clear() {
        highScores.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename the file containing the highscores.
     * @throws IOException in case something went wrong while reading from the file.
     */
    public void load(File filename) throws IOException {
        BufferedReader reader = null;
        if (!filename.exists()) {
            return;
        } else {
            this.clear();
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
                String line = reader.readLine();
                int listIndex = 0;
                while (line != null && listIndex >= highScores.size()) {
                    StringBuilder contentBuffer = new StringBuilder();
                    contentBuffer.append(line.trim());
                    String[] parts = contentBuffer.toString().split(separator);
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    this.highScores.add(listIndex, new ScoreInfo(name, score));
                    listIndex++;
                    line = reader.readLine();
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the file to which the table is saved to.
     * @throws IOException in case something went wrong while writing into the file.
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            for (int i = 0; i < highScores.size(); i++) {
                StringBuilder info = new StringBuilder();
                info.append(highScores.get(i).getName());
                info.append(separator);
                info.append(highScores.get(i).getScore());
                writer.println(info.toString());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while saving!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or there is a problem with reading it, an
     * empty table is returned.
     *
     * @param filename the file from which the table is read.
     * @return the table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        String separator = ";";
        BufferedReader reader = null;
        if (!filename.exists()) {
            return new HighScoresTable(10);
        } else {
            List<ScoreInfo> highscoresList = new ArrayList<>(10);
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
                String line = reader.readLine();
                while (line != null) {
                    StringBuilder contentBuffer = new StringBuilder();
                    contentBuffer.append(line.trim());
                    String[] parts = contentBuffer.toString().split(separator);
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highscoresList.add(new ScoreInfo(name, score));
                    line = reader.readLine();
                }
            } catch (IOException error1) {
                System.out.println("Something went wrong while loading!");
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException error2) {
                    System.out.println("Something went wrong while closing the file!");
                }

            }
            return new HighScoresTable(highscoresList);
        }
    }
}