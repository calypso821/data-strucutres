package tree;

public class AVLTree {
    Elt elt;
    int visina;
    AVLTree left, right;

    AVLTree(Elt elt, AVLTree left, AVLTree right, int visina) {
        this.elt = elt;
        this.visina = visina;
        this.left = left;
        this.right = right;
    }
}


