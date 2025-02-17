import java.util.Random;

public class Izziv5 {
    static int m = 0;
    static int c = 0;
    public static void main (String[] args) throws CollectionException {
        /*
        Oseba oseba1 = new Oseba("aatic", "kenz", 1999);
        Oseba oseba2 = new Oseba("mata", "jenzz", 1998);
        Oseba oseba3 = new Oseba("cat", "kedfzz", 1987);
        Oseba oseba4 = new Oseba("zav", "dfzz", 1996);
        Oseba oseba5 = new Oseba("bse", "aesazz", 1983);
        Oseba oseba6 = new Oseba("datr", "bvzz", 1961);

        Sequence<Oseba> seq1 = new LinkedSequence<>();
        seq1.insert(0, oseba1);
        seq1.insert(0, oseba2);
        seq1.insert(1, oseba3);
        seq1.insert(1, oseba4);
        seq1.set(0, oseba1);
        seq1.set(seq1.count()-1, oseba2);
        //seq1.delete(0);
        seq1.delete(seq1.count()-1);
        seq1.delete(1);
        seq1.delete(0);

         */


        Random rnd = new Random();
        Sequence<Oseba> seq = new LinkedSequence<>();
        String ime, priimek;
        for (int i = 0; i < 7; i++) {
            ime = Oseba.IMENA[rnd.nextInt(Oseba.IMENA.length)];
            priimek = Oseba.PRIIMKI[rnd.nextInt(Oseba.PRIIMKI.length)];
            seq.insert(i, new Oseba(ime, priimek, rnd.nextInt(110) + 1910));

        }
        Oseba.setAtr(0);
        Oseba.setSmer(1);
        System.out.println("Imena, narascajoce");
        Oseba.quickSort(seq, 0 , seq.count()-1);
        System.out.println(Oseba.toStringArr(seq, -1));
        Oseba.setAtr(1);
        Oseba.setSmer(-1);
        System.out.println("\nPriimeki, padajoce");
        Oseba.quickSort(seq, 0 , seq.count()-1);
        System.out.println(Oseba.toStringArr(seq, -1));


        Oseba.setAtr(2);
        Oseba.setSmer(1);
        System.out.println("\nLeto, narascajoce");
        Oseba.quickSort(seq, 0 , seq.count()-1);
        System.out.println(Oseba.toStringArr(seq, -1));
        Oseba.setSmer(-1);
        System.out.println("\nLeto, padajoce");
        Oseba.quickSort(seq, 0 , seq.count()-1);
        System.out.println(Oseba.toStringArr(seq, -1));

    }
}

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}


interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    // static final String ERR_MSG_FULL = "Collection is full.";
    boolean isEmpty();
    // boolean isFull();
    int count();
    String toString();
}


interface Sequence<T extends Comparable<T>> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    T set(int i, T x) throws CollectionException;
    void insert(int i, T x) throws CollectionException;
    T delete(int i) throws CollectionException;
}

class LinkedSequence<T extends Comparable<T>> implements Sequence<T>{
    Node first;
    int size;

    LinkedSequence() {
        this.size = 0;
        this.first = null;
    }


    class Node {
        T value;
        Node next, prev;
    }

