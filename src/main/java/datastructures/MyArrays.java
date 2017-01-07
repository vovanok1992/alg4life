package datastructures;

/**
 * Created by Vovan on 07.01.2017.
 */
public class MyArrays {
    private MyArrays() {
    }

    public static int[] rotate(int[] array) {
        for (int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
}
