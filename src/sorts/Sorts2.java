

import java.util.Arrays;
import java.util.Scanner;

public class Naloga2 {

    static boolean trace = true;
    static int smer =  1;
    static boolean maxMinBool = true;
    static int m = 0; // premik
    static int c = 0; // primerjave
    public static void main (String[] args) {
        /*
        smer = 1;
        int[] tab5 = new int[]{42,17,27,51,39};
        //int[] tab6 = new int[]{4,6,1,3,2,5,3};
        trace = false;

        mergeSort1(tab5);
        //bucketSort(tab5);
        mergeSort1(tab5);
        smer *= -1;
        //bucketSort(tab5);
        mergeSort1(tab5);

         */


        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            clearCnt();
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            String[] tab1 = line1.split(" ");
            line2 = line2.replace("  ", " ");
            String[] string_tab = line2.split(" ");
            int[] tab = new int[string_tab.length];

            for (int i = 0; i < string_tab.length; i++) {
                tab[i] = Integer.parseInt(string_tab[i]);
            }

            switch (tab1[2]) {
                case "up": smer = 1; break;
                case "down": smer = -1;
            }
            switch (tab1[0]) {
                case "trace": trace = true; break;
                case "count": trace = false;
            }
            if(trace)
                System.out.println(toString(tab, -1));

            switch (tab1[1]) {
                case "insert": {
                    insertSort(tab, true);
                    if(!trace) {
                        System.out.print(" | ");
                        insertSort(tab, true);
                        smer *= -1;
                        System.out.print(" | ");
                        insertSort(tab, true);
                        System.out.println();
                        System.out.println();
                    }
                } break;
                case "select": {
                    selectSort(tab);
                    if(!trace) {
                        System.out.print(" | ");
                        selectSort(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        selectSort(tab);
                        System.out.println();
                    }
                }break;
                case "bubble": {
                    bubbleSort(tab);
                    if(!trace) {
                        System.out.print(" | ");
                        bubbleSort(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        bubbleSort(tab);
                        System.out.println();
                    }
                }break;
                case "heap": {
                    heapSort(tab);
                    if(!trace) {
                        System.out.print(" | ");
                        heapSort(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        heapSort(tab);
                        System.out.println();
                    }
                }break;
                case "merge": {
                    mergeSort1(tab);
                    if(!trace) {
                        System.out.print(" | ");
                        mergeSort1(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        mergeSort1(tab);
                        System.out.println();
                    }
                }break;
                case "quick": {
                    quickSort1(tab);
                    if(!trace) {
                        System.out.print(" | ");
                        quickSort1(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        quickSort1(tab);
                        System.out.println();
                    }
                }break;
                case "radix": {
                    radixSort(tab);
                    if(!trace) {
                        System.out.print(" | ");
                        radixSort(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        radixSort(tab);
                        System.out.println();
                    }
                }break;
                case "bucket": {
                    maxMinBool = true;
                    bucketSort(tab);
                    maxMinBool = false;
                    if(!trace) {
                        System.out.print(" | ");
                        bucketSort(tab);
                        smer *= -1;
                        System.out.print(" | ");
                        bucketSort(tab);
                        System.out.println();
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

    static void insertSort(int[] tab, boolean clear) {
        if(clear)
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
            System.out.print(cntToString());
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
            System.out.print(cntToString());
    }
    static void bubbleSort(int[] tab) {
        clearCnt();
        int last;
        for (int i = 1; i < tab.length; i++) {
            last = tab.length-1;
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
            System.out.print(cntToString());
    }
    static void heapSort(int[] tab) {
        clearCnt();
        for (int i = tab.length/2-1; i >= 0; i--) {
            pogrezni(i, tab, tab.length);
        }
        int last = tab.length-1;
        while(last >= 1) {
            if(trace)
                System.out.println(toString(tab, last));
            int ele = tab[last];
            tab[last] = tab[0];
            tab[0] = ele;
            incrementPremiki(3);
            last--;
            pogrezni(0, tab, last+1);

        }
        if(trace)
            System.out.println(toString(tab, last));
        else
            System.out.print(cntToString());
    }

    static void pogrezni(int i, int[] tab, int dolzina) {
        int max_o;
        int lotrok = 2*i+1;
        int rotrok = 2*i+2;
        if(lotrok < dolzina) {
            max_o = lotrok;

            // L in D otrok
            if(rotrok < dolzina) {
                if (compareto(tab[rotrok], tab[lotrok]) > 0) {
                    max_o = rotrok;
                }

            }
            // L otrok
            if(compareto(tab[max_o], tab[i]) > 0) {
                // zamenjava
                int parent = tab[i];
                tab[i] = tab[max_o];
                tab[max_o] = parent;
                incrementPremiki(3);
                pogrezni(max_o, tab, dolzina);
            }
        }
    }
    static void mergeSort1(int[] tab) {
        clearCnt();
        int[] tmp = mergeSort(tab);
        for (int i = 0; i < tab.length; i++) {
            tab[i] = tmp[i];
        }
        if(!trace)
            System.out.print(cntToString());
    }

    static int[] mergeSort(int[] tab) {
        if(tab.length <= 1) {
            return tab;
        }
        int mid = (tab.length -1) /2;
        if(trace)
            System.out.println(toString(tab, mid));
        int[] ltab = mergeSort(slice(tab, 0, mid));
        int[] rtab = mergeSort(slice(tab, mid+1, tab.length-1));
        return zlij(ltab, rtab);
    }
    static int[] slice(int[] tab, int left, int right) {
        int[] tab1 = new int[right-left+1];
        for (int i = 0; i < tab1.length; i++) {
            incrementPremiki(1);
            tab1[i] = tab[i+left];
        }
        return tab1;
    }


    static int[] zlij(int[] ltab, int[] rtab) {
        int[] newt = new int[ltab.length + rtab.length];
        int t = 0;
        int t1 = 0;
        int t2 = 0;
        while(t1 < ltab.length && t2 < rtab.length) {
            incrementPremiki(1);
            if(compareto(rtab[t2], ltab[t1]) > 0)
                newt[t++] = ltab[t1++];
            else
                newt[t++] = rtab[t2++];
        }
        while(t1 < ltab.length) {
            incrementPremiki(1);
            newt[t++] = ltab[t1++];
        }
        while(t2 < rtab.length) {
            incrementPremiki(1);
            newt[t++] = rtab[t2++];
        }

        if(trace)
            System.out.println(toString(newt, -1));
        return newt;
    }
    static void quickSort1(int[] tab) {
        clearCnt();
        quickSort(tab, 0, tab.length-1);
        if(!trace)
            System.out.print(cntToString());
        else
            System.out.println(toString(tab, -1));
    }

    static int partition(int[] tab, int left, int right) {
        int p = tab[left];
        int l = left;
        int r = right+1;
        while(true) {
            do {
                l++;
            } while(compareto(p, tab[l]) > 0 && l < right);
            do {
                r--;
            } while(compareto(tab[r], p) > 0);
            if(l >= r)
                break;
            int ele = tab[l];
            tab[l] = tab[r];
            tab[r] = ele;
            incrementPremiki(3);
        }
        int ele = tab[r];
        tab[r] = tab[left];
        tab[left] = ele;
        incrementPremiki(3);
        if(trace)
            System.out.println(toStringPivot(tab, r, left , right+1));
        return r;
    }

    static void quickSort(int[] tab, int left, int right) {
        if(left >= right)
            return;
        int r = partition(tab, left, right);
        incrementPremiki(1);
        quickSort(tab, left, r - 1);
        quickSort(tab, r + 1, right);
    }

    static int countingSort(int[] tab, int index, boolean count) {
        int k=0;
        if(smer==-1)
            k = 1;
        int cnt = -1;
        int num;
        int[] tmp = new int[tab.length];
        int[] indextab = new int[10];
        for (int i = 0; i < tab.length; i++) {
            incrementPrimerjave(1);
            if(count && Math.floor(Math.log10(tab[i])) > cnt)
                cnt = (int)Math.floor(Math.log10(tab[i]));
            num = (int) (tab[i] / Math.pow(10, index));
            int num2 = ((num+k) % 10) * smer;
            int num1 = Math.abs(10 + num2) % 10;
            indextab[num1]++;
        }

        for (int i = 1; i < indextab.length; i++) {
            indextab[i] = indextab[i] + indextab[i-1];
        }
        // prepis
        for (int i = tab.length-1; i >= 0; i--) {
            incrementPremiki(1);
            incrementPrimerjave(1);
            num = (int) (tab[i] / Math.pow(10, index));
            int num2 = ((num+k) % 10) * smer;
            int num1 = Math.abs(10 + num2) % 10;
            tmp[--indextab[num1]] = tab[i];
        }
        // prepis
        for (int i = 0; i < tab.length; i++) {
            tab[i] = tmp[i];
            incrementPremiki(1);
        }
        if(trace)
            System.out.println(toString(tab, -1));

        return cnt;
    }

    static void radixSort(int[] tab) {
        clearCnt();
        int index = countingSort(tab, 0, true);

        for (int i = 0; i < index; i++) {
            countingSort(tab, i+1, false);
        }
        if(!trace)
            System.out.print(cntToString());

    }

    static void bucketSort(int[] tab) {
        clearCnt();
        int[] tmp = new int[tab.length];
        int k = tab.length / 2;
        int[] indextab = new int[k];
        int[] minMaxTab = maxMin(tab);
        int max = minMaxTab[0];
        int min = minMaxTab[1];
        double v = (max - min +1) / (double) k;

        int index;
        for (int i = 0; i < tab.length; i++) {
            incrementPrimerjave(1);
            index = (int) ((tab[i] - min) / v);
            if(smer==-1)
                index = indextab.length-1 - index;
            indextab[index]++;
        }
        for (int i = 1; i < indextab.length; i++) {
            indextab[i] = indextab[i] + indextab[i-1];
        }
        for (int i = tab.length-1; i >= 0; i--) {
            incrementPrimerjave(1);
            incrementPremiki(1);
            index = (int) ((tab[i] - min) / v);
            if(smer==-1)
                index = indextab.length-1 - index;
            tmp[--indextab[index]] = tab[i];
        }

        for (int i = 0; i < tab.length; i++) {
            incrementPremiki(1);
            tab[i] = tmp[i];
        }

        if(trace)
            System.out.println(toStringBucket(tab, indextab));

        insertSort(tab, false);
    }
    static int[] maxMin(int[] tab) {
        int min = tab[0];
        int max = tab[0];
        for (int i = 1; i < tab.length; i++) {
            incrementPrimerjave(1);
            if(tab[i] - max > 0)
                max = tab[i];
            if(min - tab[i] > 0)
                min = tab[i];
        }
        return new int[]{max, min};
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
    static String toStringPivot(int[] tab, int mesto, int l, int r) {
        String out = "";
        if(mesto <= l)
            out+= "| ";
        for (int i = l; i < r; i++) {
            out += String.valueOf(tab[i]) + " ";
            if(mesto > -1 && mesto == i)
                out += "| ";
            if(mesto > -1 && mesto-1 == i)
                out += "| ";

        }
        return out;
    }
    static String toStringBucket(int[] tab, int[] mesta) {
        String out = "";
        int cnt = 1;
        for (int i = 0; i < tab.length; i++) {
            out+= tab[i] + " ";
            if(cnt < mesta.length && mesta[cnt]-1 == i) {
                while(true) {
                    out += "| ";
                    if(cnt+1 == mesta.length || mesta[cnt] != mesta[cnt+1])
                        break;
                    cnt++;
                }
                cnt++;
            }
        }
        return out;
    }

}
