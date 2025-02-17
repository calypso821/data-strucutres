package tree;

public class Tree {
    Elt elt;
    Tree left;
    Tree right;

    Tree(Elt elt, Tree left, Tree right) {
        this.elt = elt;
        this.left = left;
        this.right = right;
    }
}
class Elt {
    int key;

    Elt(int key) {
        this.key = key;
    }
}
