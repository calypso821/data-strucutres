package darray;

//---------------------------------------------
//----------------- RAZREDI ------------------
//---------------------------------------------
// ArrArray - hranjenje tabel Tab
// Tab - hranimo tabelo elemntov: inner_table Ele[], zasedenost tabele z
// Ele - posamezn element: vrednost, izbrisan (T/F), stevec pojavnosti (DUPLIKATI)

//---------------------------------------------
//----------------- FUNKCIJE ------------------
//---------------------------------------------
// init - dinamicno dodajanje novih tabel
// index_prazna - 1. prazna tabela (izracunamo iz BINARNE predstavitve tabele)
// index_prazna - 1. tabela, ki vsebuje "leno" izbrisan element, else INT.MAX
// n - stevilo elementov polnih tabel
// zas - zasedenost celotne tabele

// INSERT - glede na index elementa VSTAVLJANJE ali ZLIVANJE tabel

// VSTAVLJANJE - insertEle, - napredno ZLIVANJE
// 1. SHIFT-RR - najdemo "pravo" mesto, vstavimo x, nato elemnte shiftamo do "leno" izbirsanega elementa ALI
// 2. SHIFT-RL - najdemo "leno" izbirsan element, nato elemnte shiftamo do PRAVEGA mesta, vstavimo x
// IZJEMA (SHIFT-LL) - element x > zadnji elemnt (tab[i][tab.length])

// ZLIVANJE - zlivamo tabele (in jih za sabo praznimo), ko pridemo do 1. prazne, vstavimo elemnte
// Na koncu nastavimo index 1. prazno tabelo, glede na BINARNO predstavitev polnih tabel tabele tabX

// FIND - (Binary Search) binarno iskanje po tabeli
// DELETE - FIND + brisanje
// DUPLIKATI - FIND + insert

public class ArrArray {
    // tabX = tabela tabel
    Tab[] tabX;

    int k; // stevilo tabel (tabeleX)
    int zas; // zasedenost tabeleX (glede na "leno" izbrisane elemnte)
    int n; // stevilo vseh elemtov (polne tabele)
    int index_prazna; // 1. prazna tabela
    int index_vstavljanje; // 1. tabela z "leno" izbrisanim elementom



    ArrArray(){
        // ______init_______
        // ustvarimo prazno tabelo elemntov
        Ele[] tab_ele = new Ele[1];
        Tab tab = new Tab(tab_ele, 0);
        tabX = new Tab[]{tab};

        k = 1;
        zas = 0;
        n = 0;
        index_prazna = 0;
        index_vstavljanje = Integer.MAX_VALUE;

    }

    class Tab {
        // tabela elementov Ele[]
        Ele[] inner_tab;
        // zasedenost tabele TAB (i-te tabele)
        int z;

        Tab(Ele[] tab, int zasedenost) {
            inner_tab = tab;
            z = zasedenost;
        }
    }

    class Ele {
        // posamezen elemnt
        int data; // vrednost - value
        boolean deleted; // "leno" izbrisan (T/F)
        int cnt; // stevec pojavnosti - duplikati

        Ele(int d, boolean del, int c) {
            data = d;
            deleted = del;
            cnt = c;
        }
    }
    public void init() {
        // DINAMICNO dodajanje tabel
        // ko zmanjka tabel, dodamo novo tabelo (vse tabele do k prazne)
        // nova velikost tabX = k+1
        k = k+1;
        // nova tabela tabX
        tabX = new Tab[k];
        Ele[] eles;

        for (int i = 0; i < k; i++) {
            // vstavimo k praznih tabel
            eles = new Ele[(int)Math.pow(2, i)];
            tabX[i] = new Tab(eles, 0);
        }
    }

