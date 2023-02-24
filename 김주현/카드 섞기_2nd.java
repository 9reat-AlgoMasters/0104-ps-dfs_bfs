import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q21315_3 {
    static int N, K;
    static int[] arr;
    static int[] selected = new int[2];
    static int visited;
    static boolean findAns = false;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\solve\\tc\\";
        String fileName = "Q21315.txt";
        setInputFile(path, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        K = (int) (Math.log(N) / Math.log(2));
//        System.out.printf("K : %d\n", K);
        perm(0);
    }

    public static void perm(int depth) {
//        System.out.printf("[IN] depth: %d\n", depth);
        if (findAns) {
            return;
        }

        if (depth == 2) {
//            System.out.println("다음의 선택된 K에 대해 검사를 시작합니다..");
//            System.out.println(Arrays.toString(selected));
            if (isCorrectWay()) {
                System.out.printf("%d %d", selected[1], selected[0]);
                findAns = true;
            }
            return;
        }

        for (int i=1; i<=K; i++) {
            visited = setVisited(i);
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

        int debug = 1;
        for (int k : selected) {
//            System.out.printf("%d 번째 reverse 시작! --- k : %d\n", debug++, k);
            copiedArr = reverse(copiedArr, k);
        }

        for (int i=0; i<N; i++) {
            if (copiedArr[i] != i+1) {
                return false;
            }
        }
        return true;
    }

    private static int[] reverse(int[] copiedArr, int k) {
        // 1. 인덱스 리스트를 구한다.
        int[] indexList = getIndexList(k);
//        System.out.printf("인덱스 리스트 : %s\n", Arrays.toString(indexList));

        // 2. 배열을 재구성 한다.
        int[] newArr = new int[N];
        int firstIndex = 0;
        for (int i=1; i<indexList.length; i++) {
//            System.out.printf("first index : %d\n", firstIndex);
            System.arraycopy(copiedArr, indexList[i],
                    newArr, firstIndex,
                    indexList[i-1] - indexList[i]);
            firstIndex += indexList[i-1] - indexList[i];
        }
//        System.out.printf("reverse 완료 ---> %s\n", Arrays.toString(newArr));
        return newArr;
    }

    private static int[] getIndexList(int k) {
        int[] indexList = new int[k + 3];
        int index = 1<<k;
        indexList[0] = N;
        /*if (index == k) {
            index >>= 1;
        }*/
        for (int i=1; i<=k+2; i++) {
            indexList[i] = index;
            index >>= 1;
        }
        return indexList;
    }

    private static int[] copyArr() {
        int[] copiedArr = new int[N];
        System.arraycopy(arr, 0, copiedArr, 0, N);
        return copiedArr;
    }
}