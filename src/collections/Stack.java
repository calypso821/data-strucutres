package Vaje;

import java.util.Set;
import java.util.Stack;

public class V2 {
    public static void main(String[] args) {

        // v mnozico dodamo vsa mesta (vozlisca)
        // while(mesta) {
        // >
        Stack s = new Stack();
        s.push('A');
        s.push('B');
        s.push('C');
        s.push('D');
        s.push('E');
        s.push('F');
        s.push('G');
        System.out.println(s);
        //Stack obrnjen = obrni1(s);
        //System.out.println(obrnjen);
        //obrni(s, 2, 3);
        pogrezni(s, 2, 3);
        //int obrnjen1 = izloci(s, 'A');
        System.out.println(s);

    }
    public static void obrni(Stack s) {
        Stack s1 = new Stack();
        Stack s2 = new Stack();


        while(!s.empty()) {
            s1.push(s.pop());
        }
        while(!s1.empty()) {
            s2.push(s1.pop());
        }
        while(!s2.empty()) {
            s.push(s2.pop());
        }
    }
    public static void obrni(Stack s, int n, int m) {
        Stack s1 = new Stack(); // pomozni stack za n elemntov
        Stack s2 = new Stack(); // 1. pomozni stack za m elementov
        Stack s3 = new Stack(); // 2. pomozni stack za m elementov

        // prvih n elementov
        for (int i = 0; i < n; i++) {
            s1.push(s.pop());
        }
        // naslednjih m (za obrnit) elementov
        for (int i = 0; i < m; i++) {
            s2.push(s.pop());
        }
        // m obrnjenih na pomozni sklad
        while(!s2.empty()) {
            s3.push(s2.pop());
        }
        // m (obrnjenih) elemntov nazaj na sklad (s)
        while(!s3.empty()) {
            s.push(s3.pop());
        }
        // n elemntov nazaj na sklad (s)
        while(!s1.empty()) {
            s.push(s1.pop());
        }
    }
    public static void pogrezni(Stack s, int n, int m) {
        Stack s1 = new Stack(); // pomozni stack za n elemntov (pogreznit)
        Stack s2 = new Stack(); // 1. pomozni stack za m elementov
        Stack s3 = new Stack(); // 2. pomozni stack za m elementov

        // prvih n elementov (za pogreznit)
        for (int i = 0; i < n; i++) {
            s1.push(s.pop());
        }
        // naslednjih m elementov
        for (int i = 0; i < m; i++) {
            s2.push(s.pop());
        }
        // nazaj n (pogreznenih)
        while(!s1.empty()) {
            s.push(s1.pop());
        }
        // nazaj m
        while(!s2.empty()) {
            s.push(s2.pop());
        }
    }
    public static int izloci(Stack s, Object o) {
        Stack s1 = new Stack();
        int cnt = 0;
        while(!s.empty()) {
            Object o1 = s.pop();
            if(o1.equals(o))
                cnt++;
            else
                s1.push(o1);
        }
        while(!s1.empty()) {
            s.push(s1.pop());
        }
        return cnt;
    }
    public static Object[] preuredi(Stack s) {
        Object[] tab_type = new Object [15];
        int[] tab_num = new int[15];
        int n_type = 0;
        boolean exist = false;
        while(!s.empty()) {
            Object o1 = s.pop();
            for (int i = 0; i < tab_type.length; i++) {
                if (o1.getClass().equals(tab_type[i].getClass())) {
                    exist = true;
                    tab_num[i]++;
                    break;
                }
            }
            if (!exist) {
                tab_type[n_type] = o1;
                tab_num[n_type]++;
            }
        }
        Object[] tab1 = new Object[n_type];
        for (int i = 0; i < n_type; i++) {
            Object[] tmp = new Object[tab_num[i]];

        }
        return tab1;

    }
}
