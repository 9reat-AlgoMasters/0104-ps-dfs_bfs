import java.io.*;
import java.util.StringTokenizer;

public class Q1189 {
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int EMPTY = '.';
    static final int WALL = 'T';
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                map[i][j] = input[j];
            }
        }

        visited[N-1][0] = true;
        dfs(1, N-1, 0);
        sb.append(count);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void dfs(int cnt, int x, int y) {
        if (cnt == K) {
            if (x == 0 && y == M-1) {
                count++;
            }
            return;
        }

        for (int[] d : DIR) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isInRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == EMPTY) {
                visited[nextX][nextY] = true;
                dfs(cnt+1, nextX, nextY);
                visited[nextX][nextY] = false;
            }
        }
    }

    public static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
