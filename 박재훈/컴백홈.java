import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int R, C, K, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
      
        map = new char[R][C];
        visited = new boolean[R][C];
      
        for (int i = 0; i < R; i++) {
            char[] temp = br.readLine().toCharArray();
            System.arraycopy(temp, 0, map[i], 0, C);
        }
      
        //왼쪽 아래에서 출발. 시작점도 거리에 포함되므로 cnt값 1 설정
        dfs(1, R-1,0);
        System.out.println(ans);
    }
    static void dfs(int cnt, int r, int c){
        if(cnt > K){
          //이미 K거리 이상 갔으면 탈락
            return;
        }
        if(r == 0 && c == C-1){
          //집 도착, 거리 K면 가짓수++
            if(cnt == K) {
                ans++;
            }
            return;
        }
    
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < R && nc >= 0 && nc < C
                    && !visited[nr][nc] && map[nr][nc] == '.'){
              //아직 방문 안했고 장애물 아니면 dfs 탐색
                dfs(cnt + 1, nr, nc);
            }
        }
      //해당 점 탐색결과 원하는 K가 아니었다면
      //방문했다는 표시 없애고 이전 점으로 돌아갈 수 있게
        visited[r][c] = false;
    }
}
