import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*

class Naloga31  {
    static String run() throws FileNotFoundException {
        boolean undireceted = true;
        File file = new File("Files//primer1.txt");
        Scanner sc = new Scanner(file);


        String[] line1 = sc.nextLine().trim().split(" ");
        if(line1[0].equals("directed"))
            undireceted = false;

        int vertices_num = Integer.parseInt(sc.nextLine().trim());
        int [][] matrix = new int[vertices_num][vertices_num];
        int edges_num = 0;

        while(sc.hasNextInt()) {
            edges_num++;
            int edge_out = sc.nextInt();
            int edge_in = sc.nextInt();
            matrix[edge_out][edge_in] = 1;
            if(undireceted) {
                if(matrix[edge_in][edge_out] == 1)
                    edges_num--;
                matrix[edge_in][edge_out] = 1;
            }

        }

        Graph g1 = new Graph(matrix, vertices_num, edges_num, undireceted);
        g1.powerOfVertices();
        String methode = line1[1];
        try {
            switch (methode) {
                case "info": g1.info(); break;
                case "walks": g1.walks(Integer.parseInt(line1[2])); break;
                case "dfs": g1.runDFS();break;
                case "bfs": g1.runBFS();break;
                case "sp": g1.shortestPath(Integer.parseInt(line1[2]));break;
                case "comp": g1.comp(true);break;
                case "ham":
                    g1.comp(false);
                    g1.connectComp();
                    g1.hamiltonCycle();
                    break;
            }
        }
        catch(Exception e) {
            System.out.println("Exception error!");
        }
        return g1.tabToString(g1.hamCycle);

    }
}

class Graph {
    static boolean found;
    int size;
    int[] hamCycle;
    boolean undirected;
    int[][] matrix;
    int num_vertices;
    int num_edges;
    int[][] pow_vertices;
    Sequence<Component> comp_arr;

