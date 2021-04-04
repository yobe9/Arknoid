package observers;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class Counter {
    //fields
    private int sum;

    /**
     * Constructor.
     *
     * @param sum of the counter
     */
    public Counter(int sum) {
        this.sum = sum;
    }

    /**
     * add number to current count.
     *
     * @param number of how many to increase
     */
    public void increase(int number) {
        this.sum = this.sum + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number of how many to decrease
     */
    public void decrease(int number) {
        this.sum = this.sum - number;
    }

    //

    /**
     * get current count.
     *
     * @return the value of the counter
     */
    public int getValue() {
        return this.sum;
    }
}
