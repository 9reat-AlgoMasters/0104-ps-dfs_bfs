import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] map;
    static char[][] map2;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        map2 = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            System.arraycopy(map[i], 0 , map2[i],0,N);
            for (int j = 0; j < N; j++) {
                if(map2[i][j] == 'G') {
                    map2[i][j] = 'R';
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(map[i][j], map, i, j);
                    cnt++;
                }
            }
        }
        
        visited = new boolean[N][N];
        int cnt2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(map2[i][j],map2, i, j);
                    cnt2++;
                }
            }
        }
        System.out.println(cnt + " " + cnt2);
    }

    static void bfs(char color, char[][] map,int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b});
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] tp = q.poll();
            int x = tp[0];
            int y = tp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!check(nx, ny)) {
                    continue;
                }

                if(!visited[nx][ny] && map[nx][ny] == color) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

    }

    static boolean check(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return false;
        }
        return true;
    }
}
