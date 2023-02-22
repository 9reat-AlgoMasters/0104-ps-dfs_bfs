import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N, target1, target2, m;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
          //각 번호마다 연결된(1촌) 번호 저장 
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];
      
        String[] input = br.readLine().split(" ");
        target1 = Integer.parseInt(input[0]);
        target2 = Integer.parseInt(input[1]);
      
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int val1 = Integer.parseInt(input[0]);
            int val2 = Integer.parseInt(input[1]);
            list[val1].add(val2);
            list[val2].add(val1);
        }
      //target1에서부터 탐색 시작
        dfs(0, target1);
        System.out.println(-1);
    }
    static void dfs(int cnt, int start){
        if(start == target2){
          //target2를 찾았으면 cnt == 촌수 출력
            System.out.println(cnt);
            System.exit(0);
        }
        visited[start] = true;
        int size = list[start].size();
        for (int i = 0; i < size; i++) {
            int next = list[start].get(i);
            if(!visited[next]) {
              //탐색 중인 번호에 연결된 번호들 중 아직 안찾아본 번호 탐색
                dfs(cnt + 1, next);
            }
        }
    }
}
