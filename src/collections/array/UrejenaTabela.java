package array;

import java.util.Arrays;

public class UrejenaTabela implements PosplosenaTabela {
    int tab[];
    int z;

    @Override
    public void init(int n) {
        tab = new int[n];
        z=0;
    }

    @Override
    public boolean insert(int x) {
        z++;
        if(z <= tab.length) {
            if (z == 1)
                tab[0] = x;
            else {
                for (int i = z-1; i >= 0; i--) {
                    if (tab[i-1] > x)
                        tab[i] = tab[i-1];
                    else {
                        tab[i] = x;
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int get(int i) {
        if(i < z)
            return tab[i];
        return -1;
    }

    @Override
    public int find(int x) {
        return BinarySerch(x, 0, z-1);
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
        return "UrejenaTabela{" +
                "tab=" + Arrays.toString(tab) + '}';
    }

    int BinarySerch(int x, int zac, int konc) {
        int size = konc-zac;
        int sredina = zac + size/2;
        if(x == tab[sredina])
            return sredina;
        if(x < tab[sredina] && size > 0)
            return BinarySerch(x, zac, sredina-1);
        if(x > tab[sredina] && size > 0)
            return BinarySerch(x, sredina+1, konc);
        return -1;
    }
}
