import java.util.Scanner;

public class Main {

	static int N, col[], ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N];
    
    //NxN 판에 N개 퀸->각 행마다 하나씩만 들어감
		//첫 행 퀸부터 어디 놓을지 탐색 시작
		solve(0);
		System.out.println(ans);
	}
	
	public static void solve(int cnt) {
		if(cnt == N) {
      // N개 놓았으면 가짓수++
			ans++;
			return;
		}
		for (int i = 0; i < N; i++) {
      //cnt번째 행의 퀸을 0~N-1 열에 배치
			col[cnt] = i;
			if(canPut(cnt)) {
        //놓을 수 있는지 확인 가능하면 다음 행 퀸 배치
				solve(cnt + 1);
			}
		}
	}

	public static boolean canPut(int row) {
		for (int i = 0; i < row; i++) {
      //방금 놓은 행의 퀸이 공격 받는지 확인
			if(col[i] == col[row] || 
					Math.abs(col[row]-col[i]) == row - i) {
				return false;
			}
		}
		return true;
	}

}
