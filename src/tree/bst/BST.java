package Naloga2;

public class BST {
    int cnt_p;
    boolean left;
    Node root;

    BST(){
        this.root = null;
        this.cnt_p = 0;
        left = true;
    }

    static class Node {
        int key; // vrednsot key
        int c; // stevec pojavnosti
        Node left; // levi sosed
        Node right; // desni sosed

        Node(int key, int c) {
            this.key = key;
            this.c = c;
            left = null;
            right = null;
        }

    }
    void insert(int key) {
        Node ins = new Node(key, 1);
        // 1. element = root
        if (root == null)
            root = ins;
        else
            insertRecursion(root, ins);
    }

    void insertRecursion(Node node, Node ins) {
        cnt_p++;

        if(ins.key < node.key) {
            // levo poddrevo
            if (node.left == null)
                node.left = ins;
            else
                insertRecursion(node.left, ins);
        }
        else if(ins.key > node.key) {
            // desno poddrevo
            if (node.right == null)
                node.right = ins;
            else
                insertRecursion(node.right, ins);
        }
        else
            node.c ++;
    }

    boolean find(int key) {
        return search(root, key);
    }
    boolean search(Node node, int key) {
        if(node == null)
            return false;
        cnt_p++;
        if(node.key == key)
            return true;

        if(key < node.key)
            // levo poddrevo
            return search(node.left, key);
        else
            // desno poddrevo
            return search(node.right, key);
    }
    void delete(int key) {
        root = deleteRecursive(root, key);
    }
    Node deleteRecursive(Node node, int key) {
        Node tmp;

        if(node == null)
            return node;
        cnt_p++;

        if(key < node.key)
            // levo poddrevo
            node.left = deleteRecursive(node.left, key);
        else if (key > node.key)
            // desno poddrevo
            node.right = deleteRecursive(node.right, key);
        else {
            // node == key (deleted element)
            if(node.c > 1)
                node.c--;
            else{
                if(node.left == null) // 1 child on right (or null)
                    return node.right;
                else if(node.right == null) // 1 child on left (or null)
                    return node.left;
                else{ // 2 child (find MaxL / MinR)
                    if(this.left) {
                        // left side
                        node.left = maxLeft(node.left, node);
                        this.left = false;
                    }
                    else {
                        // right side
                        node.right = minRight(node.right, node);
                        this.left = true;
                    }
                }
            }
        }
        return node;
    }
    Node maxLeft(Node node, Node toSet) {
        if(node.right == null) {
            toSet.key = node.key;
            toSet.c = node.c;
            return node.left;
        }
        else
            node.right = maxLeft(node.right, toSet);
        return node;
    }
    Node minRight(Node node, Node toSet) {
        if(node.left == null) {
            toSet.key = node.key;
            toSet.c = node.c;
            return node.right;
        }
        else
            node.left = maxLeft(node.left, toSet);
        return node;
    }

    void printInorder() {
        inOrderRecursion(root);
    }
    void inOrderRecursion(Node node) {
        if(node == null)
            return;
        // levo poddrevo
        inOrderRecursion(node.left);
        // izpisemo koren
        System.out.println(node.key);
        // desno poddrevo
        inOrderRecursion(node.right);
    }

    void printPreorder() {
        preOrderRecursion(root);
    }
    void preOrderRecursion(Node node) {
        if(node == null)
            return;
        // izpisemo koren
        System.out.println(node.key);
        // levo poddrevo
        preOrderRecursion(node.left);
        // desno poddrevo
        preOrderRecursion(node.right);
    }

    void printPostorder() {
        postOrderRecursion(root);
    }
    void postOrderRecursion(Node node) {
        if(node == null)
            return;
        // levo poddrevo
        postOrderRecursion(node.left);
        // desno poddrevo
        postOrderRecursion(node.right);
        // izpisemo koren
        System.out.println(node.key);
    }

    void printNodeComparisons() {
        System.out.println(this.cnt_p);
    }
}
