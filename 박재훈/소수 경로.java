import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int T, ans;
	static String start, end;
	static boolean[] nonPrime = new boolean[10000];
	static int[] visited = new int[10000];
	public static void main(String[] args) throws IOException {
    
		for (int i = 2; i < 10000; i++) {
      //에라토스테네스의 체 - 소수면 false
			if(!nonPrime[i])
			for (int j = i*i; j < 10000; j = j+i) {
				nonPrime[j] = true;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			ans = 0;
      // start에서 몇번 변경해야 해당 숫자를 만들 수 있는지 저장할 배열
      // 초기에는 -1 -> 방문 안했음 의미
			Arrays.fill(visited, -1);
			String[] input = br.readLine().split(" ");
			start = input[0];
			end = input[1];
			
			bfs();
			System.out.println(ans);
		}
		
	}
	public static void bfs() {
		Queue<String> q = new ArrayDeque<String>();
		q.add(start);
    //시작점은 0번만에 도달 가능
		visited[Integer.parseInt(start)] = 0;
		if(start.equals(end)) {
			return;
		}
	
		while(!q.isEmpty()) {
			String str = q.poll();
			int valStr = Integer.parseInt(str);
			for (int i = 0; i < 4; i++) {
        //큐에 저장된 문자열 하나 뽑아서 각 자리수를 바꿔보기
				char[] temp = str.toCharArray();
				for (int j = 0; j < 10; j++) {
          //0~9까지 넣기, 맨 첫자리는 0 못 옴, 같은 숫자는 넘기기
					if((i == 0 && j == 0 )||temp[i] == (char)('0'+j)) {
						continue;
					} 
          //i번째 자리를 j로 바꾼 새문자열 nstr생성
					temp[i] = (char)('0'+j);
					String nstr = String.valueOf(temp);
					int valNstr = Integer.parseInt(nstr);
					if(nonPrime[valNstr]) {
            //소수 아니면 탈락
						continue;
					}
					if(visited[valNstr] == -1) {
            //아직 탐색 안했던 문자열이면 큐에 넣고 이전 변경전 문자열의 배열값(start에서 얼마나 걸리나) + 1
						visited[valNstr] = visited[valStr]+1;
						q.add(nstr);
					}
					if(nstr.equals(end)) {
            //찾았으면 배열값을 출력하도록 하고 종료
						ans = visited[valNstr];
						return;
					}
				}
			}
		}
		
	}

}
