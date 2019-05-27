package visuals.ingameInformation;

import game.Counter;
import geometry.Point;
import visuals.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class represents the sprite that show the number of lives the player has left.
 */
public class LivesIndicator implements Sprite {
    private Point position;
    private Counter livesIndicator;

    /**
     * A constructor function that sets the sprites position and the number of lives left.
     *
     * @param pos  the position of the sprite.
     * @param lives the player's lives.
     */
    public LivesIndicator(Point pos, Counter lives) {
        this.position = pos;
        this.livesIndicator = lives;
    }

    /**
     * The function draw the sprite according ot it's location and the number of lives left.
     *
     * @param d a "DrawSurface" variable used to draw the sprite.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) position.getX(), (int) position.getY(), "Lives: "
                + Integer.toString(this.livesIndicator.getValue()), 11);
    }

    /**
     * The function let's the sprite know that a unit of time has passed.
     *
     * @param dt a unit of time.
     */
    public void timePassed(double dt) {
    }
}
