package array;

public class main {
    public static void main(String[] args) {
        TabelaTabel tab = new TabelaTabel();
        tab.init(1);
        int[] tab1 = new int[]{2,4,5,8};
        int[] tab2 = new int[]{1,3,6,7};
        tab.insert(5);
        tab.insert(4);
        tab.insert(3);
        tab.insert(9);
        tab.insert(6);
        tab.insert(2);
        tab.print();
        System.out.println("Index of element:" + tab.delete(9));
        tab.print();
        System.out.println(tab.size());
        System.out.println(tab.length());
        System.out.println("---------------");
        PovezanSeznam ps = new PovezanSeznam();
        ps.init(0);
        ps.insert(1);
        ps.insert(2);
        ps.insert(3);
        ps.insert(4);
        ps.delete(4);
        ps.print();



    }
}
