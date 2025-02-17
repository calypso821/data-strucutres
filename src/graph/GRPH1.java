package Naloga5;

class GRPH1 {
    int vertices;
    Edge[] edges;

    GRPH1(int verticesCount) {
        this.vertices = verticesCount;
        edges = null;
    }

    static class Edge {
        int src;
        int dest;
        int cost;

        Edge(int from, int to, int cost) {
            this.src = from;
            this.dest = to;
            this.cost = cost;
        }
    }

    void addEdge(int from, int to, int cost) {
        if(from >= 0 && to >= 0 && from < vertices && to < vertices){
            // new edge
            Edge povezava = new Edge(from, to, cost);
            // append edge to starting vertex
            if(edges == null) // 1. povezava
                edges = new Edge[]{povezava};
            else // dodamo povezavo v array (append)
                edges = appendEdge(edges, povezava);
        }
    }
    void printShortestDistsFrom(int from) {
        if(from < 0 || from >= vertices)
            return;
        int[] dist = BellmanFord(vertices, edges, from);
        if(dist == null)
            System.out.println("Graf vsebuje negativen cikel!");
        else{
            System.out.println("V .. Cena");
            for (int i = 0; i < dist.length; i++) {
                if(dist[i] == Integer.MAX_VALUE)
                    System.out.println(i + " .. " + "None");
                else
                    System.out.println(i + " .. " + dist[i]);
            }
        }
    }
    // dodamo povezavo v array
    Edge[] appendEdge(Edge[] tab, Edge povezava){
        Edge[] new_tab = new Edge[tab.length+1];
        for (int i = 0; i < tab.length; i++) {
            new_tab[i] = tab[i];
        }
        new_tab[tab.length] = povezava;
        return new_tab;
    }
    int[] BellmanFord(int vertices, Edge[] edges, int src) {
        // 1. postavimo se vrednosti razen srouce na max value
        int[] dist = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        // 2. Izracunamo najblizje poti - ponovimo x (vertices-1)
        for (int i = 0; i < vertices-1; i++) {
            for (int j = 0; j < edges.length; j++) {
                int start = edges[j].src;
                int end = edges[j].dest;
                int cost = edges[j].cost;
                if(dist[start] != Integer.MAX_VALUE && dist[start] + cost < dist[end])
                    dist[end] = dist[start] + cost;
            }
        }
        // 3. negativni cikel
        for (int j = 0; j < edges.length; j++) {
            int start = edges[j].src;
            int end = edges[j].dest;
            int cost = edges[j].cost;
            if(dist[start] != Integer.MAX_VALUE && dist[start] + cost < dist[end])
                // after V-1 itterations value smaller then before
                // NEGATIVE CYCLE
                return null;
        }
        return dist;
    }
}

class LBR1 {
    AdjacencyList list;

    LBR1(int[][] cells) {
        AdjacencyList list_tmp = new AdjacencyList(cells.length * cells[0].length);
        list_tmp.convertMaze(cells);
        this.list = list_tmp;
    }
    void printPath(int from, int to) {
        int source = from - 1;
        int dest = to - 1;
        if(source < 0 || source > this.list.numVertices -1  ||
                dest < 0  || dest > this.list.numVertices - 1) {
            System.out.println("None");
            return;
        }
        int[][] res = Dijsktra(list, source);
        int[] dist = res[0];
        if(dist[dest] == Integer.MAX_VALUE)
            System.out.println("None"); // pot ne obstaja
        else {
            int[] path = getPath(res[1], source, dest);
            System.out.println("Length " + (path.length - 1) + ":");
            for (int i = path.length - 1; i >= 0; i--) {
                System.out.println(path[i] + 1);
            }
        }

    }
    int[] getPath(int[] prev, int source, int dest) {
        int[] tmp = new int[prev.length];
        tmp[0] = dest;
        int node = prev[dest];
        int c=1;
        while (node != source) {
            tmp[c] = node;
            node = prev[node];
            c++;
        }
        int[] path = new int[c+1];
        for (int i = 0; i < path.length-1; i++) {
            path[i] = tmp[i];
        }
        path[c] = source;
        return path;
    }

    int[][] Dijsktra(AdjacencyList graph, int source) {
        // 1. postavimo se vrednosti razen srouce na max value
        int[] dist = new int[graph.numVertices];
        for (int i = 0; i < graph.numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;
        int[] prev = new int[graph.numVertices];
        AdjacencyList.Vertex[] U = graph.vertices;
        int min_index, nova_cena;
        AdjacencyList.Vertex minV, povezava;
        pqueue pque = new pqueue();
        pque.insert(0, source, source);

        min_index = source;

        // 2. Gremo cez vse tocke
        while(!pque.empty()) {
            pqueue.Key min_edge = pque.popMin(); // index naslednje najcenejsa povezava

            // primerjamo s direktno ceno do j (dj)
            if (min_edge.cost < dist[min_edge.to]) {
                dist[min_edge.to] = min_edge.cost;
                prev[min_edge.to] = min_edge.from;
            }

            minV = U[min_edge.to]; // tocka najcenejse povezave

            // 3. gremo cez vse sosede - j
            povezava = minV.next;
            while (povezava != null) {
                // preverimo ce je iz U - nenastavljena (MAX_VALUE)
                if (dist[povezava.key] == Integer.MAX_VALUE) {
                    // dodamo povezavo v pque
                    nova_cena = dist[min_edge.to] + povezava.cost;
                    pque.insert(nova_cena, minV.key, povezava.key);
                }
                povezava = povezava.next;
            }

        }
        return new int[][]{dist, prev};
    }
    public static int getMinVertex(AdjacencyList.Vertex[]  vertices, int[] dist) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i] != null && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

}
class AdjacencyList1 {
    int numVertices;
    Vertex[] vertices;

