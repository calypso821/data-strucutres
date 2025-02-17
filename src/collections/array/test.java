package array;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            prime(i);
        }
    }
    public static void prime(int st) {
        int k = st;
        boolean pra_stevilo = true;
        for (int i = 2; i <= k/2; i++) {
            if(k % i == 0){
                pra_stevilo = false;
                break;
            }
        }
        if(k > 1 && pra_stevilo)
            System.out.println(k);
    }
}
