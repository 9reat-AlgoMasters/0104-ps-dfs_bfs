import java.io.*;

public class Q9663 {
    static int N;
    static int[] queens;
    static boolean[] visited;
    static int count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        queens = new int[N];
        visited = new boolean[N];
        dfs(0);
        sb.append(count);
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void dfs(int depth) {
        if (depth == N) {
            count++;
            return;
        }
        
        for (int i=0; i<N; i++) {
            if (visited[i]) continue;
            if (hasOtherQueenInDiagonal(depth, i)) continue;
            visited[i] = true;
            queens[depth] = i;
            dfs(depth + 1);
            visited[i] = false;
        }
    }
    
    private static boolean hasOtherQueenInDiagonal(int depth, int pos) {
        for (int i=0; i<depth; i++) {
            if (Math.abs(i - depth) == Math.abs(queens[i] - pos)) {
                return true;
            }
        }
        return false;
    }
}
