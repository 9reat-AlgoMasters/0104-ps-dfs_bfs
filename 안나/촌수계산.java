import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int X;
	static int Y;
	static int M;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int result=-1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new boolean[N+1];
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			graph[n1].add(n2);
			graph[n2].add(n1);
		}
		visited[X] = true;
		dfs(0, X);
		System.out.println(result);
	
	}
	static void dfs(int cnt, int x) {
		if(x==Y) {
			result = cnt;
			return;
		}
		
		for (int i = 0; i < graph[x].size(); i++) {
			if(visited[graph[x].get(i)]) continue;
			visited[graph[x].get(i)] = true;
			dfs(cnt+1, graph[x].get(i));
			visited[graph[x].get(i)] = false;
		}
		
	}
}