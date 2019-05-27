package visuals.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a collection of sprites that appears during the game using a "List" variable to contain
 * classes that implements the "visuals.sprites.Sprite" interface..
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * The function adds an additional sprite to the collection.
     *
     * @param s a variable of a class that implements the visuals.sprites.Sprite interface to be added to the
     *          collection of sprites.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The function returns the list of sprites.
     *
     * @return the list of sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * The function notify all of the sprites in the collection that a unit of time has passed. The function operates
     * in the order of last to first in the collection in order to notify the ball last.
     *
     * @param dt a unit of time.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = this.sprites.size() - 1; i >= 0; i--) {
            this.sprites.get(i).timePassed(dt);
        }
    }

    /**
     * The function activates the "drawOn" function of each sprite in the collection.
     *
     * @param d a "DrawSurface" variable used to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}
