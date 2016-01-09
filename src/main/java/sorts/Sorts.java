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
    public static void mergeSort(int[] array) {
        int[] sortedArray = mergeSortRec(array);
        System.arraycopy(sortedArray, 0, array, 0, array.length);
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

        while (resultCount != result.length) {
            if (count1 < arr.length && count2 < arr2.length) {
                if (arr[count1] < arr2[count2]) {
                    result[resultCount++] = arr[count1++];
                } else {
                    result[resultCount++] = arr2[count2++];
                }
            } else if (count1 < arr.length) {
                result[resultCount++] = arr[count1++];
            } else if (count2 < arr2.length) {
                result[resultCount++] = arr2[count2++];
            }
        }
        return result;
    }


}
