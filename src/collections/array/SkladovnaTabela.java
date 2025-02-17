package array;

import java.util.Arrays;

public class SkladovnaTabela implements PosplosenaTabela {
    int tab[];
    int z;
    
    @Override
    public void init(int n) {
        tab = new int[n];
        z=0;
    }

    @Override
    public boolean insert(int x) {

        if(z < tab.length) {
            tab[z] = x;
            z++;
            return true;
        }
        return false;
    }

    @Override
    public int get(int i) {
        return tab[i];
    }

    @Override
    public int find(int x) {
        for (int i = 0; i <= z; i++) {
            if(x ==tab[i])
                return i;
        }
        return -1;
    }

    @Override
    public boolean delete(int x) {
        int index = find(x);
        if(index == -1)
            return false;
        z--;
        if(z >= 0) {
            for (int i = index; i < z; i++) {
                tab[i] = tab[i+1];
            }
            tab[z] = 0;
            return true;
        }
        return false;
    }

    @Override
    public int length() {
        return tab.length;
    }

    @Override
    public int size() {
        return z;
    }

    @Override
    public String toString() {
        return "SkladovnaTabela{" +
                "tab=" + Arrays.toString(tab) + '}';
    }
}
