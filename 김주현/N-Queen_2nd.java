import java.io.*;

public class Q9663 {
    static final int FOUR_BITS_ON = 15;
    static int N;
    static long queens;
    static int visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        queens = 0;
        visited = 0;
        dfs(0, queens, visited);
        sb.append(count);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth, long queens, int visited) {
//        System.out.printf("[IN] depth : %d, queens : %d, visited : %d\n", depth, queens, visited);
//        System.out.printf("현재 퀸 상태 ----> %s\n", printQueens(queens, depth));
//        System.out.printf("현재 방문 상태 ----> %s\n", printVisited(visited));
        if (depth == N) {
            count++;
//            System.out.printf("[OUT - 찾았다!! cnt 추가] depth : %d, queens : %d, visited : %d\n", depth, queens, visited);
            return;
        }

        for (int row=0; row<N; row++) {
            if (isVisited(visited, row)) continue;
            if (hasOtherQueenInDiagonal(queens, depth, row)) continue;
//            System.out.printf("%d번째 퀸은 %d에 배치되었습니다.\n", depth+1, row);
            dfs(depth + 1, setQueen(queens, depth, row), setVisited(visited, row));
        }
//        System.out.printf("[OUT - for end] depth : %d, queens : %d, visited : %d\n", depth, queens, visited);
    }

    private static long setQueen(long queens, int index, int row) {
        return queens & ~((long) FOUR_BITS_ON << (index<<2)) | ((long) row << (index<<2));
    }

    private static int setVisited(int visited, int row) {
        return visited | (1 << row);
    }

    private static boolean isVisited(int visited, int row) {
        return (visited & (1<<row)) != 0;
    }

    private static boolean hasOtherQueenInDiagonal(long queens, int depth, int pos) {
        for (int i=0; i<depth; i++) {
            if (Math.abs(i - depth) == Math.abs(getQueenPosition(queens, i) - pos)) {
                return true;
            }
        }
        return false;
    }

    private static long getQueenPosition(long queens, int i) {
        return (queens >> (i<<2)) & FOUR_BITS_ON;
    }

    private static String printQueens(long queens, int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            if (i >= depth) break;
            sb.append(getQueenPosition(queens, i)).append(" ");
        }
        return sb.toString();
    }

    private static String printVisited(int visited) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            sb.append(isVisited(visited, i) ? 1 : 0).append(" ");
        }
        return sb.toString();
    }

}
