package array;

public class DinamicnaTabela implements PosplosenaTabela{
    int tab[];
    int z;
    int n;

    @Override
    public void init(int l) {
        double len = Math.ceil((Math.log(l) / Math.log(2)));
        tab = new int[(int)Math.pow(2, len)];
        z=0;
        n = tab.length;
    }

    @Override
    public boolean insert(int x) {

        if(z == n) {
            // if z == n
            // povecaj tabelo za
            int[] newt = new int[2*n];
            // prepisi elemnte
            for (int i = 0; i < z; i++) {
                newt[i] = tab[i];
            }
            tab = newt;
            n = tab.length;
        }
        tab[z] = x;
        z++;
        return true;
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
        return n;
    }

    @Override
    public int size() {
        return z;
    }
}
