package visuals.ingameInformation;

import game.Counter;
import geometry.Point;
import visuals.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class represents the sprite that show the player's score.
 */
public class ScoreIndicator implements Sprite {
    private Point position;
    private Counter scoreIndicator;

    /**
     * A constructor function that sets the sprites position and the player's score.
     *
     * @param pos   the position of the sprite.
     * @param score the player's score.
     */
    public ScoreIndicator(Point pos, Counter score) {
        this.position = pos;
        this.scoreIndicator = score;
    }

    /**
     * The function draw the sprite according ot it's location and the player's score.
     *
     * @param d a "DrawSurface" variable used to draw the sprite.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) position.getX(), (int) position.getY(), "Score: "
                + Integer.toString(this.scoreIndicator.getValue()), 11);
    }

    /**
     * The function let's the sprite know that a unit of time has passed.
     *
     * @param dt a unit of time.
     */
    public void timePassed(double dt) {
    }
}