    Graph(int[][] matrix, int num_vertices, int num_edges, boolean undirected) {
        this.undirected = undirected;
        this.matrix = matrix;
        this.size = matrix.length;
        this.num_vertices = num_vertices;
        this.num_edges = num_edges;
    }
    void powerOfVertices() {
        int[][] tab = new int[this.num_vertices][2];

        for (int i = 0; i < this.num_vertices; i++) {
            for (int j = 0; j < this.num_vertices; j++) {
                if(this.matrix[i][j] == 1) {
                    tab[i][0]++;
                    tab[j][1]++;
                }
            }
        }

        this.pow_vertices = tab;
    }
    void walks(int n) {
        int[][] mat = this.matrix;
        for (int i = 0; i < n-1; i++) {
            mat = matrixMultiplication(mat, this.matrix);
        }

        for (int i = 0; i < mat.length; i++) {
            System.out.print(mat[i][0]);
            for (int j = 1; j < mat[0].length; j++) {
                System.out.print(" " + mat[i][j]);
            }
            System.out.print('\n');
        }
    }
    int[][] matrixMultiplication(int[][] mat1, int[][] mat2) {
        int sum1;
        int[][] new_mat = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                sum1 = 0;
                for (int k = 0; k < mat1[0].length; k++) {
                    sum1 += mat1[i][k] * mat2[k][j];
                }
                new_mat[i][j] = sum1;
            }
        }
        return new_mat;
    }
    void info() {
        System.out.print(this.num_vertices + " " + this.num_edges + '\n');
        for (int i = 0; i < pow_vertices.length; i++) {
            System.out.print(i + " " + this.pow_vertices[i][0]);
            if(!undirected)
                System.out.print(" " + this.pow_vertices[i][1]);
            System.out.print('\n');
        }
    }
    void hamiltonCycle() {
        found = false;
        int[] tab = new int[this.size];
        for (int i = 1; i < tab.length; i++) {
            tab[i] = -1;
        }

        hamilton(1, tab);
    }

    void hamilton(int depth, int[] tab) {
        while(!found) {
            nextVertex(depth, tab);
            if(tab[depth] == -1)
                return;
            if(depth == tab.length-1) {
                this.hamCycle = tab;
                //System.out.println(tabToString(tab));
                found = true;
            }

            else
                hamilton(depth + 1, tab);
        }
    }

    void nextVertex(int depth, int[] tab) {
        // cheack all possible values for node
        while(true) {
            // next possible node (+1)
            tab[depth] = ((tab[depth] + 1 + 1) % (tab.length+1)) -1;

            if (tab[depth] == -1)
                return;

            // edge to last node
            if (this.matrix[tab[depth]][tab[depth - 1]] == 1) {
                int i;
                for (i = 0; i < depth; i++) {
                    // already used vertices
                    if (tab[depth] == tab[i])
                        break;
                }

                if (i == depth) {

                    // check if last node AND have connection to 1.st node

                    if (depth < tab.length-1 || depth == tab.length-1 && this.matrix[tab[depth]][tab[0]] == 1)
                        return; // Hamilton Cycle
                }
            }
        }

    }
    void connectComp() throws CollectionException {
        int vertex1;
        int vertex2;
        if(this.comp_arr.size() > 1) {
            for (int i = 0; i < this.comp_arr.size(); i++) {
                // min - comp
                vertex1 = this.comp_arr.get(i % this.comp_arr.size()).min;
                // max - comp+1
                vertex2 = this.comp_arr.get((i + 1) % this.comp_arr.size()).max;

                this.matrix[vertex1][vertex2] = 1;
                this.matrix[vertex2][vertex1] = 1;
            }
        }

    }
    void comp(boolean print) throws CollectionException {
        if(this.undirected) this.makeUndirectedComp();
        else this.makeDirectedComp();

        if(print)
            for (int i = 0; i < this.comp_arr.size(); i++) {
                System.out.println(this.comp_arr.get(i).seq);
            }
    }
    void makeDirectedComp() throws CollectionException {
        boolean[] tab = new boolean[this.size];
        Sequence<Integer> out = new ArrayDeque<>(this.size);
        Sequence<Component> comp_arr = new ArrayDeque<>(this.size);

        // opposite DFS
        for (int i = 0; i < tab.length; i++) {
            if(!tab[i]) {
                DFS(i, tab, new ArrayDeque<>(this.size), out);
                out.add(i);
            }
        }

        // make components
        Sequence<Integer> in = new ArrayDeque<>(this.size);
        tab = new boolean[this.size];
        int index;
        for (int i = out.size()-1; i >= 0; i--) {
            index = out.get(i);
            if(!tab[index]) {
                in.add(index);
                // obratne povezave
                transposeDFS(out.get(i), tab, in, new ArrayDeque<>(this.size));
                Component comp = new Component(in);
                comp.sort();
                comp_arr.add(comp);
                in = new ArrayDeque<>(this.size);
            }
        }

        this.comp_arr = comp_arr;
        this.comp_arr.sort();
    }
    void makeUndirectedComp() throws CollectionException {
        boolean[] tab = new boolean[this.size];
        Sequence<Integer> in = new ArrayDeque<>(this.size);
        Sequence<Component> comp_arr = new ArrayDeque<>(this.size);

        // make components
        for (int i = 0; i < tab.length; i++) {
            if(!tab[i]) {
                in.add(i);
                DFS(i, tab, in, new ArrayDeque<>(this.size));
                Component comp = new Component(in);
                comp.sort();
                comp_arr.add(comp);
                in = new ArrayDeque<>(this.size);
            }
        }
        this.comp_arr = comp_arr;
        this.comp_arr.sort();
    }
    void runDFS() throws CollectionException {
        boolean[] tab = new boolean[this.size];
        Sequence<Integer> in = new ArrayDeque<>(this.size);
        Sequence<Integer> out = new ArrayDeque<>(this.size);

        for (int i = 0; i < tab.length; i++) {
            if(!tab[i]) {
                in.add(i);
                DFS(i, tab, in, out);
                out.add(i);
            }
        }

        System.out.println(in);
        System.out.println(out);
    }
    void transposeDFS(int node, boolean[] tab, Sequence<Integer>  in, Sequence<Integer> out) throws CollectionException {
        tab[node] = true;
        for (int i = 0; i < this.matrix[node].length; i++) {
            if(this.matrix[i][node] == 1 && !tab[i]) {
                in.add(i);
                transposeDFS(i, tab, in, out);
                out.add(i);
            }
        }
    }
    void DFS(int node, boolean[] tab, Sequence<Integer>  in, Sequence<Integer> out) throws CollectionException {
        tab[node] = true;
        for (int i = 0; i < this.matrix[node].length; i++) {
            if(this.matrix[node][i] == 1 && !tab[i]) {
                in.add(i);
                DFS(i, tab, in, out);
                out.add(i);
            }
        }
    }

    void runBFS() throws CollectionException {
        boolean[] tab = new boolean[this.size];
        StringBuilder in = new StringBuilder();


        for (int i = 0; i < tab.length; i++) {
            if(!tab[i])
                BFS(i, tab, in);
        }
        System.out.println(in.deleteCharAt(in.length()-1));
    }
    void shortestPath(int startNode) throws CollectionException {
        for (int i = 0; i < this.matrix.length; i++) {
            int cost = BFS(startNode, i);
            System.out.println(i + " " + cost);
        }
    }
    int BFS(int node, int endNode) throws CollectionException {
        boolean[] tab = new boolean[this.size];
        int[] path = new int[tab.length];
        Deque<Integer> dq  = new ArrayDeque<>(this.size);
        dq.enqueue(node);
        tab[node] = true;
        path[node] = -1;

        while(!dq.isEmpty()) {
            node = dq.dequeue();
            if(node == endNode) {
                // node found
                int cnt = 0;
                int prev = path[node];
                while(prev > -1) {
                    cnt++;
                    prev = path[prev];
                }
                return cnt;
            }


            for (int i = 0; i < this.matrix[node].length; i++) {
                if(this.matrix[node][i] == 1 && !tab[i]) {
                    dq.enqueue(i);
                    path[i] = node;
                    tab[i] = true;
                }
            }
        }
        return -1;
    }
    void BFS(int node, boolean[] tab, StringBuilder in) throws CollectionException {
        Deque<Integer> dq  = new ArrayDeque<>(this.size);
        dq.enqueue(node);
        tab[node] = true;

        while(!dq.isEmpty()) {
            node = dq.dequeue();
            in.append(node).append(" ");

            for (int i = 0; i < this.matrix[node].length; i++) {
                if(this.matrix[node][i] == 1 && !tab[i]) {
                    dq.enqueue(i);
                    tab[i] = true;
                }
            }
        }
    }
    String tabToString(int[] tab) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            str.append(tab[i]).append(" ");
        }
        return str.deleteCharAt(str.length()-1).toString();
    }
}

