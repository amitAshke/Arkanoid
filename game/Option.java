package game;

/**
 * The function represents an option in a menu that doesn't lead to a sub menu.
 */
public class Option {
     private String key;
     private String message;
     private Task status;

    /**
     * A constructor function.
     *
     * @param key1 the key that is needed for the specific option.
     * @param message1 the message that is displayed in the menu for that specific option.
     */
    public Option(String key1, String message1) {
        this.key = key1;
        this.message = message1;
    }

    /**
     * A constructor function.
     *
     * @param key1 the key that is needed for the specific option.
     * @param message1 the message that is displayed in the menu for that specific option.
     * @param status1 the task that the variable does once the specific "key" is pressed.
     */
     public Option(String key1, String message1, Task status1) {
         this.key = key1;
         this.message = message1;
         this.status = status1;
     }

    /**
     * The function sets the task that the variable does once the specific "key" is pressed.
     *
     * @param status1 the task that the variable does once the specific "key" is pressed.
     */
    public void setStatus(Task status1) {
        this.status = status1;
    }

    /**
     * The function returns the key that is needed for the specific option.
     *
     * @return the key that is needed for the specific option.
     */
    public String getKey() {
        return key;
    }

    /**
     * The function returns the message that is displayed in the menu for that specific option.
     *
     * @return the message that is displayed in the menu for that specific option.
     */
    public String getMessage() {
        return message;
    }

    /**
     * The function returns the task that the variable does once the specific "key" is pressed.
     *
     * @return the task that the variable does once the specific "key" is pressed.
     */
    public Task getStatus() {
        return status;
    }
}
