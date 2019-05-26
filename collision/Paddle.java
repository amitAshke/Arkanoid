package collision;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Rectangle;
import geometry.Point;
import geometry.Ball;

import java.awt.Color;

import visuals.sprites.Sprite;
import game.GameLevel;

/**
 * The class represents the paddle in the game with a "GUI" variable representing the game window, a "KeyboardSensor"
 * to allow the player to control it's movement, a "Rectangle" variable it's visual representation on the screen, a
 * "Color" variable for it's color and a "int" variable representing the movement speed of the paddle in a single
 * unit of time.
 */
public class Paddle implements Sprite, Collidable {
    private GUI gui;
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int paddleSpeed;
    private int leftLimit;
    private int rightLimit;

    /**
     * A constructor function that applies values to the paddle's rectangle, color and speed.
     *
     * @param upperLeft a "Point" variable to be set to the upper left point of the "Rectangle" variable.
     * @param width     a "double" variable to be set to the width of the "Rectangle" variable.
     * @param height    the a "double" variable to be set to  height of the "Rectangle" variable.
     * @param color     a "Color" variable to be set to the paddle's color.
     * @param speed     an "int" variable to be set to the paddle's speed.
     */
    public Paddle(Point upperLeft, double width, double height, Color color, int speed) {
        this.paddle = new Rectangle(upperLeft, width, height, color);
        this.color = color;
        this.paddleSpeed = speed;
    }

