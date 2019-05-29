package game;

import java.io.Serializable;

/**
 * A class that represents a score that is in the highscore.
 */
public class ScoreInfo implements Serializable {
    private String playerName;
    private int playerScore;

    /**
     * A constructor function.
     *
     * @param name the player's name.
     * @param score the player's score.
     */
    public ScoreInfo(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
    }

    /**
     * The function returns the name of the player.
     *
     * @return the name of the player.
     */
    public String getName() {
        return playerName;
    }

    /**
     * The function returns the score of the player.
     *
     * @return the score of the player.
     */
    public int getScore() {
        return playerScore;
    }
}