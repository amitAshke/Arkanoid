package game;

/**
 * The interface is used for a each option's specific task.
 *
 * @param <T> an anonymous variable.
 */
public interface Task<T> {
    /**
     * The function runs the task.
     *
     * @return an anonymous variable.
     */
    T run();
}
