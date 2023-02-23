import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Q10026 {
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int BLUE = 'B';
    static final int NO_COLOR = -1;
    
    static final boolean THREE_COLOR = true;
    static final boolean TWO_COLOR = false;
    
    static int N;
    static int[][] map;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<N; j++) {
                map[i][j] = input[j];
            }
        }
    
        sb.append(countColor(THREE_COLOR)).append(" ").append(countColor(TWO_COLOR));
    
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int countColor(boolean isThreeColor) {
        initVisited();
        // Blue -> RED
        if (isThreeColor) {
            return countColorForThreeColor();
        }
        return countColorForTwoColor();
    }
    
    private static int countColorForTwoColor() {
        int count = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j] && map[i][j] == BLUE) {
                    count++;
                    bfs(i, j, BLUE);
                    visited[i][j] = true;
                }
            }
        }
        count += countUnvisitedAreas();
        return count;
    }
    
    private static int countUnvisitedAreas() {
        int count = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    count++;
                    bfs(i, j, NO_COLOR);
                    visited[i][j] = true;
                }
            }
        }
        return count;
    }
    
    private static int countColorForThreeColor() {
        int count = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    count++;
                    bfs(i, j, map[i][j]);
                }
            }
        }
        return count;
    }
    
    private static void initVisited() {
        for (int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
    }
    
    public static void bfs(int x, int y, int color) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
    
        while (!q.isEmpty()) {
            Point now = q.poll();
    
            for (int[] d : DIR) {
                int nextX = now.x + d[0];
                int nextY = now.y + d[1];
                if (isInRange(nextX, nextY) && isPossible(nextX, nextY, color)) {
                    visited[nextX][nextY] = true;
                    q.add(new Point(nextX, nextY));
                }
            }
        }
    }
    
    public static boolean isPossible(int x, int y, int colorOption) {
        if (colorOption == NO_COLOR) {
            return !visited[x][y];
        } else {
            return !visited[x][y] && map[x][y] == colorOption;
        }
    }
    
    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
    
    static class Point {
        int x,y;
    
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
