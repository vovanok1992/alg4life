package sorts;

import annotations.Sort;

import java.util.Random;

/*
 *
 */
public class Sorts{

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

    public static void main(String[] args) {
        int[] array = new Random().ints(100, 0, 1000).toArray();
        insertionSort(array);
    }
}
