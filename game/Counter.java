package game;

/**
 * The class represents numeric value with function to increase and decrease the value.
 */
public class Counter {
    private int value;

    /**
     * A constructor value that set the numeric value of the "Counter" variable.
     *
     * @param v the numeric value to be set.
     */
    public Counter(int v) {
        value = v;
    }

    /**
     * The function increases the value of the "Counter" variable by a set amount.
     *
     * @param number the set amount to be added to the "Counter" variable current.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * The function decreases the value of the "Counter" variable by a set amount.
     *
     * @param number the set amount to be subtracted from the "Counter" variable current.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * the function returns the value of the "Counter" variable.
     *
     * @return the value of the "Counter" variable.
     */
    public int getValue() {
        return this.value;
    }
}
