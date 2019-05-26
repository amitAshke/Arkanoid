package collision;

import geometry.Point;

/**
 * The class represents both horizontal and vertical velocity in a 2D plain.
 */
public class Velocity {
    // The variable representing horizontal velocity.
    private double dx;
    // The variable representing vertical velocity.
    private double dy;

    /**
     * A constructor function that assign the variables "dx" and "dy" to "dx" and "dy respectively.
     *
     * @param dx a numeric value of horizontal velocity.
     * @param dy a numeric value of vertical velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * A constructor function that assign the variables to "dx" and "dy" according to "angle" and "speed".
     *
     * @param angle a numeric value representing the angle from the direction upwards and clockwise.
     * @param speed a numeric value representing the speed in any direction.
     */
    public void fromAngleAndSpeed(double angle, double speed) {
        this.dx = Math.sin(Math.toRadians(angle)) * speed;
        this.dy = Math.cos(Math.toRadians(angle)) * speed;
    }

    /**
     * The function return the numeric value of "dx".
     *
     * @return the value of "dx".
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The function return the numeric value of "dy".
     *
     * @return the value of "dy".
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The function return a "Point" variable according to the "Point" variable it received and the values of "dx" and
     * "dy".
     *
     * @param p a "Point" variable that will make the new "Point" variable would be based on.
     * @param dt a unit of time that has passed.
     * @return a "Point" with new 'x' and 'y' according to "dx" and "dy".
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
    }
}
