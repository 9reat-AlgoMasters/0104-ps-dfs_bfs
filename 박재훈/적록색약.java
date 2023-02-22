import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main{
    static int N, ans1, ans2;

    static boolean[][] visited;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
      //색약 아닌 사람과 색약인 사람 그림 나누기
        char[][] map1 = new char[N][N];
        char[][] map2 = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map1[i][j] = input[j];
                if(input[j] == 'R'){
                  //색약인 사람이 보는 그림은 R이 G로 보임
                    map2[i][j] = 'G';
                }else {
                    map2[i][j] = input[j];
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                  
                    bfs(i, j, map1);
                    ans1++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i],false);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    bfs(i, j, map2);
                    ans2++;
                }
            }
        }

        System.out.println(ans1+" "+ans2);
    }

    static void bfs(int r, int c, char[][] map){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r,c});
        visited[r][c] = true;
        char val = map[r][c];
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N
                    && !visited[nr][nc] && map[nr][nc] == val){
                        queue.add(new int[]{nr,nc});
                        visited[nr][nc] = true;
                }
            }
        }
    }


}
