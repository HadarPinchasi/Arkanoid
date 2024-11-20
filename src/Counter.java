//Hadar Pinchasi

/**
 * A utility class that maintains a count, allowing for incrementing and decrementing operations.
 */
public class Counter {
    private int val;

    /**
     * Constructs a Counter with an initial count value.
     *
     * @param val the initial count value.
     */
    public Counter(int val) {
        this.val = val;
    }
    /**
     * Increases the current count by a specified amount.
     *
     * @param number the amount to add to the count.
     */
    void increase(int number) {
        this.val = this.val + number;
    }

    /**
     * Decreases the current count by a specified amount.
     *
     * @param number the amount to subtract from the count.
     */
    void decrease(int number) {
        this.val = this.val - number;
    }
    /**
     * Returns the current count value.
     *
     * @return the current count.
     */
    int getValue() {
        return this.val;
    }
}