    public void insert(int x) {
        // ustavirmo nov elemnt in ga dodamo v tabelo
        Ele elt = new Ele(x, false, 1);
        Tab tabA = new Tab(new Ele[]{elt}, 1);
        zas++; // stevilo elemntov v tabeli
        int zasedenost_i;
        boolean exist = false;

        // ---------------------------------------
        // ----------------- FIND ----------------
        // ---------------------------------------
        // pogledamo elemnt x ze obstaja v tabeli (duplikat - povecamo stevec)
        exist = getEle(x, false, true);

        // ---------------------------------------
        // ------------- VSTAVLJANJE -------------
        // ---------------------------------------
        // ce element x ne obstaja v tabeli ga vstavimo (zlivanje/vstavljanje)
        int i = 0; // stevec tabele - i
        while (!exist) {
            // pridemo do zadnje tabele
            if(i == k)
                init(); // dodamo 1 tabelo

            // primerjamo index (1. prazne tabele) in index (zadnjega vstavljanja)
            if(index_prazna < index_vstavljanje) {
                // ------- PRAZNA TABELA -------
                if (tabX[i].z == 0) {
                    // stevec elemntov v tabeli
                    n++;
                    tabX[i] = tabA;
                    // nastavimo index 1. prazne tabele
                    index_prazna = getFirstEmpty(n, k);
                    break;
                }
                // --------- ZLIVANJE ------
                else {
                    tabA.inner_tab = zlij(tabA.inner_tab, tabX[i].inner_tab);
                    tabA.z = tabA.inner_tab.length;
                    tabX[i].z = 0;
                    tabX[i] = new Tab(new Ele[(int)Math.pow(2, i)], 0);
                    i++;
                }
            }
            // ----------- VSTAVLJANJE -----------
            // na mesto "leno" izbrisanega elemnta
            // -----------------------------------
            else {
                // pogledamo index vstavljanja in elemnt vstavimo v tabelo
                int i_vs = index_vstavljanje;
                insertEle(tabX[i_vs].inner_tab, x);
                tabX[i_vs].z++;
                // poiscemo naslednjo delno-zasedeno tabelo - index vstavljanja
                index_vstavljanje = getFirstInsert();
                break;
            }
        }
    }

    // FIND
    public void find(int x) {
        System.out.println(getEle(x, false, false));
    }
    // DELETE = FIND + delete x
    public void delete(int x) {
        System.out.println(getEle(x, true, false));
    }

    // ------------------------------------------------------
    // --------------- Poiscemo element x  ------------------
    //          if (delete == true) - DELETE x
    //          if (insert == true) - INSERT x (duplikati)
    // ------------------------------------------------------
    public boolean getEle(int x, boolean delete, boolean insert){
        int c = 0;
        int res;
        Ele found = null;
        int prvi_ele, zadnji_ele, len;
        while(c < k) {
            // preverimo ali je zasedenost tabele vecja od 0
            if(tabX[c].z > 0 ) {
                len = tabX[c].inner_tab.length;
                prvi_ele = tabX[c].inner_tab[0].data;
                zadnji_ele = tabX[c].inner_tab[len-1].data;

                // pogledamo ali je iskani elemnt x MOGOCE najti v tabeli (min, max)
                if (prvi_ele <= x && x <= zadnji_ele) {
                    // Z binarnim iskanjem iscemo po i-tabeli
                    res = BinarySerch(x, tabX[c].inner_tab, 0, len-1);

                    // Dobimo index najdenega elemnta, -1 ce NE obstaja
                    if (res != -1) {
                        // found = iskani element
                        found = tabX[c].inner_tab[res];

                        // pogledamo ali je element found "leno" brisan
                        if(!found.deleted) {
                            // ---------------------------------
                            // najdeni element NI "leno" brisan
                            // ---------------------------------

                            // --------- funkcija DELETE ---------
                            if (delete) {
                                // ni DUPLIKAT - ga izbrisemo
                                if (found.cnt <= 1) {
                                    found.deleted = true;
                                    found.cnt--;
                                    tabX[c].z--; // zasedenost i - tabele
                                    zas--; // celotna zasedenost

                                    // ce je trenutni index tabele manjsi od indexa vstavljanja
                                    // nastavimo novega
                                    if(c < index_vstavljanje)
                                        index_vstavljanje = c;

                                    // ce je po brisanju elemnta x
                                    // zasedenost c tabele == 0 -> jo spraznemo (tab[c] = null)
                                    if(tabX[c].z == 0){
                                        tabX[c].inner_tab = new Ele[len];
                                        n = n - len;

                                        // nastavimo nov index vstavljanja, ker smo tabelo c izbrisali
                                        if(index_vstavljanje == c)
                                            index_vstavljanje = getFirstInsert();
                                    }
                                }
                                else
                                    // DUPLIKAT (zmanjsamo stevec)
                                    found.cnt--;

                                // DELETE - element smo nasli in ga pobrisali (mu zmanjsali stevec)
                                return true;
                            }
                            // ------------ funkcija INSERT ----------
                            else if(insert){
                                // element NI "leno" brisan - DUPLIKAT
                                // povecamo stevec pojavnosti elemnta
                                found.cnt++;
                                // INSERT - element smo nasli (ze obstaja) DUPLIKAT (pojavnost +1)
                                return true;
                            }
                            // FIND - elemnt smo nasli
                            return true;
                        }
                        // FIND, DELETE - elemnt je "leno" izbrisan, ga nismo nasi/ni za brisanje
                        return false;
                    }
                }
            }
            c++;
        }
        // DELETE, FIND - pregledali celo tabelo, elemnta ni
        return false;
    }

