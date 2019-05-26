package collision;

import geometry.Point;

/**
 * The class represents 2 pieces of information regarding a collision. The point of collision and the object with
 * which there's a collision.
 */
public class CollisionInfo {
    private Point pointOfCollision;
    private Collidable object;

    /**
     * A constructor function that assigns bothe the collision point and the collision object.
     *
     * @param pointOfCollision a "Point" variable representing the collision point.
     * @param object           a "Collidable" variable representing the object with which there's a collision.
     */
    public CollisionInfo(Point pointOfCollision, Collidable object) {
        this.pointOfCollision = pointOfCollision;
        this.object = object;
    }

    /**
     * The function return the collision point.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.pointOfCollision;
    }

    /**
     * The function return the object with which there's a collision.
     *
     * @return the object with which there's a collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}