    /**
     * A constructor function to set the paddle's GUI, and set the paddle's "KeyboardSensor" accordingly.
     *
     * @param gu to be set as the paddle's GUI.
     */
    public void setGui(GUI gu) {
        this.gui = gu;
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * The function sets the paddle limits of movements.
     *
     * @param left  the left limit of the paddle's movment.
     * @param right the right limit of the paddle's movment.
     */
    public void setMovementLimits(int left, int right) {
        this.leftLimit = left;
        this.rightLimit = right;
    }

    /**
     * The function returns the "Rectangle" value of the paddle.
     *
     * @return the "Rectangle" value of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * The function returns the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    public int getPaddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function moves the paddle to the left according to the it's speed and "Rectangle" values.
     *
     * @param dt a unit of time that has passed.
     */
    public void moveLeft(double dt) {
        // Checks if the paddle won't reach the left border block in the next step.
        if (this.paddle.getUpperLeft().getX() - this.paddleSpeed * dt > this.leftLimit) {
            // Move the paddle to the left according to it's own speed.
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() - this.paddleSpeed * dt,
                    this.paddle.getUpperLeft().getY()));
        } else {
            // Otherwise place the paddle where it touches the left border block
            this.paddle.setUpperLeft(new Point(this.leftLimit, this.paddle.getUpperLeft().getY()));
        }

    }

    /**
     * The function moves the paddle to the right according to the it's speed and "Rectangle" values.
     *
     * @param dt a unit of time that has passed.
     */
    public void moveRight(double dt) {
        // The upper right corner point of the paddle.
        Point upperRight = new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth(),
                this.paddle.getUpperLeft().getY());
        // Checks if the paddle won't reach the right border block in the next step.
        if (upperRight.getX() + this.paddleSpeed * dt < this.rightLimit) {
            // Move the paddle to the right according to it's own speed.
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() + this.paddleSpeed * dt,
                    this.paddle.getUpperLeft().getY()));
        } else {
            // Otherwise place the paddle where it touches the right border block
            this.paddle.setUpperLeft(new Point(this.rightLimit - this.paddle.getWidth(), this.paddle.getUpperLeft()
                    .getY()));
        }
    }

    /**
     * This function is used to inform the paddle that a unit of time has passed. According to the button press on
     * the keyboard the function will wither tell the paddle to move right or left.
     *
     * @param dt a unit of time that has passed.
     */
    public void timePassed(double dt) {
        // Checks if the "left arrow key" is pressed.
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // If so than activate the function that moves the paddle left.
            moveLeft(dt);
            // Otherwise checks if th "right arrow key" is pressed.
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // If so than activate the function that moves the paddle right.
            moveRight(dt);
        }
    }

    /**
     * The function draw the "Paddle" variable that called for the function based on it's color and rectangle values.
     *
     * @param surface a "DrawSurface" variable used to draw the block on the screen.
     */
    public void drawOn(DrawSurface surface) {
        // Sets the "DrawSurface" variable's color to the paddle's
        surface.setColor(this.color);
        // Draw the rectangle that visually represents the paddle.
        surface.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * The function receive a "Point" variable representing the ball's point of collision with the top of the paddle
     * and a "Velocity" variably representing the ball's current velocity. The function returns a new "Velocity"
     * variable based on the ball's current velocity and the point of collision on the paddle.
     *
     * @param hitter the ball the collide with the paddle.
     * @param collisionPoint  the ball's point of collision with the top of the paddle.
     * @param currentVelocity the ball's current velocity.
     * @return a new "Velocity" variable based on the ball's current velocity and the point of collision
     * on the top of the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // The Distance from the collision point to the upper left point of the paddle
        double positionOnPaddle = collisionPoint.getX() - this.paddle.getUpperLeft().getX();
        // Calculate the total size of the velocity.
        double totalVelocity = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // Checks if the the collision Point is the top part of the paddle.
        if (isAlmostEqual(this.paddle.getUpperLeft().getY(), collisionPoint.getY())) {
            for (int i = 1; i <= 5; i++) {
                // Check with what part of the paddle does the collision occur with.
                if (positionOnPaddle <= this.paddle.getWidth() / 5 * i) {
                    // Checks if it's the middle part.
                    if (i == 3) {
                        // If so than switch the direction of the vertical velocity.
                        return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                    } else {
                        // Otherwise game the velocity an angle according the the specific part on the paddle.
                        return new Velocity(totalVelocity * Math.sin(Math.toRadians(-90 + 30 * i)),
                                totalVelocity * -1 * Math.cos(Math.toRadians(-90 + 30 * i)));
                    }
                }
            }
        }
        return currentVelocity;
    }

    /**
     * The function checks if a point is inside the paddle's rectangle.
     *
     * @param point a "Point" variable.
     * @return true or false depending on whether or not the ball's center is inside the paddle's rectangle.
     */
    public boolean isPointInside(Point point) {
        // The upper right point of the paddle.
        Point upperRight = new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth(),
                this.paddle.getUpperLeft().getY());
        // The lower left Point of the paddle.
        Point lowerLeft = new Point(this.paddle.getUpperLeft().getX(),
                this.paddle.getUpperLeft().getY() + this.paddle.getHeight());
        // Checks if the point is inside the paddle's rectangle.
        return (this.paddle.getUpperLeft().getX() < point.getX() && point.getX() < upperRight.getX()
                && this.paddle.getUpperLeft().getY() < point.getY() && point.getY() < lowerLeft.getY());
    }

    /**
     * The function checks if the collision point is on the side of the paddle.
     *
     * @param collisionPoint the collision point of the ball with the paddle.
     * @return true or false according to the check mentioned earlier.
     */
    public boolean isBallHitSide(Point collisionPoint) {
        // The upper right point of the paddle.
        Point upperRight = new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth(),
                this.paddle.getUpperLeft().getY());
        // The lower left Point of the paddle.
        Point lowerLeft = new Point(this.paddle.getUpperLeft().getX(),
                this.paddle.getUpperLeft().getY() + this.paddle.getHeight());
        // Checks if the collision point is on the side of the paddle.
        return ((isAlmostEqual(collisionPoint.getX(), this.paddle.getUpperLeft().getX())
                || isAlmostEqual(collisionPoint.getX(), upperRight.getX()))
                && this.paddle.getUpperLeft().getY() < collisionPoint.getY()
                && collisionPoint.getY() < lowerLeft.getY());
    }

    /**
     * The function create a new velocity to be used as the ball's new velocity if it hits the side of the paddle or
     * is inside the paddle.
     *
     * @param ball the game's ball.
     * @return a new velocity to be used as the ball's new velocity if it hits the side of the paddle or
     * is inside the paddle. the horizontal velocity the will be made if bigger than the paddle's in order to make
     * sure the paddle can continually hold the ball inside of it.
     */
    public Velocity adjustBallVel(Ball ball) {
        if (ball.getVelocity().getDx() > 0 || (ball.getVelocity().getDx() < 0 && this.paddleSpeed > ball
                .getVelocity().getDx() * -1)) {
            return new Velocity(-1 * this.paddleSpeed - 1, ball.getVelocity().getDy());
        } else if (ball.getVelocity().getDx() < 0 || (ball.getVelocity().getDx() > 0 && this.paddleSpeed > ball
                .getVelocity().getDx() * -1)) {
            return new Velocity(this.paddleSpeed + 1, ball.getVelocity().getDy());
        } else {
            return ball.getVelocity();
        }
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

    /**
     * The function adds the paddle to the "game.GameLevel" sprites and collidables.
     *
     * @param g a "game.GameLevel" variable.
     */
    public void addToGame(GameLevel g) {
        g.getSpriteCollection().addSprite(this);
        g.getEnvironment().getCollidables().add(this);
    }
}
