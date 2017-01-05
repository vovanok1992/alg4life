package datastructures;

/**
 * Created by Vovan on 05.01.2017.
 */
public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        Node<T> node = new Node<>();
        node.value = value;

        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public T getHeadVal() {
        return head.value;
    }

    public T getTailVal() {
        return tail.value;
    }

    public int getSize() {
        int size = 0;
        Node<T> currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.next;
            size++;
        }
        return size;
    }

    public T get(int index) {
        Node<T> currentNode = head;
        int currentIndex = 0;
        while (currentIndex != index && currentNode != null) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        if (currentIndex != index) {
            throw new IndexOutOfBoundsException("Size of collection (" + currentIndex + ") is smaller than your index");
        }

        return currentNode.value;
    }

    @Override
    public String toString() {
        String result = "["; // do not using StringBuilder since we on java 8
        Node<T> currentNode = head;
        while (currentNode != null) {
            result += currentNode.value.toString() + ", ";
            currentNode = currentNode.next;
        }
        return result.replaceAll("(\\, )$", "") + "]";
    }

    public MyLinkedList<T> reverse(){
        tail = head;
        Node<T> currentNode = head;
        Node<T> previous = null;

        while (currentNode != null){
            Node<T> next = currentNode.next;
            currentNode.next = previous;
            previous = currentNode;
            currentNode = next;
        }
        head = previous;
        return this;
    }

    /*
     * Skip getters and setters for simplicity
     */
    static class Node<T> {
        T value;
        Node next;
    }

}
