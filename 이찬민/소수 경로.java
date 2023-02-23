import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int testCase;
    static int f;
    static int l;
    static int answer;
    static int[] first;
    static int[] last;
    static boolean[] prime;
    static boolean[] visited;

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
            System.out.println(answer);
        }
    }

    static void bfs(int cnt){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{first[0], first[1],first[2],first[3], cnt});
        visited[f] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int count = temp[4];
            if (toInt(new int[]{temp[0], temp[1], temp[2], temp[3]}) == l) {
                answer = count;
                return;
            }

            for (int i = 0; i < 10; i++) {
                
                // 중복 코드 메서도 바꾸자
                int tVal = toInt(new int[]{i, temp[1], temp[2], temp[3]});
                //첫번째자리가 0인경우는 빼주도록하자
                if (i !=0 && prime[tVal] == false && !visited[tVal]) {
                    q.add(new int[]{i, temp[1], temp[2], temp[3], count + 1});
                    visited[tVal] = true;
                }

                tVal = toInt(new int[]{temp[0], i, temp[2], temp[3]});
                if (prime[tVal] == false && !visited[tVal]){
                    q.add(new int[]{temp[0], i, temp[2], temp[3], count + 1});
                    visited[tVal] = true;
                }
                tVal = toInt(new int[]{temp[0], temp[1], i, temp[3]});
                if (prime[tVal] == false && !visited[tVal]) {
                    q.add(new int[]{temp[0], temp[1], i, temp[3], count + 1});
                    visited[tVal] = true;
                }

                tVal = toInt(new int[]{temp[0], temp[1], temp[2], i});
                if (prime[tVal] == false && !visited[tVal]) {
                    q.add(new int[]{temp[0], temp[1], temp[2], i, count + 1});
                    visited[tVal] = true;
                }

            }
        }
    }

    static int toInt(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]).append(arr[1]).append(arr[2]).append(arr[3]);
        return Integer.parseInt(sb.toString());
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
