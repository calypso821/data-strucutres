package Izzivi;

import java.awt.*;

public class Izziv2 {
    public static void main(String[] args) {
        CompleteBinaryTreeDrawer bt = new CompleteBinaryTreeDrawer(args[0]);
        //bt.drawLevelorder();
        //bt.drawPreorder(0);
        //bt.drawInorder(0);
        bt.drawPostorder(0);
    }
}

class CompleteBinaryTreeDrawer {
    int size;
    String[] elements;
    int[] x;
    int[] y;

    CompleteBinaryTreeDrawer(String n) {
        this.size = Integer.parseInt(n);
        this.elements = new String[this.size];
        for (int i = 0; i < this.size; i++) {
            elements[i] = String.valueOf(i+1);
        }
        this.x = new int[this.size];
        int y = (int) (Math.log(this.size) / Math.log(2)) + 1;
        this.y = new int[this.size];
        traverse(0, 0, 0);
        StdDraw.setCanvasSize(1500, 800);
        StdDraw.setXscale(-1, this.size);
        StdDraw.setYscale(y, -1);

    }
    int traverse(int i, int x, int y) {
        // levi otrok
        int levi = 2*i+1;
        int desni = 2*i+2;
        // levi otrok
        if(levi < this.size)
            x = traverse(levi, x, y+1);
        // Vozlisce
        this.x[i] = x;
        this.y[i] = y;
        x++;
        // desni otrok
        if(desni < this.size)
            x = traverse(desni, x, y+1);
        return x;
    }
    void drawNode(int i) {
        StdDraw.setPenColor(Color.blue);
        StdDraw.filledCircle(this.x[i], this.y[i], 0.3);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(this.x[i], this.y[i], elements[i]);
    }
    void drawEdgeToParent(int i) {
        StdDraw.setPenColor(Color.black);
        StdDraw.line(this.x[i], this.y[i], this.x[(i-1)/2], this.y[(i-1)/2]);
    }
    void drawLevelorder() {
        for (int i = 0; i < elements.length; i++) {
            if(this.y[i] != 0) // ni root
                drawEdgeToParent(i);
            drawNode(i);
        }
    }
    void drawPreorder(int i) {
        if(this.y[i] != 0) // ni root
            drawEdgeToParent(i);
        drawNode(i);
        if(2*i+1 < this.size)
            drawPreorder(2*i+1);
        if(2*i+2 < this.size)
            drawPreorder(2*i+2);
    }
    void drawInorder(int i) {
        if(2*i+1 < this.size)
            drawInorder(2*i+1);
        if(this.y[i] != 0) // ni root
            drawEdgeToParent(i);
        drawNode(i);
        if(2*i+2 < this.size)
            drawInorder(2*i+2);
    }
    void drawPostorder(int i) {
        if(2*i+2 < this.size)
            drawPostorder(2*i+1);
        if(2*i+2 < this.size)
            drawPostorder(2*i+2);
        if(this.y[i] != 0) // ni root
            drawEdgeToParent(i);
        drawNode(i);
        
    }

}
