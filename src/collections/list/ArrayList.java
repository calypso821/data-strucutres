package list;

public class ArrayList implements List {
    Object[] array;
    @Override
    public void init(int n) {
        array = new Object[10];
    }

    @Override
    public void put(int i, Object x) {
        array[i] = x;
    }

    @Override
    public Object get(int i) {
        return array[i];
    }

    @Override
    public int length() {
        return array.length;
    }
}
