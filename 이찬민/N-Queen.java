import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int answer;
    static int[][] map;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        visited = new boolean[N]; // 같은 열에 퀸이 있는지 확인해주는 방문확인 배열

        recurSearch(0, 0);
        
        System.out.println(answer);
    }
    
    static void recurSearch(int idx,int cnt) {
        if (cnt == N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            // 자신 다음의 퀸을 놓을땐 앞쪽 방향을 볼핑요가 없다
            // 즉 같은 열인지와 ↖ 방향 확인, ↗ 방향 확인 이 3가지만 수행하면 된다.
            // 확인후 가능성이 없다면 들어가지 않도록함
            if (!visited[i] && check(idx, i)) {
                map[idx][i] = 1;
                visited[i] = true;
                recurSearch(idx + 1, cnt +1);
                visited[i] = false;
                map[idx][i] = 0;
            }
        }
    }

    //  ↖ 방향 확인, ↗ 방향 확인 해주는 메서드
    static boolean check(int x, int y) {
        int nx = x;
        int ny = y;
        // ↖ 방향 확인
        while (nx >= 0 && ny >= 0) {
            if (map[nx][ny] == 1) {
                return false;
            }
            nx--;
            ny--;
        }

        nx = x;
        ny = y;
        // ↗ 방향 확인
        while (nx >= 0 && ny < N) {
            if (map[nx][ny] == 1) {
                return false;
            }
            nx--;
            ny++;
        }

        return true;
    }
}
