package darray;

public class main {
    public static void main(String[] args) {

        ArrArray tbl = new ArrArray();
        tbl.insert(1);
        tbl.insert(2);
        tbl.insert(3);
        tbl.insert(4);
        tbl.insert(5);
        tbl.insert(6);
        tbl.insert(7);
        tbl.delete(5);
        tbl.delete(6);
        tbl.insert(8);
        tbl.printOut();

    }
}