    AdjacencyList1(int numVertices) {
        this.numVertices = numVertices;
        this.vertices = new Vertex[numVertices];
        for (int i = 0; i < numVertices; i++) {
            this.vertices[i] = new Vertex(i, null, 0);
        }
    }
    static class Vertex {
        int key;
        Vertex next;
        int cost;

        Vertex(int key, Vertex next, int cost) {
            this.key = key;
            this.next = next;
            this.cost = cost;
        }
    }
    void addEdge(int from, int to, int cost) {
        if(from >= 0 && to >= 0 && from < numVertices && to < numVertices){
            // new edge
            Vertex node = vertices[from];
            while(node.next != null) {
                node = node.next;
            }
            node.next = new Vertex(to, null, cost);
        }
    }

    void convertMaze(int cells[][]) {
        int y = cells.length;
        int x = cells[0].length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                int point = cells[i][j];
                int index;
                int pos = i*x+j;
                if(point == 0 ){
                    // levo
                    index = j-1;
                    if(index >= 0 && cells[i][index] == 0)
                        addEdge(pos, pos-1, 1);
                    // desno
                    index = j+1;
                    if(index < x && cells[i][index] == 0)
                        addEdge(pos, pos+1, 1);

                    // gor
                    index = i-1;
                    if(index >= 0 && cells[index][j] == 0)
                        addEdge(pos, pos-x, 1);
                    // dol
                    index = i+1;
                    if(index < y && cells[index][j] == 0)
                        addEdge(pos, pos+x, 1);
                }
                else
                    vertices[pos] = null;
            }
        }
    }
}

class pqueue {
    Key[] tab;
    int m;
    int z;
    int c;

    pqueue() {
        this.tab = new Key[2];
        this.m = 2;
        this.z = 1;
        this.c = 0;
    }

    class Key {
        int from;
        int to;
        int cost; // vrednsot cost
        Key(int cost, int source, int dest) {
            this.cost = cost;
            this.from = source;
            this.to = dest;
        }
    }

    void resize() {
        m = m*2;
        Key[] old_tab = tab;
        tab = new Key[m];
        for (int i = 0; i < old_tab.length; i++) {
            tab[i] = old_tab[i];
        }
    }

    void insert(int cost, int source, int dest) {
        if(z == m)
            resize();
        tab[z] = new Key(cost, source, dest);
        push();
        z++;
    }
    void push() {
        int index = z;
        while (index > 1) {
            c++;
            Key parent = tab[index / 2]; // parent i/2
            Key child = tab[index];
            if (child.cost < parent.cost) {
                tab[index] = parent;
                tab[index / 2] = child;
            }
            else
                break;
            index /= 2;
        }
    }
    Key popMin() {
        Key out;
        if(z == 1)
            return null;
        else {
            out = tab[1];
            z--;
            tab[1] = tab[z];
            tab[z] = null;
            swap();
        }
        return out;
    }
    void swap(){
        int index = 1;

        while (index < z-1) {
            Key parent = tab[index];
            Key Lchild = tab[index * 2];
            Key Rchild = tab[index * 2 + 1];

            if(Lchild == null && Rchild == null)
                break;
            if(Lchild == null) { // desni otrok
                c++;
                if (Rchild.cost < parent.cost) {
                    tab[index * 2 + 1] = parent;
                    tab[index] = Rchild;
                }
                index = index * 2 + 1;
            }
            else if(Rchild == null) { // levi otrok
                c++;
                if (Lchild.cost < parent.cost) {
                    tab[index * 2] = parent;
                    tab[index] = Lchild;
                }
                index = index * 2;
            }
            else{ // 2 otroka
                if(parent == tab[1]) { // check if root
                    c++;
                    if (Lchild.cost < Rchild.cost) { // levi manjsi (levi v root)
                        tab[index * 2] = parent;
                        tab[index] = Lchild;
                        index = index * 2;
                    }
                    else { // desni manjsi (desni v root)
                        tab[index * 2 + 1] = parent;
                        tab[index] = Rchild;
                        index = index * 2 + 1;
                    }
                }
                else { // ni root (notranje vozlisce)
                    c++;
                    if (Lchild.cost < Rchild.cost) { // levi otrok manjsi
                        c++;
                        if(Lchild.cost < parent.cost) { // ce je manjsi kot stars, ga zamenjamo
                            tab[index * 2] = parent;
                            tab[index] = Lchild;
                        }
                        index = index * 2;
                    } else { // desni otrok manjsi
                        c++;
                        if(Rchild.cost < parent.cost) { // ce je manjsi kot stars, ga zamenjamo
                            tab[index * 2 + 1] = parent;
                            tab[index] = Rchild;
                        }
                        index = index * 2 + 1;
                    }
                }
            }
        }
    }
    boolean empty() {
        return z < 2;
    }
    void printMin() {
        if(z == 1)
            System.out.println("MIN: none");
        else
            System.out.println("MIN: " + tab[1].cost);
    }
    void printComparisons() {
        System.out.println("COMPARISONS: " + this.c);
    }

    void printElements() {
        if(z == 1)
            System.out.println("empty");
        else {
            System.out.print(tab[1].cost);
            for (int i = 2; i < tab.length; i++) {
                if(tab[i] != null) {
                    System.out.print(", ");
                    System.out.print((tab[i].cost));
                }
            }
            System.out.println();
        }
    }
}

