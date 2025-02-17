package Izziv4;

import java.util.Random;

public class Izziv4 {
    public static void main (String[] args) {
        Oseba oseba1 = new Oseba("aatic", "kenz", 1999);
        Oseba oseba2 = new Oseba("mata", "jenzz", 1998);
        Oseba oseba3 = new Oseba("cat", "kedfzz", 1987);
        Oseba oseba4 = new Oseba("zav", "dfzz", 1996);
        Oseba oseba5 = new Oseba("bse", "aesazz", 1983);
        Oseba oseba6 = new Oseba("datr", "bvzz", 1961);


        Random rnd = new Random();
        Comparable[] tab2 = new Comparable[10];
        String ime, priimek;
        for (int i = 0; i < tab2.length; i++) {
            ime = Oseba.IMENA[rnd.nextInt(Oseba.IMENA.length)];
            priimek = Oseba.PRIIMKI[rnd.nextInt(Oseba.PRIIMKI.length)];
            tab2[i] = new Oseba(ime, priimek, rnd.nextInt(110) + 1910);

        }
        Oseba.setAtr(0);
        Oseba.setSmer(1);
        System.out.println("Imena, narascajoce");
        Oseba.bubblesort1(tab2);
        Oseba.setAtr(1);
        Oseba.setSmer(-1);
        System.out.println("\nPriimeki, padajoce");
        Oseba.bubblesort1(tab2);


        Oseba.setAtr(2);
        Oseba.setSmer(1);
        System.out.println("\nLeto, narascajoce");
        Oseba.bubblesort1(tab2);
        Oseba.setSmer(-1);
        System.out.println("\nLeto, padajoce");
        Oseba.bubblesort1(tab2);

    }
}



class Oseba implements Comparable{
    final static String[] IMENA = {
            "Ana", "Bor", "Cvetka", "Dusan", "Meta", "Nina", "Mia", "Karmen"};
    final static String[] PRIIMKI = {
            "Arh", "Buh", "Cah", "Duh", "Knez", "Velikonja", "Novak", "Nah"};

    static int atr = 0;
    static int smer = 1;
    String ime, priimek;
    int letoR;

    Oseba(String ime, String priimek, int leto) {
        this.ime = ime;
        this.priimek = priimek;
        this.letoR = leto;
    }

    public String toString() {
        switch(atr) {
            case 0:
                return this.ime;
            case 1:
                return this.priimek;
            case 2:
                return String.valueOf(this.letoR);
        }
        return "";
    }
    public static void setAtr(int a) {
        atr = a;
    }
    public static int getAtr() {
        return atr;
    }
    public static void setSmer(int d) {
        smer = d;
    }
    public static int getSmer() {
        return smer;
    }

    public static void bubblesort1(Comparable[] tab) {
        System.out.println(toStringArr(tab, -1));
        int last;
        Comparable tmp;
        for (int i = 0; i < tab.length-1; i++) {
            tmp = tab[tab.length-1];
            last = tab.length-2;
            for (int j = tab.length-2; j >= i; j--) {
                if(tab[j].compareTo(tmp) > 0) {
                    tab[j+1] = tab[j];
                    last = j;
                }
                else {
                    tab[j+1] = tmp;
                    tmp = tab[j];
                }
            }
            tab[i] = tmp;
            i = last;
            System.out.println(toStringArr(tab, i));
        }
    }
    static String toStringArr(Comparable[] tab, int mesto) {
        String out = "";
        String ele = "";
        for (int i = 0; i < tab.length; i++) {
            out += tab[i].toString() + " ";
            if(mesto > -1 && mesto == i)
                out += "| ";

        }
        return out;
    }

    @Override
    public int compareTo(Object o) {
        switch(atr) {
            case 0:
                return (this.ime.compareTo(((Oseba)o).ime)) * smer;
            case 1:
                return (this.priimek.compareTo(((Oseba)o).priimek)) * smer;
            case 2:
                return (this.letoR - ((Oseba)o).letoR) * smer;
            default:
                return 0;
        }
    }
}