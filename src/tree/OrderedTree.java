package tree;

public class OrderedTree {
    Tree tree;

    OrderedTree() {
        this.tree = null;
    }

    void insert(int key) {
        this.tree = insert(this.tree, new Elt(key));
    }
    Tree insert(Tree tree, Elt elt) {
        if(tree == null) return new Tree(elt, null, null);
        if(elt.key <= tree.elt.key){
            tree.left = insert(tree.left, elt);
            return tree;
        }
        else {
            tree.right = insert(tree.right, elt);
            return tree;
        }
    }
    boolean find(int key) {
        return find(this.tree, key) != null;
    }
    private Tree find(Tree tree, int key) {
        if(tree == null) return null;
        if(tree.elt.key == key) return tree;
        if(key < tree.elt.key)
            return find(tree.left, key);
        else
            return find(tree.right, key);
    }
    boolean delete(int key) {
        return delete(this.tree, key);

    }
    private boolean delete(Tree tree, int key) {
        tree = find(tree, key);
        if(tree != null) {
            // ni nalsednika (elemnt odstranimo)
            if(tree.left == null && tree.right == null)
                tree = null;
            // 1 naslednik (left)
            else if(tree.right == null)
                tree = tree.left;
                // 1 naslednik (right)
            else if(tree.left == null)
                tree = tree.right;
            else  {
                // 2 naslednika
                //tree.left = getMaxLeft(tree.left, tree);
                tree.right = getMinRight(tree.right, tree);
            }
            return true;
        }
        else
            return false;
    }
    private Tree getMaxLeft(Tree tree, Tree node) {
        if(tree.right == null) { // najdemo Max elemnt
            node.elt = tree.elt;
            return tree.left;
        }
        else {
            tree.right = getMaxLeft(tree.right, node);
            return tree;
        }
    }
    private Tree getMinRight(Tree tree, Tree node) {
        if(tree.left == null) { // najdemo Min elemnt
            node.elt = tree.elt;
            return tree.right;
        }
        else {
            tree.left = getMinRight(tree.left, node);
            return tree;
        }
    }
    void preorder() {
        preorder(this.tree);
        System.out.println();
    }
    private void preorder(Tree tree) {
        if(tree == null) return;
        System.out.print(tree.elt.key + " ");
        preorder(tree.left);
        preorder(tree.right);
    }

    void inorder() {
        inorder(this.tree);
        System.out.println();
    }
    private void inorder(Tree tree) {
        if(tree == null) return;
        inorder(tree.left);
        System.out.print(tree.elt.key + " ");
        inorder(tree.right);
    }
    void postorder() {
        postorder(this.tree);
        System.out.println();
    }
    private void postorder(Tree tree) {
        if(tree == null) return;
        postorder(tree.left);
        postorder(tree.right);
        System.out.print(tree.elt.key + " ");

    }
}


