package collision;

import biuoop.DrawSurface;
import collision.listeners.HitListener;
import game.GameLevel;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import visuals.sprites.Sprite;

import java.awt.Image;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * The class represents a block in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier, BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private Color border;
    private List<Object> fillings = new ArrayList<>();
    private Rectangle rectangle;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Point upperleft;
    private boolean isborder = false;

    /**
     * An empty constructor.
     */
    public Block() {
    }

    /**
     * A constructor containing a "Rectangle" variable from which several values are taken into members.
     *
     * @param rectangle1 the "Rectangle" variable from which several values are taken into members.
     */
    public Block(Rectangle rectangle1) {
        this.rectangle = rectangle1;
        this.upperleft = rectangle1.getUpperLeft();
        this.width = (int) rectangle1.getWidth();
        this.height = (int) rectangle1.getHeight();
        this.isborder = true;
    }

    /**
     * A constructor containing a "Block" variable from which several values are taken into members.
     *
     * @param newBlock the "Block" variable from which several values are taken into members.
     */
    public Block(Block newBlock) {
        this.width = newBlock.getWidth();
        this.height = newBlock.getHeight();
        this.hitPoints = newBlock.getHitPoints();
        this.border = newBlock.getBorder();
        this.fillings = newBlock.getFillings();
    }

    /**
     * The function receives an "int" variable to be set as the block's width.
     *
     * @param width1 the block's new width value.
     */
    public void setWidth(int width1) {
        this.width = width1;
    }

    /**
     * The function receives an "int" variable to be set as the block's height.
     *
     * @param height1 the block's new height value.
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * The function receives an "int" variable to be set as the block's hit points.
     *
     * @param hitPoints1 the block's new hit points value.
     */
    public void setHitPoints(int hitPoints1) {
        this.hitPoints = hitPoints1;
    }

    /**
     * The function receives an "Color" variable to be set as the block's edge color.
     *
     * @param border1 the block's new edge color.
     */
    public void setBorder(Color border1) {
        this.border = border1;
    }

    /**
     * The function receives an list of "Object" variable to be set as the block's filling.
     *
     * @param colorFillings a list that contain colors and images as the block's filling.
     */
    public void setFillings(List<Object> colorFillings) {
        fillings = colorFillings;
    }

    /**
     * The function returns the block's width.
     *
     * @return the block's width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * The function returns the block's height.
     *
     * @return the block's height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * The function returns the block's hit points.
     *
     * @return the block's hit points.
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * The function returns the block's edge color.
     *
     * @return the block's edge color.
     */
    public Color getBorder() {
        return this.border;
    }

    /**
     * The function returns the block's fillings.
     *
     * @return the block's fillings.
     */
    public List<Object> getFillings() {
        return this.fillings;
    }

    /**
     * The function returns the "Rectangle" value of the block.
     *
     * @return the "Rectangle" value of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The function receive a "Point" variable representing the ball's point of collision with the closest block (the
     * one that initiated this function) and a "Velocity" variably representing the ball's current velocity. The
     * function returns a new "Velocity" variable based on the ball's current velocity and the point of collision
     * on the block.
     *
     * @param hitter          The ball that collide with the block.
     * @param collisionPoint  the ball's point of collision with the block that initiated this function
     * @param currentVelocity the ball's current velocity.
     * @return a new "Velocity" variable based on the ball's current velocity and the point of collision
     * on the block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // The upper right point of the block.
        Point upperRight = new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                this.rectangle.getUpperLeft().getY());
        // The lower left point of the block.
        Point lowerLeft = new Point(this.rectangle.getUpperLeft().getX(), this.rectangle.getUpperLeft().getY() + this
                .rectangle.getHeight());
        // The lower right point of the block.
        Point lowerRight = new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight());
        this.notifyHit(hitter);
        // Checks if the collision occur anywhere but the corners.
        if (collisionPoint.isDifferent(this.rectangle.getUpperLeft()) && collisionPoint.isDifferent(lowerLeft)
                && collisionPoint.isDifferent(lowerRight) && collisionPoint.isDifferent(upperRight)) {
            if (isAlmostEqual(collisionPoint.getX(), this.rectangle.getUpperLeft().getX())
                    || isAlmostEqual(collisionPoint.getX(), upperRight.getX())) {
                return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            }
            if (isAlmostEqual(collisionPoint.getY(), this.rectangle.getUpperLeft().getY())
                    || isAlmostEqual(collisionPoint.getY(), lowerLeft.getY())) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            }
            return null;
        } else {
            /* Check with which corner does the collision occur and return a new velocity acording to the corner and
             the direction of the current velocity.*/
            if (collisionPoint.isSame(this.rectangle.getUpperLeft())) {
                if (currentVelocity.getDy() < 0) {
                    return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
                } else if (currentVelocity.getDx() < 0) {
                    return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                } else {
                    return new Velocity(currentVelocity.getDy() * -1, currentVelocity.getDx() * -1);
                }
            } else if (collisionPoint.isSame(upperRight)) {
                if (currentVelocity.getDy() < 0) {
                    return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
                } else if (currentVelocity.getDx() > 0) {
                    return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                } else {
                    return new Velocity(currentVelocity.getDy() * -1, currentVelocity.getDx() * -1);
                }
            } else if (collisionPoint.isSame(lowerRight)) {
                if (currentVelocity.getDy() > 0) {
                    return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
                } else if (currentVelocity.getDx() > 0) {
                    return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                } else {
                    return new Velocity(currentVelocity.getDy() * -1, currentVelocity.getDx() * -1);
                }
            } else if (collisionPoint.isSame(lowerLeft)) {
                if (currentVelocity.getDy() > 0) {
                    return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
                } else if (currentVelocity.getDx() < 0) {
                    return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                } else {
                    return new Velocity(currentVelocity.getDy() * -1, currentVelocity.getDx() * -1);
                }
            }
            return null;
        }
    }

    /**
     * The function draw the "BlockDefinitionReader" variable that called for the function based on it's color and
     * rectangle values. Additionally it draw either it's hit points value ot 'X' inside of the rectangle, based on
     * the value of it's hit points.
     *
     * @param surface a "DrawSurface" variable used to draw the block on the screen.
     */
    public void drawOn(DrawSurface surface) {
        if (isborder) {
            surface.setColor(rectangle.getColor());
            surface.fillRectangle((int) upperleft.getX(), (int) upperleft.getY(), width, height);
        } else if (fillings.get(hitPoints - 1) instanceof Color) {
            // Drawing the rectangle representing the block.
            surface.setColor((Color) fillings.get(hitPoints - 1));
            surface.fillRectangle((int) upperleft.getX(), (int) upperleft.getY(), width, height);
        } else if (fillings.get(hitPoints - 1) instanceof Image) {
            surface.drawImage((int) upperleft.getX(), (int) upperleft.getY(), (Image) fillings.get(hitPoints - 1));
        }
        if (border != null) {
            surface.setColor(border);
            surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft()
                            .getY(), (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }
    }

    /**
     * This function is used to inform the block that a unit of time has passed. Currently none of the block values
     * are based on the time passed, therefore the function is empty.
     *
     * @param dt A unit of time that has passed.
     */
    public void timePassed(double dt) {
    }

    /**
     * The function adds a "HitListener" variable to the block's list.
     *
     * @param hl a "HitListener" variable to be added to a list.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * The function removes a "HitListener" variable to the block's list.
     *
     * @param hl a "HitListener" variable to be added to a list.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * The function gives the block it's position.
     *
     * @param xpos the x coordinate.
     * @param ypos the y coordinate.
     * @return the block itself.
     */
    public Block create(int xpos, int ypos) {
        upperleft = new Point(xpos, ypos);
        this.rectangle = new Rectangle(upperleft, width, height);
        return this;
    }

    /**
     * The function notify all of the block's "HitListener" variables that a collision with the block occurred.
     *
     * @param hitter the ball that had collided with the block.
     */
    public void notifyHit(Ball hitter) {
        for (HitListener hl : this.hitListeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function reduces the block number of hit points by 1.
     */
    public void reduceHitPoints() {
        this.hitPoints -= 1;
    }

    /**
     * The function removes the block from the game.
     *
     * @param gameLevel the "GameLevel" variable from which the block is removed.
     */
    public void removeFromGame(GameLevel gameLevel) {

        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * The function checks if two number are different by 0.001 and returns a boolean variable accordingly.
     *
     * @param num1 the first number in the comparison.
     * @param num2 the second number in the comparison.
     * @return true or false based on the comparison mentioned earlier.
     */
    public boolean isAlmostEqual(double num1, double num2) {
        return Math.abs(num2 - num1) < 0.001;
    }
}
