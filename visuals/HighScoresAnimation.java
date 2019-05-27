package visuals;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.HighScoresTable;

/**
 * The class represents an animation showing the game's highscores.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * A constructor Function.
     *
     * @param scores the highscores table that the animation will show.
     * @param sensor the "Keyboard" sensor used to stop the animation.
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor sensor) {
        this.highScoresTable = scores;
        this.keyboardSensor = sensor;
        this.stop = false;
    }

    /**
     * The function draw a frame of the animation.
     *
     * @param d a "DrawSurface" variable used to draw the frame.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int listIndex = 0;
        d.drawText(d.getWidth() / 4, d.getHeight() / 8, "Highscores", 64);
        while (listIndex < highScoresTable.getHighScores().size()) {
            if (highScoresTable.getHighScores().get(listIndex) != null) {
                d.drawText(d.getWidth() / 7, d.getHeight() / 4 + listIndex * 48,
                        highScoresTable.getHighScores().get(listIndex).getName(), 32);
                d.drawText(d.getWidth() / 3 * 2, d.getHeight() / 4 + listIndex * 48,
                        "" + highScoresTable.getHighScores().get(listIndex).getScore(), 32);
            }
            listIndex++;
        }
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The function resets the variable used to stop the animation.
     */
    public void reset() {
        this.stop = false;
    }

    /**
     * The function is used to determine whether or not to stop the the animation.
     *
     * @return a boolean variable used to stop the animation.
     */
    public boolean shouldStop() {
        return stop;
    }
}
