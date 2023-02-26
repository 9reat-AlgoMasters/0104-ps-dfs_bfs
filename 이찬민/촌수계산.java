import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int a;
    static int b;
    static int m;
    static int answer;
    static boolean[] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        a = Integer.parseInt(temp[0]);
        b = Integer.parseInt(temp[1]);
        m = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            map[x][y] = 1;
            map[y][x] = 1;
        }

        visited[a] = true;
        dfs(a, 0);

        System.out.println(answer!=0? answer:-1);
    }

    static void dfs(int s, int cnt) {
        if(s == b) {
            answer = cnt;
            return;
        }

        for (int i = 1; i < map.length; i++) {
            int t = map[s][i];
            if(t == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i, cnt+1);
                visited[i] = false;
            }
        }
    }
}
