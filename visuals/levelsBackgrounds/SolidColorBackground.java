package visuals.levelsBackgrounds;

import biuoop.DrawSurface;
import visuals.sprites.Sprite;

import java.awt.Color;

/**
 * A class that represents a solid color as a background for a level.
 */
public class SolidColorBackground implements Sprite {
    private Color color;

    /**
     * A constructor function.
     *
     * @param solid the color of the background.
     */
    public SolidColorBackground(Color solid) {
        this.color = solid;
    }

    /**
     * The function draw the background.
     *
     * @param draw a "DrawSurface" variable used to draw the solid color background.
     */
    public void drawOn(DrawSurface draw) {
        draw.setColor(color);
        draw.fillRectangle(0, 0, draw.getWidth(), draw.getHeight());
    }

    /**
     * The function let's the sprite know that a unit of time has passed.
     *
     * @param dt a unit of time.
     */
    public void timePassed(double dt) {
    }
}
