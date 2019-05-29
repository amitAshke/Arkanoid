package game;

import biuoop.KeyboardSensor;
import visuals.AnimationRunner;
import visuals.HighScoresAnimation;

/**
 * The class represents a task of showing the highscores of the game.
 */
public class ShowHiScoreTask implements Task<Void> {
    private AnimationRunner runner;
    private HighScoresAnimation highscores;
    private KeyboardSensor sensor;

    /**
     * A constructor function.
     *
     * @param animationRunner a "AnimationRunner" variable to run the required animation.
     * @param highscoresAnimation the animation that is going to run.
     * @param keyboardSensor a "KeyboardSensor" variable to know when to stop the animation.
     */
    public ShowHiScoreTask(AnimationRunner animationRunner, HighScoresAnimation highscoresAnimation, KeyboardSensor
            keyboardSensor) {
        this.runner = animationRunner;
        this.highscores = highscoresAnimation;
        this.sensor = keyboardSensor;
    }

    /**
     * The function runs the animation showing the game's highscore.
     *
     * @return null;
     */
    public Void run() {
        this.runner.run(60, this.highscores, this.sensor, this.sensor.SPACE_KEY);
        highscores.reset();
        return null;
    }
}
