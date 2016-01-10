package sorts;

import annotations.Sort;

import java.util.*;

/*
 *
 */
public class Sorts {

    private Sorts() {
    }

    @Sort
    public static void defaultSort(int[] array) {
        Arrays.sort(array);
    }

    @Sort
    public static void bubbleSort(int[] array) {
        for(int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length - 1; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    @Sort
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    @Sort
    public static void gnomeSort(int[] array) {
        int i = 0;
        while (i < array.length) {
            if (i == 0 || array[i-1] <= array[i]) i++;
            else {int tmp = array[i]; array[i] = array[i-1]; array[--i] = tmp;}
        }
    }

    @Sort
    public static void mergeSort(int[] array) {
        int[] sortedArray = mergeSortRec(array);
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }

    /**
     * Because the algorithm uses only simple for loops, without recursion or subroutine calls, it is straightforward to
     * analyze. The initialization of the count array, and the second for loop which performs a prefix sum on the count
     * array, each iterate at most k + 1 times and therefore take O(k) time. The other two for loops, and the
     * initialization of the output array, each take O(n) time. Therefore the time for the whole algorithm is the sum
     * of the times for these steps, O(n + k).
     * <p>
     * Because it uses arrays of length k + 1 and n, the total space usage of the algorithm is also O(n + k).
     * For problem instances in which the maximum key value is significantly smaller than the number of items,
     * counting sort can be highly space-efficient, as the only storage it uses other than its input and output arrays
     * is the Count array which uses space O(k).
     *
     * @see <a href="https://en.wikipedia.org/wiki/Counting_sort">Counting_sort</a>
     */
    @Sort
    public static void countingSort(int[] array) {
        int maxVal = Arrays.stream(array).max().getAsInt();
        int minVal = Arrays.stream(array).min().getAsInt();
        countingSort(array, minVal, maxVal);
    }

    public static void countingSort(int[] array, int min, int max) {
        int[] count = new int[max - min + 1];
        for (int number : array) {
            count[number - min]++;
        }
        int z = 0;
        for (int i = min; i <= max; i++) {
            while (count[i - min] > 0) {
                array[z] = i;
                z++;
                count[i - min]--;
            }
        }
    }

    private static int[] mergeSortRec(int[] array) {
        if (array.length < 2) return array;
        return merge(mergeSortRec(Arrays.copyOfRange(array, 0, array.length / 2)),
                mergeSortRec(Arrays.copyOfRange(array, (array.length / 2) , array.length)));
    }

    private static int[] merge(int[] arr, int[] arr2) {
        int[] result = new int[arr.length + arr2.length];
        int count1 = 0;
        int count2 = 0;
        int resultCount = 0;

        while (count1 < arr.length && count2 < arr2.length) {
            if (arr[count1] < arr2[count2]) {
                result[resultCount++] = arr[count1++];
            } else {
                result[resultCount++] = arr2[count2++];
            }
        }

        if (count1 < arr.length) {
            System.arraycopy(arr, count1, result, resultCount, arr.length - count1);
        } else {
            System.arraycopy(arr2, count2, result, resultCount, arr2.length - count2);
        }

        return result;
    }


}
