package Naloga4;

public class binheap {
    Key[] tab;
    int m;
    int z;
    int c;

    binheap() {
        this.tab = new Key[2];
        this.m = 2;
        this.z = 1;
        this.c = 0;
    }

    class Key {
        int key; // vrednsot key
        Key(int key) {
            this.key = key;
        }
    }

    void resize() {
        m = m*2;
        Key[] old_tab = tab;
        tab = new Key[m];
        for (int i = 0; i < old_tab.length; i++) {
            tab[i] = old_tab[i];
        }
    }

    void insert(int key) {
        if(z == m)
            resize();
        tab[z] = new Key(key);
        push();
        z++;
    }
    void push() {
        int index = z;
        while (index > 1) {
            c++;
            Key parent = tab[index / 2]; // parent i/2
            Key child = tab[index];
            if (child.key < parent.key) {
                tab[index] = parent;
                tab[index / 2] = child;
            }
            else
                break;
            index /= 2;
        }
    }
    void deleteMin() {
        if(z == 1)
            System.out.println("false");
        else {
            System.out.println("true: " + tab[1].key);
            z--;
            tab[1] = tab[z];
            tab[z] = null;
            swap();
        }
    }
    void swap(){
        int index = 1;

        while (index < z-1) {
            Key parent = tab[index];
            Key Lchild = tab[index * 2];
            Key Rchild = tab[index * 2 + 1];

            if(Lchild == null && Rchild == null)
                break;
            if(Lchild == null) { // desni otrok
                c++;
                if (Rchild.key < parent.key) {
                    tab[index * 2 + 1] = parent;
                    tab[index] = Rchild;
                }
                index = index * 2 + 1;
            }
            else if(Rchild == null) { // levi otrok
                c++;
                if (Lchild.key < parent.key) {
                    tab[index * 2] = parent;
                    tab[index] = Lchild;
                }
                index = index * 2;
            }
            else{ // 2 otroka
                if(parent == tab[1]) { // check if root
                    c++;
                    if (Lchild.key < Rchild.key) { // levi manjsi (levi v root)
                        tab[index * 2] = parent;
                        tab[index] = Lchild;
                        index = index * 2;
                    }
                    else { // desni manjsi (desni v root)
                        tab[index * 2 + 1] = parent;
                        tab[index] = Rchild;
                        index = index * 2 + 1;
                    }
                }
                else { // ni root (notranje vozlisce)
                    c++;
                    if (Lchild.key < Rchild.key) { // levi otrok manjsi
                        c++;
                        if(Lchild.key < parent.key) { // ce je manjsi kot stars, ga zamenjamo
                            tab[index * 2] = parent;
                            tab[index] = Lchild;
                        }
                        index = index * 2;
                    } else { // desni otrok manjsi
                        c++;
                        if(Rchild.key < parent.key) { // ce je manjsi kot stars, ga zamenjamo
                            tab[index * 2 + 1] = parent;
                            tab[index] = Rchild;
                        }
                        index = index * 2 + 1;
                    }
                }
            }
        }
    }
    void printMin() {
        if(z == 1)
            System.out.println("MIN: none");
        else
            System.out.println("MIN: " + tab[1].key);
    }
    void printComparisons() {
        System.out.println("COMPARISONS: " + this.c);
    }

    void printElements() {
        if(z == 1)
            System.out.println("empty");
        else {
            System.out.print(tab[1].key);
            for (int i = 2; i < tab.length; i++) {
                if(tab[i] != null) {
                    System.out.print(", ");
                    System.out.print((tab[i].key));
                }
            }
            System.out.println();
        }
    }
}

// public class main {
//     public static void main(String[] args) {
//         binheap bh = new binheap();

//         bh.printElements();
//         bh.printMin();
//         bh.printComparisons();
//         bh.insert(30); bh.insert(20); bh.insert(10);
//         bh.insert(7); bh.insert(25); bh.insert(5); bh.insert(15);
//         bh.printElements();
//         bh.printMin();
//         bh.printComparisons();
//         bh.deleteMin();
//         bh.printElements();
//         bh.printComparisons();
//         bh.deleteMin();
//         bh.printElements();
//         bh.deleteMin();
//         bh.printElements();
//         bh.deleteMin(); bh.deleteMin(); bh.deleteMin();
//         bh.printElements();
//         bh.deleteMin();
//         bh.printElements();
//         bh.printComparisons();
//         bh.deleteMin();
//     }
// }
