package collections;

public class UrejenSeznam {
    Elt head;
    UrejenSeznam tail;

    UrejenSeznam(Elt elt, UrejenSeznam s) {
        this.head = elt;
        this.tail = s;
    }

    static UrejenSeznam insert(UrejenSeznam s, Elt ele) {
        Elt ele1 = find(s, ele.key);
        if(ele1 != null) {// kljuc ze obstaja
            ele1.value = ele.value;
            return s;
        }
        if(s == null || s.head.key > ele.key)
            return new UrejenSeznam(ele, s);
        else
            return new UrejenSeznam(s.head, insert(s.tail, ele));
    }

    static Elt find(UrejenSeznam s, int key) {
        if(s == null)
            return null;
        if(s.head.key == key)
            return s.head;
        else
            return find(s.tail, key);
    }
    static UrejenSeznam delete(UrejenSeznam s, int key) {
        if(s == null) return null;
        if(s.head.key == key)
            return s.tail;
        else {
            s.tail = delete(s.tail, key);
            return s;
        }

    }

    public String toString() {
        String out = "[" + String.valueOf(head.value) + ", ";
        String tail2 = "]";
        UrejenSeznam tail1 = this.tail;
        while (tail1 != null) {
            out+= "[" + tail1.head.value + ", ";
            tail1 = tail1.tail;
            tail2 += "]";

        }
        out += "null" + tail2;

        return out;
    }
}
