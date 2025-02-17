package array;

public class PovezanSeznam implements PosplosenaTabela {
    Node head; // prvi elemnt v seznamu
    int z;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }


    @Override
    public void init(int n) {
        z=0;
    }

    @Override
    public boolean insert(int x) {
        z++;
        Node new_node = new Node(x);
        new_node.next = head;
        head = new_node;
        return true;
    }

    @Override
    public int get(int i) {
        Node ele = head;
        int c = 0;
        while(c < i){
            ele = ele.next;
            c++;
        }
        if(ele != null)
            return ele.data;
        return -1;
    }

    @Override
    public int find(int x) {
        Node ele = head;
        int c = 0;
        while(ele != null){
            if(ele.data == x)
                return c;
            ele = ele.next;
            c++;
        }
        return -1;
    }

    @Override
    public boolean delete(int x) {
        Node ele = head;
        z--;
        if(ele.data == x) {
            head = ele.next;
            return true;
        }
        while(ele.next != null){
            if(ele.next.data == x) {
                ele.next = ele.next.next;
                return true;
            }
            ele = ele.next;
        }
        return false;
    }

    @Override
    public int length() {
        return z;
    }

    @Override
    public int size() {
        return z;
    }
    public void print(){
        Node ele = head;
        for (int i = 0; i < z; i++) {
            System.out.println(ele.data);
            ele = ele.next;
        }
    }
}
