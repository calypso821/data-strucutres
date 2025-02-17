package Izzivi.Izziv1;

public class Izziv1 {
    public static void main (String[] args) throws CollectionException {
        /*
        Stack<String> s = new ArrayDeque<>();
        Deque<String> d = new ArrayDeque<>();
        Sequence<String> z = new ArrayDeque<>();
        d.enqueue("A");
        System.out.println(d.front());
        System.out.println(d.back());

        d.dequeue();
        d.enqueueFront("A");
        System.out.println(d.front());
        System.out.println(d.back());

        s.push("A");

        s.push("B");
        s.push("C");
        s.push("D");
        s.push("E");
        s.push("F");
        System.out.println(s);

        System.out.println("Stack: ");
        while(!s.isEmpty()) {
            System.out.println(s.top() + " ");
            d.enqueueFront(s.pop());
        }
        System.out.println("Deque: ");
        while(!d.isEmpty()) {
            System.out.println(d.front() + " ");
            z.add(d.dequeue());
        }
        System.out.println("Sequence: ");
        for (int i = 0; i < z.size(); i++) {
            System.out.println((i+1) + "."+z.get(i)+" ");

        }
        System.out.println(z.get(5));

         */





        Stack<String> s = new ArrayDeque<>();
        Deque<String> d = new ArrayDeque<>();
        Sequence<String> z = new ArrayDeque<>();

        System.out.println("----------------------------------------------------------------");
        System.out.println("DEQUE:");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enqueue:");
        d.enqueue("a");
        d.enqueue("b");
        d.enqueue("c");
        System.out.println(d + " -> Should be: [a, b, c]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Front and Back:");
        System.out.println(d);
        System.out.println("Back: " + d.back() + " -> Should be: c");
        System.out.println("Front: " + d.front() + " -> Should be: a");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enqueue Front:");
        System.out.println(d);
        d.enqueueFront("x");
        System.out.println(d);
        System.out.println(d.front() + " -> Should be: x");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Dequeue Back:");
        System.out.println(d);
        d.dequeueBack();
        System.out.println(d);
        System.out.println(d.back() + " -> Should be: b");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Dequeue:");
        System.out.println(d);
        String removed = d.dequeue();
        System.out.println(d);
        System.out.println(removed + " -> Should be: x");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("STACK:");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Push:");
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println(s + " -> Should be: [A, B, C]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Top:");
        System.out.println(s);
        String topElement = s.top();
        System.out.println(topElement + " -> Should be: C");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pop:");
        System.out.println(s);
        s.pop();
        System.out.println(s + "-> Should be: [A, B]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("SEQUENCE:");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Add:");
        z.add("a");
        z.add("b");
        z.add("c");
        for(int i = 0; i < z.size(); i++)
            System.out.print((i) + ". " + z.get(i) + " ");
        System.out.println(" -> Should be: 0. a 1. b 2. c");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Get:");
        String getElement = z.get(0);
        System.out.println(getElement + " -> Should be: a");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        z.add(0, "a1");
        z.add(4, "c2");
        System.out.println(z);
        System.out.println(z.set(1, "new"));
        System.out.println(z);
        System.out.println(z.indexOf("c1"));
        System.out.println(z.remove("b"));
        System.out.println(z);
        System.out.println(z.remove(2));
        System.out.println(z);
        System.out.println(z.remove(0));
        System.out.println(z);
        System.out.println(z.remove(0));
        System.out.println(z);
        System.out.println(z.remove(0));
        System.out.println(z);



    }
}

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}


interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}


interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}


interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}


interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";

    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
    void add(int i, T x) throws CollectionException;
    T set (int i, T x) throws CollectionException;
    int indexOf(T x) throws CollectionException;
    T remove(int i) throws CollectionException;
    T remove(T x) throws CollectionException;
}

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] tab;
    private int num_ele;
    private int front;
    private int back;

    ArrayDeque() {
        this.tab = (T[]) new Object[DEFAULT_CAPACITY];
        this.front = DEFAULT_CAPACITY-1;
        this.back = 0;
        this.num_ele = 0;
    }
    private int next(int index) {
        return (DEFAULT_CAPACITY + index + 1) % DEFAULT_CAPACITY;
    }
    private int prev(int index) {
        return (DEFAULT_CAPACITY + index - 1) % DEFAULT_CAPACITY;
    }


    @Override
    public boolean isEmpty() {
        return num_ele == 0;
    }

    @Override
    public boolean isFull() {
        return num_ele == DEFAULT_CAPACITY;
    }

    @Override
    public int size() {
        return num_ele;
    }

    @Override
    public T top() throws CollectionException {
        return back();
    }

    @Override
    public void push(T x) throws CollectionException {
        enqueue(x);
    }

    @Override
    public T pop() throws CollectionException {
        return dequeueBack();
    }

    @Override
    public T front() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return tab[next(front)];
    }

    @Override
    public T back() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return tab[prev(back)];
    }

    @Override
    public void enqueue(T x) throws CollectionException {
        if(isFull())
            throw new CollectionException(ERR_MSG_FULL);
        tab[back] = x;
        back = next(back);
        num_ele++;
    }

    @Override
    public void enqueueFront(T x) throws CollectionException {
        if(isFull())
            throw new CollectionException(ERR_MSG_FULL);
        tab[front] = x;
        front =prev(front);
        num_ele++;
    }

    @Override
    public T dequeue() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        front = next(front);
        T ele = tab[front];
        tab[front] = null;
        num_ele--;
        return ele;
    }

    @Override
    public T dequeueBack() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        back = prev(back);
        T ele = tab[back];
        tab[back] = null;
        num_ele--;
        return ele;
    }

    @Override
    public T get(int i) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if(i >= size() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        return tab[i];
    }

    @Override
    public void add(T x) throws CollectionException {
        enqueue(x);
    }
    public void add(int i, T x) throws CollectionException {
        if(isFull())
            throw new CollectionException(ERR_MSG_FULL);
        if(i > size() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        for (int j = size()-1; j >= i; j--) {
            tab[j+1] = tab[j];
        }
        num_ele++;
        tab[i] = x;
    }

    @Override
    public T set(int i, T x) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if(i >= size() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        T ele = tab[i];
        tab[i] = x;
        return ele;
    }

    @Override
    public int indexOf(T x) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        for (int i = 0; i < size(); i++) {
            if(x.equals(tab[i]))
                return i;
        }
        return -1;
    }

    @Override
    public T remove(T x) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        int index = indexOf(x);
        if(index == -1)
            return null;
        else {
            T ele = tab[index];
            tab[index] = null;
            for (int i = index; i < size(); i++) {
                tab[i] = tab[i+1];
            }
            num_ele--;
            return ele;
        }
    }
    @Override
    public T remove(int i) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if(i >= size() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        if(i == -1)
            return null;
        else {
            T ele = tab[i];
            tab[i] = null;
            for (int j = i; j < size(); j++) {
                tab[j] = tab[j+1];
            }
            num_ele--;
            return ele;
        }
    }

    public String toString() {
        String s;
        if(isEmpty())
            s = "[]";
        else {
            int ele = next(front);
            s = "[" + tab[ele];
            for (int i = 0; i < size()-1; i++) {
                ele = next(ele);
                s += ", " + tab[ele];
            }
            s += "]";
        }
        return s;
    }
}