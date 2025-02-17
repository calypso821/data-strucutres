package array;


import java.util.Arrays;



public class TabelaTabel implements PosplosenaTabela{
    int[][] tabX;
    int[] tabZapolnjenosti;
    int k; // stevilo tabel (tabeleX)
    int n; // dolzina tabeleX
    int z; // stevilo elemntov (skupaj)

    @Override
    public void init(int l) {
        int len = (int)(Math.log(l) / Math.log(2)) + 1;
        tabX = new int[len][];
        int[] tab;
        for (int i = 0; i < len; i++) {
            tab = new int[(int)Math.pow(2, i)];
            tabX[i] = tab;
            k = i;
        }
        k++;
        z=0;
        n= (int)Math.pow(2, k) - 1;
        tabZapolnjenosti = new int[20];
    }

    @Override
    public boolean insert(int x) {
        int[] tabA = new int[]{x};
        int i = 0; // stevec tabele i
        while (!isEmpty(tabA)) {
            if(i == k) {
                init(n + 1);
                k++;
            }
            if(isEmptyi(i)) {
                tabX[i] = tabA;
                tabZapolnjenosti[i] = tabX[i].length;
                return true;
            }
            else {
                tabA = zlij(tabA, tabX[i]);
                tabX[i] = new int[tabX[i].length];
                tabZapolnjenosti[i] = 0;
                i++;
            }

        }
        return false;
    }
    public int[] zlij(int[] tab1, int[] tab2){
        int[] newt = new int[tab1.length+tab2.length];
        int tab1_c = 0, tab2_c = 0;
        int c = 0;

        // razvrstimo elemnte v new array (dokler ne pridemo do konca 1 od arrayev)
        while(tab1_c < tab1.length && tab2_c < tab2.length) {
            if (tab1[tab1_c] < tab2[tab2_c]) {
                newt[c++] = tab1[tab1_c++];
            } else {
                newt[c++] = tab2[tab2_c++];
            }

        }
        // ko pridemo do konca 1 arraya, nato prepisemo se vse preostale elemnte array 2
        while(tab1_c < tab1.length)
            newt[c++] = tab1[tab1_c++];
        while(tab2_c < tab2.length)
            newt[c++] = tab2[tab2_c++];
        return newt;
    }

    @Override
    public int get(int i) {
        int c = 0;
        while(c < k) {
            if (!isEmptyi(c)){
                if(tabX[c].length > i )
                    return tabX[c][i];
                i -= tabX[c].length;
            }
            c++;
        }
        return -1;
    }

    @Override
    public int find(int x) {
        int index = 0;
        int c = 0;
        int res;
        while(c < k) {
            if(tabX[c][0] <= x && x <= tabX[c][tabX[c].length-1]) {
                res = BinarySerch(x, tabX[c], 0, tabX[c].length - 1);
                if (res != -1)
                    return res+index;

            }
            if(!isEmptyi(c))
                index += tabX[c].length;
            c++;
        }
        return -1;
    }
    int BinarySerch(int x, int[] tab, int zac, int konc) {
        int size = konc-zac;
        int sredina = zac + size/2;
        if(x == tab[sredina])
            return sredina;
        if(x < tab[sredina] && size > 0)
            return BinarySerch(x, tab, zac, sredina-1);
        if(x > tab[sredina] && size > 0)
            return BinarySerch(x, tab,sredina+1, konc);
        return -1;
    }

    @Override
    public boolean delete(int x) {
        int c = 0;
        int res;
        while(c < k) {
            if(tabX[c][0] <= x && x <= tabX[c][tabX[c].length-1]) {
                res = BinarySerch(x, tabX[c], 0, tabX[c].length - 1);
                if (res != -1) {
                    tabX[c][res] = 0;
                    return true;
                }
            }
            c++;
        }
        return false;
    }
    // tabela tab
    public boolean isEmpty(int[] tab){
        return tab[0] == 0;
    }
    // i - tabela, tabX
    public boolean isEmptyi(int i){
        return tabX[i][0] == 0;
    }

    @Override
    public int length() { return n; }

    @Override
    public int size() {
        return z;
    }

    @Override
    public String toString() {
        return "TabelaTabel{" +
                "tab=" + Arrays.toString(tabX) +
                '}';
    }
    public void print(){
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < tabX[i].length; j++) {
                System.out.print(tabX[i][j] + " ");
            }
            System.out.println();

        }
    }
}
