import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int R;
    static int C;
    static int K;
    static int answer;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[R-1][0] = true;
        dfs(R-1, 0, 1);

        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt) {
        if (cnt > K) {
            return;
        }
        if (x == 0 && y == C - 1) {
            if (cnt == K) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (check(nx, ny) && map[nx][ny] == '.') {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, cnt + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    static boolean check(int nx, int ny) {
        if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
            return false;
        }
        return true;
    }
}
