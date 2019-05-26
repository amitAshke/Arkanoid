package collision;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface is used to make sure that every collidable object in the game has the same functions.
 */
public interface Collidable {
    /**
     * The function returns the rectangle that visually represents the collidable.
     *
     * @return the rectangle that visually represents the collidable.
     */
    Rectangle getCollisionRectangle();

    /**
     * The function return a new velocity according the what collidable object the ball hit as well as the collision
     * point and the balls current velocity.
     *
     * @param hitter          the ball that collide with the "Collidable" object.
     * @param collisionPoint  a "Point" variable representing the point of collision
     * @param currentVelocity a "Velocity" variable representing the ball's current velocity.
     * @return a new velocity according the what collidable object the ball hit as well as the collision
     * point and the balls current velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}