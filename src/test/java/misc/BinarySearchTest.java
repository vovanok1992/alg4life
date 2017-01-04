package misc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vovan on 04.01.2017.
 */
public class BinarySearchTest {

    @Test
    public void testPositionFinding(){
        int[] arr1 = {};
        int val1 = BinarySearch.findPosition(arr1, 1);
        assertEquals(-1, val1);

        int[] arr2 = {2, 2, 2, 2};
        int val2 = BinarySearch.findPosition(arr2, 1);
        assertEquals(-1, val2);

        int[] arr3 = {2};
        int val3 = BinarySearch.findPosition(arr3, 2);
        assertEquals(0, val3);

        int[] arr4 = {0,1,2,4,5,6,8,100};
        int val4 = BinarySearch.findPosition(arr4, 100);
        assertEquals(7, val4);

        int[] arr5 = {0,1,2,4,5,6,8,100,111,1111,2222,3333,4444};
        int val5 = BinarySearch.findPosition(arr5, 100);
        assertEquals(7, val5);

    }

}
