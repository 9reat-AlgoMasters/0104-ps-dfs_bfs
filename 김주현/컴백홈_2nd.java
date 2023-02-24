import java.io.*;
import java.util.StringTokenizer;

public class Q1189_2 {
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int EMPTY = '.';
    static int N, M, K;
    static int[][] map;
    static int visited;
    static int count = 0;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }

    public static void main(String[] args) throws IOException {
        String remainPath = "\\solve\\tc\\";
        String fileName = "Q1189.txt";
        setInputFile(remainPath, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = 0;
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                map[i][j] = input[j];
            }
        }

        visited = setVisited(visited, N-1, 0);
        dfs(1, N-1, 0, visited);
        sb.append(count);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void dfs(int cnt, int x, int y, int visited) {
        if (cnt == K) {
            if (x == 0 && y == M-1) {
                count++;
            }
            return;
        }

        for (int[] d : DIR) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isInRange(nextX, nextY) && isNotVisited(visited, nextX, nextY) && map[nextX][nextY] == EMPTY) {
                dfs(cnt + 1, nextX, nextY, setVisited(visited, nextX, nextY));
            }
        }
    }
    private static int setVisited(int visited, int x, int y) {
        return visited | (1<<(findLinearIndex(x, y)));
    }

    private static boolean isNotVisited(int visited, int x, int y) {
        return (visited & (1<<findLinearIndex(x, y))) == 0;
    }

    private static int findLinearIndex(int x, int y) {
        return x*M + y;
    }

    public static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
