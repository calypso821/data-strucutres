package zigzag;

class Solution {
    public static void main (String[] args) {
        String test = convert("PAYPALISHIRING", 4);
        System.out.println(test);

    }
    static public String convert(String s, int numRows) {
        String out = "";
        if(numRows == 1)
            out = s;
        else {
            int len = s.length();
            int index = 0;
            int row = 0;
            int empty = numRows - 2;
            int maxl = numRows + empty;
            int next_char = maxl-2;
            char [] tab = new char[len];

            // first row
            for(int i = row; i < len; i+= maxl){
                tab[index++] = s.charAt(i);
            }


            row++;

            while(row < numRows-1) { // rows
                for(int i = row; i < len; i+= maxl) {
                    tab[index++] = s.charAt(i); // first char
                    if (i+next_char < len)
                        tab[index++] = s.charAt(i + next_char); // sec char
                }

                row++;
                next_char -= 2;
            }

            // last row
            for(int i = row; i < len; i+= maxl){
                tab[index++] = s.charAt(i);
            }
            out = String.valueOf(tab);

        }

        return out;



    }
}