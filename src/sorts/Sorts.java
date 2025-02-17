package Naloge;

import java.util.Scanner;

public class Sort {

    static boolean trace = true;
    static int smer =  1;
    static int m = 0; // premik
    static int c = 0; // primerjave
    public static void main (String[] args) {
        smer = -1;
        int[] tab5 = new int[]{42,17,27,51,39};
        trace = true;
        selectSort(tab5);
        //mergeSort(0, tab5.length-1, tab5);
        //mergeSort(0, tab5.length-1, tab5);
        smer *= -1;
        //mergeSort(0, tab5.length-1, tab5);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            clearCnt();
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            String[] tab1 = line1.split(" ");
            String[] string_tab = line2.split(" ");
            int[] tab = new int[string_tab.length];

            for (int i = 0; i < string_tab.length; i++) {
                tab[i] = Integer.parseInt(string_tab[i]);
            }

            switch (tab1[2]) {
                case "up" -> smer = 1;
                case "down" -> smer = -1;
            }
            switch (tab1[0]) {
                case "trace" -> trace = true;
                case "count" -> trace = false;
            }
            if(trace)
                System.out.println(toString(tab, -1));

            switch (tab1[1]) {
                case "insert" -> {
                    insertSort(tab);
                    if(!trace) {
                        insertSort(tab);
                        smer *= -1;
                        insertSort(tab);
                    }
                }
                case "select" -> {
                    selectSort(tab);
                    if(!trace) {
                        selectSort(tab);
                        smer *= -1;
                        selectSort(tab);
                    }
                }
                case "bubble" -> {
                    bubbleSort(tab);
                    if(!trace) {
                        bubbleSort(tab);
                        smer *= -1;
                        bubbleSort(tab);
                    }
                }
                case "heap" -> {
                    heapSort(tab);
                    if(!trace) {
                        heapSort(tab);
                        smer *= -1;
                        heapSort(tab);
                    }
                }
                case "merge" -> {
                    mergeSort(0, tab.length - 1, tab);
                    if(!trace) {
                        mergeSort(0, tab.length - 1, tab);
                        smer *= -1;
                        mergeSort(0, tab.length - 1, tab);
                    }
                }
                case "quick" -> {
                    quickSort(0, tab.length - 1, tab);
                    if(!trace) {
                        quickSort(0, tab.length - 1, tab);
                        smer *= -1;
                        quickSort(0, tab.length - 1, tab);
                    }
                }
                case "radix" -> {
                    radixSort(tab);
                    if(!trace) {
                        bucketSort(tab);
                        smer *= -1;
                        bucketSort(tab);
                    }
                }
                case "bucket" -> {
                    bucketSort(tab);
                    if(!trace) {
                        bucketSort(tab);
                        smer *= -1;
                        bucketSort(tab);
                    }
                }
            }

        }
    }
    public static String cntToString() {
        return String.valueOf(m) + " " + String.valueOf(c);
    }

    public static void clearCnt() {
        c = 0;
        m = 0;
    }
    public static void incrementPremiki(int n) {
        m+=n;
    }
    public static void incrementPrimerjave(int n) {
        c+=n;
    }
    static int compareto(int st1, int st2) {
        incrementPrimerjave(1);
        int stev = st1 - st2;
        return stev * smer;

    }

    static void insertSort(int[] tab) {
        clearCnt();
        int ele;
        for (int i = 1; i < tab.length; i++) {
            ele = tab[i];
            int j = i;
            incrementPremiki(1);
            while(j > 0 && compareto(tab[j-1], ele) > 0) {
                tab[j] = tab[j - 1];
                incrementPremiki(1);
                j--;
            }
            tab[j] = ele;
            incrementPremiki(1);
            if(trace)
                System.out.println(toString(tab, i));
        }
        if(!trace)
            System.out.println(cntToString());
    }
    static void selectSort(int[] tab) {
        clearCnt();
        int ele;
        int max;
        for (int i = 0; i < tab.length-1; i++) {
            max = i;
            for (int j = i+1; j < tab.length; j++) {
                if(compareto(tab[max], tab[j]) > 0) {
                    max = j;
                }
            }
            ele = tab[i];
            tab[i] = tab[max];
            tab[max] = ele;
            incrementPremiki(3);
            if(trace)
                System.out.println(toString(tab, i));
        }
        if(!trace)
            System.out.println(cntToString());
    }
    static void bubbleSort(int[] tab) {
        clearCnt();
        int last;
        for (int i = 1; i < tab.length; i++) {
            last = tab.length;
            for (int j = tab.length-1; j >= i; j--) {
                if (compareto(tab[j - 1], tab[j]) > 0) {
                    last = j;
                    int ele = tab[j];
                    tab[j] = tab[j - 1];
                    tab[j - 1] = ele;
                    incrementPremiki(3);
                }
            }
            i = last;
            if(trace)
                System.out.println(toString(tab, i-1));
        }
        if(!trace)
            System.out.println(cntToString());
    }
    static void heapSort(int[] tab) {
        int[] tab1 = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            if(trace)
                System.out.println(toString(tab1, i));
            tab = buildHeap(tab);
            tab1[i] = tab[0];
            tab[0] = tab[tab.length-1];
            if(smer == 1)
                tab[tab.length-1] = 0;
            else
                tab[tab.length-1] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < tab1.length; i++) {
            tab[i] = tab1[i];
        }

    }
    static int[] buildHeap(int[] tab) {
        int dolzina = tab.length;
        int zadnji = (int) (Math.pow(2, Math.log(dolzina) / Math.log(2)) -1);
        // pogrezni
        for (int i = zadnji-1; i >= 0; i--) {
            pogrezni(i, tab);
        }
        return tab;
    }
    static void pogrezni(int i, int[] tab) {
        int max_o;
        int dolzina = tab.length;
        int lotrok = 2*i+1;
        int rotrok = 2*i+2;
        if(lotrok < dolzina) {
            max_o = lotrok;
            // L in D otrok
            if(rotrok < dolzina)
                if(compareto(tab[rotrok], tab[lotrok]) > 0)
                    max_o = rotrok;
            // L otrok
            if(compareto(tab[max_o], tab[i]) > 0) {
                // zamenjava
                int parent = tab[i];
                tab[i] = tab[max_o];
                tab[max_o] = parent;
                pogrezni(max_o, tab);
            }
        }
    }

    static int[] mergeSort(int left, int right, int[] tab) {
        if(left == right)
            return new int[]{tab[right]};
        if(trace)
            System.out.println(toStringRange(tab, left, right, true));
        if(right - left < 2) {
            int[] l = new int[]{tab[left]};
            int[] r = new int[]{tab[right]};
            return zlij(l, r);
        }
        int[] ltab = mergeSort(left, left + (right-left)/2, tab);

        int[] rtab = mergeSort(left + (right-left)/2 + 1 , right, tab);
        return zlij(ltab, rtab);
        }

        static int[] zlij(int[] ltab, int[] rtab) {
        int new_t_d = ltab.length + rtab.length;
        int t1 = 0;
        int t2 = 0;
        int[] newt = new int[new_t_d];
        for (int i = 0; i < new_t_d; i++) {
            if(t1 < ltab.length && t2 < rtab.length && compareto(ltab[t1], rtab[t2]) > 0) {
                newt[i] = ltab[t1];
                t1++;
            }
            else if (t2 < rtab.length){
                newt[i] = rtab[t2];
                t2++;
            }
            else{
                newt[i] = ltab[t1];
                t1++;
            }
        }

        if(trace)
            System.out.println(toString(newt, -1));
        return newt;
        }
        static void quickSort(int left, int right, int[] tab) {
            if(right-left < 2)
                return;
            boolean lside;
            boolean rside;
            int p = left;
            int l = left + 1;
            int r = right;
            while (l<=r) {
                // true if (pivot > l)
                //lside = compare(tab[p], tab[l]) > 0;
                // true if (pivot < r)
                //rside = compare(tab[r], tab[p]) > 0;
                lside = true;
                rside = true;
                while (lside || rside) {
                    if (compareto(tab[p], tab[l]) > 0)
                        l++;
                    else
                        lside = false;
                    if (compareto(tab[r], tab[p]) > 0)
                        r--;
                    else
                        rside = false;
                }
                if (l <= r) {
                    // swtich (r, l)
                    int ele = tab[l];
                    tab[l] = tab[r];
                    tab[r] = ele;
                    l++;
                    r--;
                }
            }
            // switch with pivot
            int ele = tab[r];
            tab[r] = tab[p];
            tab[p] = ele;

            quickSort(left, r - 1, tab);
            quickSort(r + 1, right, tab);
        }

    static int countingSort(int[] tab, int index, boolean count) {
        int cnt = -1;
        int num;
        int[] tmp = new int[tab.length];
        int[] indextab = new int[10];
        for (int i = 0; i < tab.length; i++) {
            if(count && Math.floor(Math.log10(tab[i])) > cnt)
                cnt = (int)Math.floor(Math.log10(tab[i]));
            num = (int) (tab[i] / Math.pow(10, index));
            indextab[num % 10]++;
        }
        for (int i = 1; i < indextab.length; i++) {
            indextab[i] = indextab[i] + indextab[i-1];
        }
        for (int i = tab.length-1; i >= 0; i--) {
            num = (int) (tab[i] / Math.pow(10, index));
            tmp[--indextab[num % 10]] = tab[i];
        }
        for (int i = 0; i < tab.length; i++) {
            tab[i] = tmp[i];
        }

        return cnt;
    }

    static void radixSort(int[] tab) {
        int index = countingSort(tab, 0, true);
        for (int i = 0; i < index; i++) {
            countingSort(tab, i+1, false);
        }
    }

    static void bucketSort(int[] tab) {
        int[] tmp = new int[tab.length];
        int k = tab.length / 2;
        int[] indextab = new int[k+1];
        int[] maxmin = maxMin(tab);
        int max = maxmin[0];
        int min = maxmin[1];
        incrementPrimerjave(1);
        float v = (max - min +1) / (float) k;
        int index;

        for (int i = 0; i < tab.length; i++) {
            index = (int) Math.floor((tab[i] - min) / v);
            indextab[index]++;
        }
        for (int i = 1; i < indextab.length; i++) {
            indextab[i] = indextab[i] + indextab[i-1];
        }
        for (int i = tab.length-1; i >= 0; i--) {
            index = (int) Math.floor((tab[i] - min) / v);
            tmp[--indextab[index]] = tab[i];
        }
        for (int i = 0; i < tab.length; i++) {
            tab[i] = tmp[i];
        }
        insertSort(tab);
    }
    static int[] maxMin(int[] tab) {
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < tab.length; i++) {
            if(compareto(tab[i], max) > 0)
                max = tab[i];
            if(compareto(min, tab[i]) > 0)
                min = tab[i];
        }
        return new int[]{max, min};
    }
    static void swap() {

    }


    static String toString(int[] tab, int mesto) {
        String out = "";
        for (int i = 0; i < tab.length; i++) {
            out += String.valueOf(tab[i]) + " ";
            if(mesto > -1 && mesto == i)
                out += "| ";

        }
        return out;
    }
    static String toStringRange(int[] tab, int left, int right, boolean m) {
        int mesto = left + (right - left) /2;
        String out = "";
        for (int i = left; i < right+1; i++) {
            out += String.valueOf(tab[i]) + " ";
            if(m && mesto > -1 && mesto == i)
                out += "| ";

        }
        return out;
    }

}
