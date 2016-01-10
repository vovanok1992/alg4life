package sorts;

import annotations.Sort;
import org.junit.Before;
import org.junit.Test;
import utils.AnnotationHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author vovan on 09.01.2016.
 */
public class SortsTest {

    private int[] arrayForTest;

    @Before
    public void init(){
        arrayForTest = new Random().ints(100, 0, 1000).toArray();
    }

    @Test
    public void testMergeSort(){
        int[] result = getArrayForTest();
        Sorts.mergeSort(result);
        testSorting(result);
    }

    @Test
    public void testDefaultSort(){
        int[] result = getArrayForTest();
        Sorts.defaultSort(result);
        testSorting(result);
    }

    private void testSorting(int[] result) {
        int[] expected = getArrayForTest();
        Arrays.sort(expected);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAllSorts() {

        Collection<Method> methods = AnnotationHelper.findAllMethodsWithAnnotation(Sort.class, "sorts");
        methods.stream().forEach(method -> testSortReflect(method));

    }

    private void testSortReflect(Method method) {
        try {
            System.out.println("Testing " + method.getName() + " ...");
            int[] arrayForTest = getArrayForTest();
            method.invoke(new Object(), arrayForTest);
            testSorting(arrayForTest);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private int[] getCopy(int[] src){
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    public int[] getArrayForTest() {
        return getCopy(arrayForTest);
    }

}