class Component implements Comparable<Component> {
    int min;
    int max;
    Sequence<Integer> seq;

    Component(Sequence<Integer> seq) {
        this.seq = seq;
    }
    void setMinMax() throws CollectionException {
        this.min = seq.get(0);
        this.max = seq.get(seq.size()-1);
    }
    void sort() throws CollectionException {
        this.seq.sort();
        this.setMinMax();
    }

    @Override
    public int compareTo(Component o) {
        return this.min - o.min;
    }
}

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}


interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}

interface Deque<T extends Comparable<T>> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    T dequeue() throws CollectionException;
}

interface Sequence<T extends Comparable<T>> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
    void sort() throws CollectionException;
}

@SuppressWarnings("unchecked")
class ArrayDeque<T extends Comparable<T>> implements Deque<T>, Sequence<T> {
    //private static final int DEFAULT_CAPACITY = 64;
    private static int DEFAULT_CAPACITY;
    private T[] tab;
    private int num_ele;
    private int front;
    private int back;

    ArrayDeque(int size) {
        DEFAULT_CAPACITY = size;
        this.tab = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.front = DEFAULT_CAPACITY-1;
        this.back = 0;
        this.num_ele = 0;
    }
    private int next(int index) {
        return (DEFAULT_CAPACITY + index + 1) % DEFAULT_CAPACITY;
    }
    private int prev(int index) {
        return (DEFAULT_CAPACITY + index - 1) % DEFAULT_CAPACITY;
    }
    private int curr(int index) {
        return (DEFAULT_CAPACITY + index) % DEFAULT_CAPACITY;
    }

    @Override
    public boolean isEmpty() {
        return num_ele == 0;
    }

    @Override
    public boolean isFull() {
        return num_ele == DEFAULT_CAPACITY;
    }

    @Override
    public int size() {
        return num_ele;
    }


    @Override
    public T front() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return tab[next(front)];
    }

    @Override
    public T back() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return tab[prev(back)];
    }

    @Override
    public void enqueue(T x) throws CollectionException {
        if(isFull())
            throw new CollectionException(ERR_MSG_FULL);
        tab[back] = x;
        back = next(back);
        num_ele++;
    }


    @Override
    public T dequeue() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        front = next(front);
        T ele = tab[front];
        tab[front] = null;
        num_ele--;
        return ele;
    }

    @Override
    public T get(int i) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if(i >= size() || i < 0)
            throw new CollectionException(ERR_MSG_INDEX);
        return tab[i];
    }

    @Override
    public void add(T x) throws CollectionException {
        enqueue(x);
    }
    @Override
    public void sort() throws CollectionException {
        int last;
        for (int i = 1; i < this.size(); i++) {
            last = this.size()-1;
            for (int j = this.size()-1; j >= i; j--) {
                T ele1 = this.tab[curr(j - 1)];
                T ele2 = this.tab[curr(j)];
                if (ele1.compareTo(ele2) > 0) {
                    last = j;
                    T ele = this.tab[curr(j)];
                    this.tab[curr(j)] = this.tab[curr(j-1)];
                    this.tab[curr(j-1)] = ele;
                }
            }
            i = last;
        }
    }
    /*
    @Override
    public String toString() {
        String s;
        if(isEmpty())
            s = "[]";
        else {
            int ele = next(front);
            s = "[" + tab[ele];
            for (int i = 0; i < size()-1; i++) {
                ele = next(ele);
                s += ", " + tab[ele];
            }
            s += "]";
        }
        return s;
    }

     */
/*
    @Override
    public String toString() {
        int ele = next(front);
        String s = String.valueOf(tab[ele]);
        for (int i = 0; i < size()-1; i++) {
            ele = next(ele);
            s += " " + tab[ele];
        }
        return s;
    }
}
*/