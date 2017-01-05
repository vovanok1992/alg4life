package datastructures;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Vovan on 05.01.2017.
 */
public class MyLinkedListTest {

    @Test
    public void basicTests(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.getSize());
        assertEquals("[]", list.toString());
        list.add(1);

        assertEquals("[1]", list.toString());

        assertEquals(1, (int) list.getHeadVal());
        assertEquals(1, (int) list.getTailVal());
        assertEquals(1, list.getSize());

        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(4, list.getSize());
        assertEquals(1, (int) list.getHeadVal());
        assertEquals(4, (int) list.getTailVal());
        assertEquals("[1, 2, 3, 4]", list.toString());

        assertEquals(3, (int) list.get(2));
        assertEquals(new LinkedList<Integer>(){{add(1); add(2); add(3); add(4);}}.toString(), list.toString());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testException(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.get(2);
    }


    @Test
    public void testOneWayReverse(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals("[4, 3, 2, 1]", list.reverse().toString());
        assertEquals(1, (int) list.getTailVal());
        MyLinkedList<Integer> list2 = new MyLinkedList<Integer>(){{add(1); add(2); add(3); add(4);}};

        String orig = list2.toString();

        assertEquals("[1, 2, 3, 4]", orig);

        assertEquals(orig, list2.reverse().reverse().toString());

        MyLinkedList<Integer> list3 = new MyLinkedList<>();
        assertEquals("[]", list3.reverse().toString());

        MyLinkedList<Integer> list4 = new MyLinkedList<>();
        list4.add(Integer.MAX_VALUE);
        assertEquals("["+Integer.MAX_VALUE+"]", list4.reverse().toString());
    }

}
