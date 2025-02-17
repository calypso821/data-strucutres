package Naloga2;

public class main {
    public static void main (String[] args) {

/*
        BST b = new BST();

        b.insert(17);
        b.insert(6);
        b.delete(12);
        b.insert(7);
        b.insert(2);

        b.delete(6);
        b.delete(6);
        b.delete(18);
        b.delete(16);
        b.delete(4);
        b.delete(5);
        b.insert(5);
        b.delete(11);
        b.insert(2);


        b.insert(19);
        b.delete(13);
        b.insert(12);
        b.delete(14);

        b.insert(13);
        b.insert(18);
        b.insert(12);
        b.delete(17);







        b.printInorder();



 */
        BST b = new BST();

        b.insert(2);
        b.insert(1);
        b.insert(3);
        b.delete(2);
        b.insert(0);
        b.delete(1);
        System.out.println("--");
        b.printPreorder();




    }
}

/*
    void insert(int key) {
        Node ins = new Node(key);
        Node cmp = root; // vozlisce, ki ga primerjamo
        // 1. element = root
        if (root == null)
            root = ins;
        else { // vstavimo
            while (true) {
                // obiscemo vozlisce (primerjamo)
                cnt_p ++;
                // element == vozlisce (povecamo STEVEC)
                if (key == cmp.key) {
                    root.c += 1;
                    break;
                }
                if(key < cmp.key) {
                    // LEVO poddrevo
                    if (cmp.left == null) { // nima levega otroka
                        // vstvimo elemnt
                        cmp.left = ins;
                        break;
                    }
                    cmp = cmp.left;
                }
                else {
                    // DESNO poddrevo
                    if (cmp.right == null) { // nima desnega otroka
                        // vstvimo elemnt
                        cmp.right = ins;
                        break;
                    }
                    cmp = cmp.right;
                }
            }
        }
    }

     */