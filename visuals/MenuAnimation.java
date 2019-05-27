package visuals;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import game.Option;
import game.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * The class represents a menu in the game.
 */
public class MenuAnimation implements Menu {
    private String nameOfStage;
    private Object status;
    private List<Option> options;
    private List<SubMenu> subMenus;
    private boolean stopeAnimation = false;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    /**
     * A constructor function.
     *
     * @param stageName The message that tells the player what the submenu does.
     * @param sensor a "KeyboardSensor" variable to stop the animation.
     * @param runner an "AnimationRunner" variable to run the sub-menu's animation.
     */
    public MenuAnimation(String stageName, KeyboardSensor sensor, AnimationRunner runner) {
        this.nameOfStage = stageName;
        this.keyboardSensor = sensor;
        this.animationRunner = runner;
        this.options = new ArrayList<>();
        this.subMenus = new ArrayList<>();
    }

    /**
     * The function adds an option to the menu.
     *
     * @param key the option's key.
     * @param message the option's message.
     * @param returned the option's task.
     */
    public void addSelection(String key, String message, Task returned) {
        options.add(new Option(key, message, returned));
    }

    /**
     * The function adds an option to the menu.
     *
     * @param newOption adds an option to the menu.
     */
    public void addSelection(Option newOption) {
        options.add(newOption);
    }

    /**
     * The function adds an sub-menu to the menu.
     *
     * @param key the sub-menu's key.
     * @param message the sub-menu's message.
     * @param returned the sub-menu's animation.
     */
    public void addSubMenu(String key, String message, Menu returned) {
        subMenus.add(new SubMenu(key, message, (MenuAnimation) returned, animationRunner, keyboardSensor));
    }

    /**
     * The function draw a frame of the menu's animation.
     *
     * @param draw a "DrawSurface" variable used to draw the frame.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface draw, double dt) {
        draw.drawText(draw.getWidth() / 4, draw.getHeight() / 8, nameOfStage, 64);
        for (int index = 0; index < subMenus.size(); index++) {
            draw.drawText(draw.getWidth() / 4, draw.getHeight() / 8 + 64 + 48 * index, "[" + subMenus.get(index)
                    .getKey() + "] " + subMenus.get(index).getMessage(), 32);
        }
        for (int index = 0; index < subMenus.size(); index++) {
            if (keyboardSensor.isPressed(subMenus.get(index).getKey())) {
                status = subMenus.get(index).getMenu();
                stopeAnimation = true;
                break;
            }
        }
        for (int index = 0; index < options.size(); index++) {
             draw.drawText(draw.getWidth() / 4, draw.getHeight() / 8 + 112 + 48 * index, "[" + options.get(index)
                    .getKey() + "] " + options.get(index).getMessage(), 32);
        }
        for (int index = 0; index < options.size(); index++) {
            if (keyboardSensor.isPressed(options.get(index).getKey())) {
                status = options.get(index).getStatus();
                stopeAnimation = true;
                break;
            }
        }
    }

    /**
     * The function returns the task or animation according to the what was chosen during it's animation .
     *
     * @return the task or animation according to the what was chosen during it's animation .
     */
    public Object getStatus() {
        return status;
    }

    /**
     * The function is used to decide when the sub-menu's animation will stop.
     *
     * @return the boolean that decide when the sub-menu's animation will stop.
     */
    public boolean shouldStop() {
        return stopeAnimation;
    }

    /**
     * The function resets the variable used to stop the animation and the status that was chosen.
     */
    public void reset() {
        this.status = null;
        this.stopeAnimation = false;
    }
}
