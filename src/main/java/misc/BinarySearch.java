package misc;

/**
 * Created by Vovan on 04.01.2017.
 */

/**
 * Binary search runs in at worst logarithmic time, making O(log n) comparisons, where n is the number of elements in
 * the array and log is the logarithm. Binary search takes only constant (O(1)) space, meaning that the space taken by
 * the algorithm is the same for any number of elements in the array.[5] Although specialized data structures designed
 * for fast searching—such as hash tables—can be searched more efficiently, binary search applies to a wider range of
 * search problems.
 */
public class BinarySearch {

    private BinarySearch() {
    }

    public static int findPosition(int[] array, int value) {
        return findPosition(array, value, 0, array.length - 1);
    }

    public static int findPosition(int[] array, int value, int l, int r) {
        if (l > r) {
            return -1;
        }
        int m = (l + r) / 2;
        if (value < array[m]) {
            return findPosition(array, value, l, m - 1);
        } else if (value > array[m]) {
            return findPosition(array, value, m + 1, r);
        } else {
            return m;
        }
    }
}

