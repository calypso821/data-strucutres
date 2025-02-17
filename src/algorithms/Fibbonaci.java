package fibbo;

public class Fibbonaci {
    public static void main(String[] args) {
        System.out.println(Afib1(14));
        System.out.println(Afib(14));
        System.out.println(Bfib(150));
        System.out.println(Bfib1(150));

    }
    static int Afib(int n) {
        int[] fibs = new int[n+1];
        fibs[0] = fibs[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibs[i] = fibs[i-1] + fibs[i-2];
        }
        return fibs[n];
    }
    static int Afib1(int n) {
        int[] fibs = new int[2];
        fibs[0] = fibs[1] = 1;
        int i1 = 0;
        for (int i = 2; i <= n; i++) {
            i1 = i % 2;
            fibs[i1] = fibs[(i-1) % 2] + fibs[(i-2) % 2];
        }
        return fibs[i1];
    }
    static int Bfib(int n) {
        if(n == 0)
            return 1;
        n--;
        int[] fibs = new int[n+4];
        fibs[0] = fibs[1] = fibs[2] = fibs[3] = 1;
        for (int i = 4; i <= n; i++) {
            fibs[i] = fibs[i-2] + fibs[i-4];
        }
        return fibs[n];
    }
    static int Bfib1(int n) {
        if(n == 0)
            return 1;
        n--;
        int[] fibs = new int[4];
        fibs[0] = fibs[1] = fibs[2] = fibs[3] = 1;
        int i1 = 0;
        for (int i = 4; i <= n; i++) {
            i1 = i % 4;
            fibs[i1] = fibs[(i-2) % 4] + fibs[(i-4) % 4];
        }
        return fibs[i1];
    }
}
