package collision;

import collision.listeners.HitListener;

/**
 * The interface is used to add and remove the "HitListener" variables from a list.
 */
public interface HitNotifier {
    /**
     * The function adds a "HitListener" variable to the list.
     *
     * @param hl a "HitListener" variable to be added to a list.
     */
    void addHitListener(HitListener hl);

    /**
     * The function removes a "HitListener" variable to the list.
     *
     * @param hl a "HitListener" variable to be removed to a list.
     */
    void removeHitListener(HitListener hl);
}
