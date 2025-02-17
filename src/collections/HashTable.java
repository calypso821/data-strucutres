package Naloga3;

public class HTB {
    int p;
    int m, z;
    Key[] tab;
    int c1, c2;
    int collision;

    HTB(int p, int m, int c1, int c2) {
        this.z = 0;
        this.p = p;
        this.m = m;
        this.c1 = c1;
        this.c2 = c2;
        tab = new Key[m];
        this.collision = 0;
    }

    class Key {
        int key; // vrednsot key
        Key(int key) {
            this.key = key;
        }
    }
    void resize() {
        m = m*2+1;
        z = 0;
        Key[] old_tab = tab;
        tab = new Key[m];
        for (int i = 0; i < old_tab.length; i++) {
            if(old_tab[i] != null)
                insert(old_tab[i].key);
        }
    }

    void insert(int key) {
        int base = (key * p) % m;
        int i = 1;
        int h = base;
        while(tab[h] != null && i <= m) {
            if(tab[h].key == key) // key element already exist (duplicate)
                break;
            h = hash(i, base);
            collision++;
            i++;
        }
        if (tab[h] == null) { // empty hash (space), insert key
            tab[h] = new Key(key);
            z++;
        } else { // no empty hash (space) - resize
            if(tab[h].key != key) {
                resize();
                insert(key);
            }
        }
    }

    boolean find(int key) {
        int h = get_index(key);
        if(h > -1)
            return true;
        else
            return false;
    }

    void delete(int key) {
        int h = get_index(key);
        if(h > -1){
            tab[h] = null;
            z--;
        }
    }
    int get_index(int key) {
        int base = (key * p) % m;
        int i = 1;
        int h = base;
        while (tab[h] != null && i <= m) {
            if (tab[h].key == key) // if key in tab, return index
                return h;
            h = hash(i, base);
            i++;
        }
        return -1; // if key not in tab, return -1

    }


    int hash(int i, int base) {
        int h1, h2;
        h1 = c1*i;
        h2 = (int)(c2*(Math.pow(i, 2)));
        return (base + h1 + h2) % m;
    }
    void printCollisions() {
        System.out.println(collision);
    }
    void printKeys() {
        if(z > 0){
            for (int i = 0; i < m; i++) {
                if(tab[i] != null)
                    System.out.println(i + ": " + tab[i].key);
            }
        }
    }
}

// public class main {

//     public static void main(String[] args) {
//         HTB ht = new HTB(7, 3, 5, 7);

//         ht.insert(19);
//         ht.insert(11);
//         ht.insert(23);
//         ht.insert(19);
//         ht.insert(29);
//         ht.insert(17);
//         ht.insert(2);
//         ht.insert(33);
//         ht.insert(99);
//         ht.insert(129);

//         ht.printKeys();
//         System.out.println("--");
//         ht.printCollisions();


//     }

// }