    @Override
    public T get(int i) throws CollectionException {
        if (i >= count() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        Node tmp = this.first;
        for (int j = 0; j < i; j++) {
            tmp = tmp.next;
        }
        return tmp.value;
    }

    @Override
    public T set(int i, T x) throws CollectionException {
        if (i >= count() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        Node tmp = this.first;
        for (int j = 0; j < i; j++) {
            tmp = tmp.next;
        }
        T val = tmp.value;
        tmp.value = x;
        return val;
    }

    @Override
    public void insert(int i, T x) throws CollectionException {
        if (i > count() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);

        Node new_node = new Node();
        if(count() == 0) {
            new_node.value = x;
            this.first = new_node;
        }
        else {
            Node tmp = this.first;
            for (int j = 0; j < i-1; j++) {
                tmp = tmp.next;
            }
            new_node.value = x;

            new_node.next = tmp.next;
            if (tmp.next != null)
                tmp.next.prev = new_node;

            tmp.next = new_node;
            new_node.prev = tmp;

            if (i == 0) {
                new_node.value = tmp.value;
                tmp.value = x;
            }
        }

        this.size++;
    }

    @Override
    public T delete(int i) throws CollectionException {
        if (i >= count() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        T ele = this.first.value;
        if (i == 0)
            this.first = this.first.next;
        else {
            Node tmp = this.first;
            for (int j = 0; j < i; j++) {
                tmp = tmp.next;
            }
            ele = tmp.value;
            tmp.prev.next = tmp.next;
            if (tmp.next != null)
                tmp.next.prev = tmp.prev;
        }
        this.size--;
        return ele;
    }


    @Override
    public boolean isEmpty() {
       return this.size == 0;
    }

    @Override
    public int count() {
        return this.size;
    }

    public String toString() {
        String s;
        if (isEmpty())
            s = "[]";
        else {
            Node ele = this.first;
            s = "[" + ele.value;
            while(ele.next != null) {
                ele = ele.next;
                s += ", " + ele.value;
            }
        }
        s += "]";
        return s;
    }
}

class Oseba implements Comparable<Oseba>{
    final static String[] IMENA = {
            "Ana", "Bor", "Cvetka", "Dusan", "Meta", "Nina", "Mia", "Karmen", "Rozi", "Renata", "David"};
    final static String[] PRIIMKI = {
            "Arh", "Buh", "Cah", "Duh", "Knez", "Velikonja", "Novak", "Nah", "Priimek", "Kofol", "Horjak", "Zeleni"};

    static int atr = 0;
    static int smer = 1;
    String ime, priimek;
    int letoR;

    Oseba(String ime, String priimek, int leto) {
        this.ime = ime;
        this.priimek = priimek;
        this.letoR = leto;
    }

    public String toString() {
        switch(atr) {
            case 0:
                return this.ime;
            case 1:
                return this.priimek;
            case 2:
                return String.valueOf(this.letoR);
        }
        return "";
    }
    public static void setAtr(int a) {
        atr = a;
    }
    public static int getAtr() {
        return atr;
    }
    public static void setSmer(int d) {
        smer = d;
    }
    public static int getSmer() {
        return smer;
    }

    static int partition(Sequence<Oseba> seq, int left, int right) throws CollectionException {
        Oseba p = seq.get(left);
        int l = left;
        int r = right+1;
        while(true) {
            do {
                l++;
            } while(p.compareTo(seq.get(l)) > 0 && l < right);
            do {
                r--;
            } while(seq.get(r).compareTo(p) > 0);
            if(l >= r)
                break;
            Oseba ele = seq.get(l);
            seq.set(l, seq.get(r));
            seq.set(r, ele);
            //incrementPremiki(3);
        }
        Oseba ele = seq.get(r);
        seq.set(r, seq.get(left));
        seq.set(left, ele);
        //incrementPremiki(3);
        return r;
    }

    public static void quickSort(Sequence<Oseba> seq, int left, int right) throws CollectionException {
        if(left >= right)
            return;
        int r = partition(seq, left, right);
        //incrementPremiki(1);
        quickSort(seq, left, r - 1);
        quickSort(seq, r + 1, right);
    }


    static String toStringArr(Sequence<Oseba> seq, int mesto) throws CollectionException {
        String out = "";
        String ele = "";
        for (int i = 0; i < seq.count(); i++) {
            out += seq.get(i).toString() + " ";
            if(mesto > -1 && mesto == i)
                out += "| ";

        }
        return out;
    }


    @Override
    public int compareTo(Oseba o) {
        switch(atr) {
            case 0:
                return (this.ime.compareTo(o.ime)) * smer;
            case 1:
                return (this.priimek.compareTo((o).priimek)) * smer;
            case 2:
                return (this.letoR - (o).letoR) * smer;
            default:
                return 0;
        }
    }
}
