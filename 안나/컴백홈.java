import java.io.*;
import java.util.*;

public class Main {
	static int R;
	static int C;
	static int K;
	static char[][]map;
	static int count=0;
	static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	static boolean [][]visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
//		print();
		visited[R-1][0] = true;
		dfs(1, R-1, 0);
		System.out.println(count);
	}
	static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}
	static void dfs(int cnt, int r, int c) {
		if(cnt>K) return;
		if(cnt == K ) {
			if(r==0 && c == C-1) {
				count++;
			}
			return;
		}
		for (int i = 0; i < dir.length; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			
			if(isRange(nr,nc) && !visited[nr][nc]) {
				if(map[nr][nc] != 'T') {
					visited[nr][nc] = true;
					dfs(cnt+1, nr, nc);
					visited[nr][nc] = false;
				}
			}
		}
		
	}
	static boolean isRange(int nr, int nc) {
		return nr>=0 && nc >=0 && nr < R && nc<C;
	}
}