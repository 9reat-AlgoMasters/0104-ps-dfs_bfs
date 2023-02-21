import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q21315 {
    static int N, K;
    static int[] arr;
    static int[] selected = new int[2];
    static int visited;
    static boolean findAns = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        K = (int) (Math.log(N) / Math.log(2));
        perm(0);
    }

    public static void perm(int depth) {
        if (findAns) {
            return;
        }

        if (depth == 2) {
            if (isCorrectWay()) {
                System.out.printf("%d %d", selected[1], selected[0]);
                findAns = true;
            }
            return;
        }

        for (int i=1; i<=K; i++) {
            selected[depth] = i;
            perm(depth+1);
            visited = setUnvisited(i);
        }
    }

    private static int setUnvisited(int i) {
        return visited & ~(1 << i);
    }

    private static int setVisited(int i) {
        return visited | (1<<i);
    }

    private static boolean isVisited(int i) {
        return (visited & (1<<i)) > 0;
    }

    private static boolean isCorrectWay() {
        int[] copiedArr = copyArr();
        for (int k : selected) {
            reverse(copiedArr, k);

        }

        for (int i=0; i<N; i++) {
            if (copiedArr[i] != i+1) {
                return false;
            }
        }
        return true;
    }

    private static void reverse(int[] copiedArr, int k) {

        for (int i=1; i<=k; i++) {
            secondSwapReverse(copiedArr, i);
        }
        firstSwapReverse(copiedArr, k);
    }

    private static void firstSwapReverse(int[] copiedArr, int k) {
        int[] temp = new int[N];
        int len = 1<<k;
        System.arraycopy(copiedArr, 0, temp, N-len, len);
        System.arraycopy(copiedArr, len, temp, 0, N-len);
        System.arraycopy(temp, 0, copiedArr, 0, N);
    }

    private static void secondSwapReverse(int[] copiedArr, int k) {
        int space = (1<<k-1);
        for (int i=0; i<space; i++) {
            swap(copiedArr, i, i+space);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] copyArr() {
        int[] copiedArr = new int[N];
        System.arraycopy(arr, 0, copiedArr, 0, N);
        return copiedArr;
    }
}