    // ------------------------------------------------------
    // FUNKCIJE za VSTAVLJANJE elementa X v sortirano tabelo
    // ------------------------------------------------------
    public void insertEle(Ele[] tab, int x){
        int len = tab.length;
        int c = 0;

        if(x > tab[len-1].data){
            // PRAVO mesto = tab.length-1 (zadnje mesto v tabeli)
            // SHIFT-LL
            shiftLL(tab, x, len-1);
        }
        else{
            while(c < len){
                // najdemo DELETED element
                // SHIFT-RL
                if(tab[c].deleted) {
                    shiftRL(tab, x, c);
                    break;
                }
                // najdemo PRAVO MESTO (x < tab[c])
                // SHIFT-RR
                else if(x < tab[c].data){
                    shiftRR(tab, x, c);
                    break;
                }
                c++;
            }
        }

    }
    public void shiftLL(Ele[] tab, int x, int index) {
        Ele st = new Ele(x, false, 1);
        Ele st_temp;
        int i = index;
        // ----------------------------------------------------
        // (tab.length-1) = PRAVO mesto (x > tab[length-1])
        // (vstavimo x, zamaknemo elemente v levo do deleted)
        // From - RIGHT to LEFT (tab.length to 0)
        // Shift elements to LEFT
        // ----------------------------------------------------
        while (i >= 0){
            //shranimo trenutnega
            st_temp = tab[i];
            // namesto trenutnega vpisemo st
            tab[i] = st;
            // st za vpis = temp
            st = st_temp;
            // ko pridemo do DELETED se ustavimo
            if(st.deleted)
                break;
            i--;
        }
    }
    public void shiftRR(Ele[] tab, int x, int index) {
        Ele st = new Ele(x, false, 1);
        Ele st_temp;
        int i = index;
        // ----------------------------------------------------
        // Nasli smo PRAVO mesto vstavljanja
        // (vstavimo x, zamaknemo elemente v desno do deleted)
        // From - LEFT to RIGHT (0 to tab.length)
        // Shift elements to RIGHT
        // ----------------------------------------------------
        while (i < tab.length){
            //shranimo trenutnega
            st_temp = tab[i];
            // namesto trenutnega vpisemo st (zamaknemo)
            tab[i] = st;
            // shranimo temp v st (naslednje mesto)
            st = st_temp;
            // ko pridemo do DELETED se ustavimo
            if(st.deleted)
                break;
            i++;
        }
    }
    public void shiftRL(Ele[] tab, int x, int index){
        int i = index;
        // ----------------------------------------------------
        // Nasli smo DELETED element
        // zamikamo elemente v levo, ko najdemo (x < tab[i+x]) break, in vstavimo x
        // From - LEFT to RIGHT (0 to tab.length)
        // Shift elements to LEFT
        // ----------------------------------------------------
        while (i < tab.length-1){
            // ponavljamo dokler ne pridemo x < tab[i+] (naslednji je vecji) - PRAVO mesto
            if (x < tab[i + 1].data)
                break;
            // trenutni, je prejsnji
            tab[i] = tab[i + 1];
            i++;
        }
        tab[i] = new Ele(x, false, 1);
        // vstavimo X
    }

