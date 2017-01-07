package datastructures;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Vovan on 07.01.2017.
 */
public class MyArraysTest {

    @Test
    public void reverseArrayTest() {
        assertTrue(Arrays.equals(new int[]{1, 2, 3}, MyArrays.rotate(new int[]{3, 2, 1})));
        assertTrue(Arrays.equals(new int[]{1, 2}, MyArrays.rotate(new int[]{2, 1})));
        assertTrue(Arrays.equals(new int[]{1}, MyArrays.rotate(new int[]{1})));
        assertTrue(Arrays.equals(new int[]{}, MyArrays.rotate(new int[]{})));
    }

}
