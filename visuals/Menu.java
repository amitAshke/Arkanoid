package visuals;

import game.Task;

/**
 * The interface is used to describe the menu methods in the game.
 *
 * @param <T> an anonymous variable.
 */
public interface Menu<T> extends Animation {
    /**
     * The function adds an option to the menu.
     *
     * @param key the option's key.
     * @param message the option's message.
     * @param returned the option's task.
     */
    void addSelection(String key, String message, Task returned);

    /**
     * The function returns the task or animation according to the what was chosen during it's animation .
     *
     * @return the task or animation according to the what was chosen during it's animation .
     */
    Object getStatus();

    /**
     * The function adds an sub-menu to the menu.
     *
     * @param key the sub-menu's key.
     * @param message the sub-menu's message.
     * @param subMenu the sub-menu's animation.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
