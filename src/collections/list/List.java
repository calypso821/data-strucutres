package list;

public interface List {
    void init(int n);
    void put(int i, Object x);
    Object get(int i);
    int length();
}
