package sorts;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author vovan on 09.01.2016.
 */
public class SortsTest {

    @Test
    public void testMergeSort(){
        int[] array = new Random().ints(100, 0, 1000).toArray();

        int[] result = getCopy(array);
        Sorts.mergeSort(result);

        assertEquals(array.length, result.length);
        for(int i = 1; i < result.length; i++){
            assertTrue(result[i-1] <= result[i]);
        }
    }

    private int[] getCopy(int[] src){
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

}