    // ------------------------------------------------------
    // ------------- INDEX 1. PRAZNE tabele -----------------
    // ------------------------------------------------------
    public int getFirstEmpty(int st_vseh_ele, int st_tabel){
        // st_vseh_ele - stevilo elemntov v polnih tabelah
        // BINARNA predstavitev zasedenih tabel
        // shiftamo dokler ne najdemo prve 0 (1. prazna tabela)
        int st = st_vseh_ele;
        int index_firstEmpty = st_tabel;
        int c = 0;
        while(st > 0){
            if(st % 2 == 0) {
                index_firstEmpty = c;
                break;
            }
            c++;
            st = st >> 1;
        }
        return index_firstEmpty;
    }
    // ------------------------------------------------------
    // ----------- INDEX 1. NEZASEDENE tabele ---------------
    // ------------------------------------------------------

    public int getFirstInsert() {
        int c = index_vstavljanje;
        while(c < k){
            // preverimo ali je zasedenost manjsa od velikosti tabele
            if(tabX[c].z < tabX[c].inner_tab.length && tabX[c].z > 0)
                // vrnemo index i tabele, kjer lahko "leno" vstavljamo
                return c;
            c++;
        }
        // nobena tabela nima "leno" izbirsanega elemnta, vse so POLNE
        return Integer.MAX_VALUE;
    }

    // ------------------------------------------------------
    // ----------------- ZLIVANJE 2x tabel ------------------
    // ------------------------------------------------------
    public Ele[] zlij(Ele[] tab1, Ele[] tab2){
        Ele[] new_tab = new Ele[tab1.length+tab2.length];
        int tab1_c = 0, tab2_c = 0;
        int c = 0;

        // razvrstimo elemnte v new array (dokler ne pridemo do konca 1 ali 2 arraya)
        while(tab1_c < tab1.length && tab2_c < tab2.length) {
            if (tab1[tab1_c].data < tab2[tab2_c].data)
                new_tab[c++] = tab1[tab1_c++];
             else
                new_tab[c++] = tab2[tab2_c++];

        }
        // ko pridemo do konca 1 (2) arraya, nato prepisemo se vse preostale elemnte array 2 (1)
        while(tab1_c < tab1.length)
            new_tab[c++] = tab1[tab1_c++];
        while(tab2_c < tab2.length)
            new_tab[c++] = tab2[tab2_c++];
        return new_tab;
    }

    // ------------------------------------------------------
    // ------------ BINARNO ISKANJE po tabeli ---------------
    // ------------------------------------------------------

    int BinarySerch(int x, Ele[] tab, int zac, int konc) {
        int size = konc-zac;
        int sredina = zac + size/2;
        if(x == tab[sredina].data)
            return sredina;
        if(x < tab[sredina].data && size > 0)
            return BinarySerch(x, tab, zac, sredina-1);
        if(x > tab[sredina].data && size > 0)
            return BinarySerch(x, tab,sredina+1, konc);
        return -1;
    }

    // ------------------------------------------------------
    // ------------- IZPIS tabele tabel - tabX --------------
    // ------------------------------------------------------
    public void printOut(){
        Ele ele;
        if(zas == 0)
            System.out.println("empty");
        else {
            for (int i = 0; i < k; i++) {
                System.out.print("A_" + i + ":");
                for (int j = 0; j < tabX[i].inner_tab.length; j++) {
                    if(tabX[i].z == 0){
                        System.out.print(" ...");
                        break;
                    }
                    if (tabX[i].inner_tab[j].deleted) {
                        System.out.print(" x");
                    }
                    else {
                        ele = tabX[i].inner_tab[j];
                        System.out.printf(" %d/%d", ele.data, ele.cnt);
                    }
                    if(j < tabX[i].inner_tab.length-1)
                        System.out.print(",");
                }
                System.out.println();
            }
        }
    }
}
