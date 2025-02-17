package list;

public class LinkedList implements List {
    Node head;

    class Node {
        Object data;
        Node next;

        // constructor
        Node(Object d) {
            data = d;
            next = null;
        }
    }
    @Override
    public void init(int n) {
        head = new Node(null);
        Node first = new Node(10);
        head.next = first;
        Node next;
        for (int i = 0; i < n-1; i++) {
            next = new Node(i);
            first.next = next;
            first = next;
        }

    }

    @Override
    public void put(int i, Object x) {
        Node ele = head;
        for (int j = 0; j < i; j++) {
            ele = ele.next;
        }
        ele.next.data = x;
    }

    @Override
    public Object get(int i) {
        Node ele = head;
        for (int j = 0; j < i; j++) {
            ele = ele.next;
        }
        return ele.next.data;
    }

    @Override
    public int length() {
        int c = 0;
        Node ele = head;
        while(ele.next != null){
            c++;
            ele = ele.next;
        }
        return c;
    }
}
