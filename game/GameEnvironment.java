package game;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Line;
import collision.Collidable;
import collision.CollisionInfo;

/**
 * The class represents the collective list of objects with which the ball can collide with.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * The function adds a class that implements the "Collidable" interface to the list.
     *
     * @param c the class that implements the "Collidable" interface.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The function return the entire list of collidable object.
     *
     * @return the entire list of collidable object.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * The function receives a "Line" variable and returns a "CollisionInfo" variable containing the closest
     * collision point and the object with which the collision occurs. It does so by finding all of the intersection
     * points between the "Line" variable and the collidable object in the list and pick the one who's intersection
     * point is closest to the star of the "Line" variable.
     *
     * @param trajectory a "Line" variableto be used in the function to find the closest collision point.
     * @return a "CollisionInfo" variable containing the closest collision point and the object with which the
     * collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Minimum distance is 1000 since the window size is 500x500.
        double minDistance = 10000;
        int collisionObjectIndex = -1;
        Point minDistPoint = trajectory.closestIntersectionToStartOfLine((this.collidables.get(0))
                .getCollisionRectangle());
        // Checks if there is a collision with the first collidable in the list.
        if (minDistPoint != null) {
            // Set first base distance.
            minDistance = trajectory.start().distance(minDistPoint);
            // Keep the collidable's index.
            collisionObjectIndex = 0;
        }
        // A 'for' loop to make the same check with the other collidables in the list.
        for (int i = 1; i < this.collidables.size(); i++) {
            // Checks in case there's no collision with several collidables in the beginning of the list.
            if (trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle()) != null) {
                if (minDistPoint == null) {
                    minDistPoint = trajectory.closestIntersectionToStartOfLine((this.collidables.get(i))
                            .getCollisionRectangle());
                    minDistance = trajectory.start().distance(minDistPoint);
                    collisionObjectIndex = i;
                    continue;
                }
                Point comparePoint = trajectory.closestIntersectionToStartOfLine((this.collidables.get(i))
                        .getCollisionRectangle());
                // Find the distance between to the other collision point.
                double compareDistance = trajectory.start().distance(comparePoint);
                // Checks if the new distance found is shorter than the one in the "minimum" variable.
                if (compareDistance < minDistance) {
                    minDistPoint = comparePoint;
                    minDistance = compareDistance;
                    collisionObjectIndex = i;
                }
            }
        }
        // If not collision point was found.
        if (minDistPoint == null) {
            return new CollisionInfo(null, null);
        }
        //Otherwise return the collision point and collidable in a "CollisionInfo" variable.
        return new CollisionInfo(minDistPoint, this.collidables.get(collisionObjectIndex));
    }
}
