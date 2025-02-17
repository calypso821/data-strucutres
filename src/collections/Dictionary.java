package collections;

public interface Slovar {
    boolean insert(Elt elt);
    int find(int key);
    boolean delete(int key);
}

class Elt {
    int key;
    int value;

    Elt(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
