import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_1963 {
    static int testCase;
    static int f;
    static int l;
    static int answer = -1;
    static int[] first;
    static int[] last;
    static boolean[] prime;
    static boolean[] visited;
    static int[] d = {1000, 100, 10, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        prime = new boolean[10000];
        testCase = Integer.parseInt(br.readLine());
        prime();

        for (int i = 0; i < testCase; i++) {
            String[] temp = br.readLine().split(" ");
            f = Integer.parseInt(temp[0]);
            first = new int[4];
            int k = 0;
            for (char s : temp[0].toCharArray()) {
                first[k++] = s - '0';
            }

            l = Integer.parseInt(temp[1]);
            last = new int[4];
            k = 0;
            for (char s : temp[1].toCharArray()) {
                last[k++] = s - '0';
            }

            visited = new boolean[10000];

            bfs(0);
            if (answer == -1) {
                System.out.println("Impossible");
            }
            if (answer != -1) {
                System.out.println(answer);
            }

        }
    }

    static void bfs(int cnt){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{f, cnt});
        visited[f] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int num = temp[0];
            int count = temp[1];

            if (num == l) {
                answer = count;
                return;
            }

            int[] nums = toIntArr(num);

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 4; j++) {
                    int tVal = makeNum(j, nums, i);
                    //첫번째자리가 0인경우는 빼주도록하자
                    if (tVal > 1000) {
                        addPrime(q,tVal,count);
                    }
                }
            }
        }
    }

    static int[] toIntArr(int num) {
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            nums[i] = num/d[i];
            num %= d[i];
        }
        return nums;
    }

    static int makeNum(int pos, int[] nums, int i) {
        int num = 0;
        if (pos == 0) {
            num = i * d[0] + nums[1] * d[1] + nums[2] * d[2] + nums[3];
        }
        if (pos == 1) {
            num = nums[0] * d[0] + i * d[1] + nums[2] * d[2] + nums[3];
        }
        if (pos == 2) {
            num = nums[0] * d[0] + nums[1] * d[1] + i * d[2] + nums[3];
        }
        if (pos == 3) {
            num = nums[0] * d[0] + nums[1] * d[1] + nums[2] * d[2] + i;
        }
        return num;
    }

    static void addPrime(Queue<int[]> q, int tVal, int count) {
        if (prime[tVal] == false && !visited[tVal]) {
            q.add(new int[]{tVal, count + 1});
            visited[tVal] = true;
        }
    }

    static void prime() {
        for (int i = 2; i <= Math.sqrt(10000); i++) {
            if (prime[i] != true) {
                for (int j = i * i; j < 10000; j+=i) {
                    prime[j] = true;
                }
            }
        }
    }
}
