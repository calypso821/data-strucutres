package array;

public interface PosplosenaTabela {
    void init(int n);
    boolean insert(int x);
    int get(int i);
    int find(int x);
    boolean delete(int x);
    int length();
    int size();
}
