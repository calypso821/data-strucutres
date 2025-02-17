package Naloga5;

public class main {
    public static void main(String[] args) {
        int cells[][] = new int[][] { { 0, 0, 0, 1 },
                { 0, 0, 1, 0 },
                { 0, 1, 0, 0 },
                { 1, 0, 0, 0 } };

        LBR1 l = new LBR1(cells);
        l.printPath(1, 16);

    }

}
