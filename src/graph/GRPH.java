package Naloga5;

class GRPH {
    int vertices;
    Edge[] edges;

    GRPH(int verticesCount) {
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

class LBR {
    AdjacencyList list;

    LBR(int[][] cells) {
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

        min_index = source;

        // 2. Gremo cez vse tocke
        while(min_index != -1) {
            minV = U[min_index]; // najmanjsa tocka iz U
            if(minV == null) // zacetna tocka = 1 (ni prehoda)
                break;
            U[min_index] = null; // i je obiskana, postavimo na null

            // 3. gremo cez vse sosede - j
            povezava = minV.next;
            while(povezava != null) {
                // preverimo ce je iz U
                if(U[povezava.key] != null) {
                    // cena do i + cena povezave (di + cij)
                    nova_cena = dist[min_index] + povezava.cost;
                    // primerjamo z direktno ceno do j (dj)
                    if(nova_cena < dist[povezava.key]) {
                        dist[povezava.key] = nova_cena;
                        prev[povezava.key] = minV.key;
                    }
                }
                povezava = povezava.next;
            }
            // index naslednje najmanjse tocke iz U
            min_index = getMinVertex(U, dist);
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
class AdjacencyList {
    int numVertices;
    Vertex[] vertices;

    AdjacencyList(int numVertices) {
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
