import java.util.*;


class Edge {
    int to;
    int weight;


    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}


public class FloodGraphSystem {


    static int V = 8;


    static String[] name = {
            "Warehouse",
            "Village A",
            "Village B",
            "Village C",
            "Village D",
            "Village E",
            "Village F",
            "Village G"
    };


    public static void buildPath(int[] parent, int node, StringBuilder path) {
        if (node == -1) return;


        if (parent[node] != -1) {
            buildPath(parent, parent[node], path);
            path.append(" -> ");
        }


        path.append(node == 0 ? "Warehouse" : name[node]);
    }


    public static void dijkstra(List<List<Edge>> graph, int start) {


        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];


        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);


        dist[start] = 0;


        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));


        pq.add(new int[]{start, 0});


        while (!pq.isEmpty()) {


            int[] current = pq.poll();
            int node = current[0];


            if (visited[node]) continue;
            visited[node] = true;


            for (Edge edge : graph.get(node)) {


                if (dist[node] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[node] + edge.weight;
                    parent[edge.to] = node;
                    pq.add(new int[]{edge.to, dist[edge.to]});
                }
            }
        }


        // =========================
        // CLEAN OUTPUT (3 COLUMNS ONLY)
        // =========================


        System.out.println("\n===== SHORTEST PATH RESULT =====\n");


        System.out.printf("%-12s %-40s %-15s\n",
                "Village", "Shortest Path", "Time Taken (min)");


        System.out.println("-------------------------------------------------------------");


        for (int i = 0; i < V; i++) {


            StringBuilder path = new StringBuilder();
            buildPath(parent, i, path);


            System.out.printf("%-12s %-40s %-15d\n",
                    name[i],
                    path.toString(),
                    dist[i]);
        }


        System.out.println("\n");
    }


    public static void main(String[] args) {


        List<List<Edge>> graph = new ArrayList<>();


        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }


        // Warehouse connections
        graph.get(0).add(new Edge(1, 30));
        graph.get(1).add(new Edge(0, 30));


        graph.get(0).add(new Edge(2, 8));
        graph.get(2).add(new Edge(0, 8));


        graph.get(0).add(new Edge(3, 15));
        graph.get(3).add(new Edge(0, 15));


        graph.get(0).add(new Edge(4, 18));
        graph.get(4).add(new Edge(0, 18));


        graph.get(0).add(new Edge(5, 22));
        graph.get(5).add(new Edge(0, 22));


        graph.get(0).add(new Edge(6, 25));
        graph.get(6).add(new Edge(0, 25));


        graph.get(0).add(new Edge(7, 30));
        graph.get(7).add(new Edge(0, 30));


        // Village connections
        graph.get(1).add(new Edge(2, 6));
        graph.get(2).add(new Edge(1, 6));


        graph.get(1).add(new Edge(3, 9));
        graph.get(3).add(new Edge(1, 9));


        graph.get(2).add(new Edge(4, 7));
        graph.get(4).add(new Edge(2, 7));


        graph.get(2).add(new Edge(5, 11));
        graph.get(5).add(new Edge(2, 11));


        graph.get(3).add(new Edge(4, 5));
        graph.get(4).add(new Edge(3, 5));


        graph.get(3).add(new Edge(6, 13));
        graph.get(6).add(new Edge(3, 13));


        graph.get(3).add(new Edge(7, 20));
        graph.get(7).add(new Edge(3, 20));


        graph.get(4).add(new Edge(5, 8));
        graph.get(5).add(new Edge(4, 8));


        graph.get(5).add(new Edge(6, 9));
        graph.get(6).add(new Edge(5, 9));


        graph.get(6).add(new Edge(7, 10));
        graph.get(7).add(new Edge(6, 10));


        dijkstra(graph, 0);
    }
}
