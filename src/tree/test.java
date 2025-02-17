package tree;

import org.w3c.dom.ls.LSOutput;

public class test {
    public static void main (String[] args) {
        /*
        OrderedTree ot = new OrderedTree();
        ot.insert(50);
        ot.insert(90);
        ot.insert(60);
        ot.insert(30);
        ot.insert(20);
        ot.insert(35);
        ot.insert(41);
        ot.insert(39);
        ot.insert(36);
        ot.insert(37);
        ot.insert(30);

        ot.preorder();
        ot.inorder();
        ot.postorder();

        System.out.println(ot.find(36));
        System.out.println(ot.find(41));
        System.out.println(ot.find(126));

        ot.delete(50);
        ot.inorder();

         */
        int[] tab = new int[]{65,76,71,79,82,73,84,77};
        for (int i = 0; i < tab.length; i++) {
            func1(tab[i]);
        }



    }
    static void func1(int n) {
        int h1 = n * 11 % 9;
        //int h2 = n * 23 % 11;
        System.out.println("h1=" + h1);
        //System.out.println("h2=" + h2);
        System.out.println();


    }
}
