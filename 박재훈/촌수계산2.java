import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main_2644 {
    static int N, target1, target2, m;
    static ArrayList<Integer>[] list;
    static int[] visited;
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
          //각 번호마다 연결된(1촌) 번호 저장 
            list[i] = new ArrayList<>();
        }
        visited = new int[N+1];
      
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
        bfs();
        System.out.println(-1);
    }
    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        //시작점(target1) 큐에 저장, 방문 안한 사람과 구별하기위해 1값 저장
        q.add(target1);
        visited[target1] = 1;
        while(!q.isEmpty()) {
        	int t = q.poll();
        	int size = list[t].size();
            for (int i = 0; i < size; i++) {
              //큐에 저장된 번호 하나 빼서 그 번호와 연결된 번호 탐색
                int next = list[t].get(i);
                if(visited[next] == 0) {
                  //아직 방문안했으면 큐에 넣고 target1과 얼마나 떨어져있는지 저장
                  q.add(next);
                  visited[next] = visited[t]+1;
                }
                if(next == target2) {
                  //target2 찾았으면 출력후 끝
                  //시작점 값을 1로 해놨으므로 촌수를 구하려면 1빼줌
                	System.out.println(visited[next]-1);
                	System.exit(0);
                }
            }
        }
    }
}
