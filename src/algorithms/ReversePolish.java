package Naloge;

import java.util.Scanner;

public class Naloga1 {
    static Sequence<Stack<String>> seq;
    static boolean flag;
    static Stack<String> s0;
    static Scanner sc1;

    public static void main (String[] args) throws CollectionException {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            // initialize
            seq = initialize();
            s0 = seq.get(0);
            flag = false;
            sc1 = new Scanner(line);
            while (sc1.hasNext()) {
                proccess(sc1.next());
            }
        }
    }

    static void proccess(String niz) throws CollectionException {
        if (niz.charAt(0) == '?')
            if(flag) niz = niz.substring(1); else return;
        switch (niz) {
            case "echo":
                if (s0.isEmpty()) System.out.println();
                else System.out.println(s0.top()); break;
            case "pop": s0.pop(); break;
            case "dup": s0.push(s0.top()); break;
            case "dup2": duplicate2(s0); break;
            case "swap": swap(s0); break;
            case "char": s0.push(String.valueOf((char)Integer.parseInt(s0.pop()))); break;
            case "even": case "odd": evenOdd(s0, niz); break;
            case "!": s0.push(String.valueOf(factorial(Integer.parseInt(s0.pop())))); break;
            case "len": s0.push(String.valueOf(s0.pop().length())); break;
            case ".": concat(niz, s0);break;
            case "<>": case "==": case "<": case "<=": case ">":
            case ">=": case "+": case "-": case "*": case "/":
            case "%": case "rnd": proccessNum(niz, s0); break;
            case "then": flag = !s0.pop().equals("0");break;
            case "else": flag = !flag; break;
            case "print": System.out.println(getStack(seq, s0.pop())); break;
            case "clear": clearStack(getStack(seq, s0.pop())); break;
            case "run": run(getStack(seq, s0.pop())); break;
            case "loop": loop(getStack(seq, s0.pop()), Integer.parseInt(s0.pop())); break;
            case "fun": func(getStack(seq, s0.pop()), Integer.parseInt(s0.pop()), sc1); break;
            case "move": move(getStack(seq, s0.pop()), s0, Integer.parseInt(s0.pop())); break;
            case "reverse": reverse(getStack(seq, s0.pop())); break;
            default: s0.push(niz);
        }
     }
    static Sequence<Stack<String>> initialize() throws CollectionException {
        Sequence<Stack<String>> seq = new ArrayDeque<Stack<String>>();
        for (int i = 0; i < 42; i++) {
            Stack<String> k = new ArrayDeque<>();
            seq.add(k);
        }
        return seq;
    }
    static void loop(Stack<String> s, int n) throws CollectionException {
        for (int i = 0; i < n; i++) {
            run(s);
        }
    }
    static void run(Stack<String> s) throws CollectionException {
        String line = s.toStringReverse();
        Scanner sc2 = new Scanner(line);
        while (sc2.hasNext()) {
            proccess(sc2.next());
        }
    }
    static void reverse(Stack<String> s) throws CollectionException {
        Stack<String> s1 = new ArrayDeque<>();
        Stack<String> s2 = new ArrayDeque<>();

        while(!s.isEmpty()) {
            s1.push(s.pop());
        }
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        while(!s2.isEmpty()) {
            s.push(s2.pop());
        }
    }
    static void func(Stack<String> s, int st, Scanner sc1) throws CollectionException {
        for (int i = 0; i < st; i++) {
            s.push(sc1.next());
        }
    }
    static void move(Stack<String> s, Stack<String> s0, int st) throws CollectionException {
        for (int i = 0; i < st; i++) {
            s.push(s0.pop());
        }
    }
    static Stack<String> getStack(Sequence<Stack<String>> seq, String niz) throws CollectionException {
        return seq.get(Integer.parseInt(niz));
    }
    static void clearStack(Stack<String> s) throws CollectionException {
        while(!s.isEmpty()) {
            s.pop();
        }
    }
    static void evenOdd(Stack<String> s, String niz) throws CollectionException {
        int x = Integer.parseInt(s.pop());
        int val = 0;
        switch(niz) {
            case "even": if(x%2 == 0) val++; break;
            case "odd": if(x%2 != 0) val++; break;
        }
        s.push(String.valueOf(val));
    }

    static void swap(Stack<String> s) throws CollectionException {
        String ele_top = s.pop();
        String ele_bot = s.pop();
        s.push(ele_top);
        s.push(ele_bot);
    }
    static void duplicate2(Stack<String> s) throws CollectionException {
        String ele2 = s.pop();
        String ele1 = s.top();
        s.push(ele2);
        s.push(ele1);
        s.push(ele2);
    }
    static int factorial(int n) {
        int sum = 1;
        for (int i = n; i > 0; i--) {
            sum *= i;
        }
        return sum;
    }
    static void proccessNum(String niz, Stack<String> s) throws CollectionException {
        int y = Integer.parseInt(s.pop());
        int x = Integer.parseInt(s.pop());
        int val = 0;
        switch (niz) {
            case "<>": if(x != y) val++; break;
            case "==": if(x == y) val++; break;
            case "<": if(x < y) val++; break;
            case "<=": if(x <= y) val++; break;
            case ">": if(x > y) val++; break;
            case ">=": if(x >= y) val++; break;
            case "+": val = x + y; break;
            case "-": val = x - y; break;
            case "*": val = x * y; break;
            case "/": val = x / y; break;
            case "%": val = x % y; break;
            case "rnd":
                int range = (y - x) + 1;
                val = (int)(Math.random() * range) + x;
        }
        s.push(String.valueOf(val));
    }
    static void concat(String niz, Stack<String> s) throws CollectionException {
        String ele1 = s.pop();
        String ele2 = s.pop();
        s.push(String.valueOf(ele2+ele1));
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
    String toStringReverse();
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

    @SuppressWarnings("unchecked")
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
            s = "";
        else {
            int ele = prev(back);
            s = String.valueOf(tab[ele]);
            for (int i = 0; i < size()-1; i++) {
                ele = prev(ele);
                s += " " + tab[ele];
            }
        }
        return s;
    }
    public String toStringReverse() {
        String s;
        if(isEmpty())
            s = "";
        else {
            int ele = next(front);
            s = String.valueOf(tab[ele]);
            for (int i = 0; i < size()-1; i++) {
                ele = next(ele);
                s += " " + tab[ele];
            }
        }
        return s;
    }
}