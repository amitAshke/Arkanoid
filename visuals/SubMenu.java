package visuals;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Option;
import game.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a sub-menu in the game.
 */
public class SubMenu implements Menu<Task> {
    private String key;
    private String message;
    private List<Option> options;
    private List<SubMenu> subMenus;
    private MenuAnimation menu;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private boolean stopeAnimation = false;
    private Object returned;

    /**
     * A constructor function.
     *
     * @param keyPressed The key that is required to be pressed in order to run the animation of the sub-menu.
     * @param gameMessage The message that tells the player what the submenu does.
     * @param subMenu The animation of the sub-menu itself.
     * @param animationRunner1 an "AnimationRunner" variable to run the sub-menu's animation.
     * @param keyboardSensor1 a "KeyboardSensor" variable to stop the animation.
     */
    public SubMenu(String keyPressed, String gameMessage, MenuAnimation subMenu, AnimationRunner animationRunner1,
                   KeyboardSensor keyboardSensor1) {
        this.key = keyPressed;
        this.message = gameMessage;
        this.menu = subMenu;
        this.animationRunner = animationRunner1;
        this.keyboardSensor = keyboardSensor1;
        this.options = new ArrayList<>();
        this.subMenus = new ArrayList<>();
    }

    /**
     * The function returns the sub-menu's key.
     *
     * @return the sub-menu's key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * The function returns the sub-menu's message.
     *
     * @return the sub-menu's message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * The function returns the sub-menu's animation.
     *
     * @return the sub-menu's animation.
     */
    public MenuAnimation getMenu() {
        return menu;
    }

    /**
     * The function adds an option to the subMenu.
     *
     * @param key1 the option's key.
     * @param message1 the option's message.
     * @param returned1 the option's task.
     */
    public void addSelection(String key1, String message1, Task returned1) {
        options.add(new Option(key1, message1, returned1));
    }

    /**
     * The function adds an sub-menu to the subMenu.
     *
     * @param key1 the sub-menu's key.
     * @param message1 the sub-menu's message.
     * @param returned1 the sub-menu's animation.
     */
    public void addSubMenu(String key1, String message1, Menu returned1) {
        subMenus.add(new SubMenu(key1, message1, (MenuAnimation) returned1, animationRunner, keyboardSensor));
    }

    /**
     * The function draw a frame of the sub-menu's animation.
     *
     * @param draw a "DrawSurface" variable used to draw the frame.
     * @param dt a unit of time.
     */
    public void doOneFrame(DrawSurface draw, double dt) {
        draw.drawText(draw.getWidth() / 4, draw.getHeight() / 8, message, 64);
        for (int index = 0; index < subMenus.size(); index++) {
            draw.drawText(draw.getWidth() / 4, draw.getHeight() / 8 + 64 + 48 * index, "[" + subMenus.get(index)
                    .getKey() + "] " + subMenus.get(index).getMessage(), 32);
        }
        for (int index = 0; index < subMenus.size(); index++) {
            if (keyboardSensor.isPressed(subMenus.get(index).getKey())) {
                returned = subMenus.get(index).getMenu();
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
                returned = options.get(index).getStatus();
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
        return this.returned;
    }

    /**
     * The function is used to decide when the sub-menu's animation will stop.
     *
     * @return the boolean that decide when the sub-menu's animation will stop.
     */
    public boolean shouldStop() {
        return stopeAnimation;
    }
}
