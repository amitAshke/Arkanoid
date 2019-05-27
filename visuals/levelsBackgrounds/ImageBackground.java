package visuals.levelsBackgrounds;

import biuoop.DrawSurface;
import visuals.sprites.Sprite;

import java.awt.Image;

/**
 * A class that represents an image as a background for a level.
 */
public class ImageBackground implements Sprite {
    private Image image;

    /**
     * A constructor function.
     *
     * @param background the image that will serve as a background for a level.
     */
    public ImageBackground(Image background) {
        this.image = background;
    }

    /**
     * The function draw the background.
     *
     * @param draw a "DrawSurface" variable used to draw the image as background.
     */
    public void drawOn(DrawSurface draw) {
        draw.drawImage(0, 0, image);
    }

    /**
     * The function let's the sprite know that a unit of time has passed.
     *
     * @param dt a unit of time.
     */
    public void timePassed(double dt) {
    }
}
