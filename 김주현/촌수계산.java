import java.io.*;
import java.util.*;

public class Q2644 {
    static int N, M;
    static int from, to;
    static Graph g;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        g = new Graph(N);
        visited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        while (M-- >0) {
            st = new StringTokenizer(br.readLine());
            g.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        sb.append(bfs());
    
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int bfs() {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(from);
        visited[from] = true;
        int dist = 0;
    
        while (!q.isEmpty()) {
            dist++;
            int size = q.size();
    
            for (int i = 0; i < size; i++) {
                int now = q.poll();
    
                for (int next : g.adjList[now]) {
                    if (!visited[next]) {
                        if (next == to) {
                            return dist;
                        }
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }
        return -1;
    }
    
    
    static class Graph {
        List<Integer>[] adjList;
        int size;
    
        public Graph(int size) {
            adjList = new ArrayList[size + 1];
            for (int i=1; i<=size; i++) {
                adjList[i] = new ArrayList<>();
            }
            this.size = size;
        }
        
        public void addEdge(int v1, int v2) {
            adjList[v1].add(v2);
            adjList[v2].add(v1);
        }
    }
}
