import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int num1;
    static int num2;
    static int cnt;
    static boolean[] visited;
    static Queue<Integer> q ;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            cnt=0;
            result = -1;
            visited = new boolean[10001];
            q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            visited[num1] = true;
            q.offer(num1);
            bfs();
            if(result ==-1) {
                System.out.println("놉");
            }else {
                System.out.println(result);
            }
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            cnt++;
//			System.out.printf("%d 번 변환 \n", cnt-1);
            int size = q.size();
//			System.out.println(size);
            for (int s = 0; s < size ; s++) {
                int num = q.poll();
                if(num == num2) {
                    result = cnt-1;
                    return;
                }
                int[] n = new int[4];
                for (int i = 0; i < 4; i++) {
                    n[3 - i] = num % 10;
                    num /= 10;
                }
                for (int i = 0; i < 4; i++) {
                    int temp = n[i];
                    for (int j = 0; j < 10; j++) {
                        n[i] = j;
                        int a = arrayToint(n);
                        if (isRange(a) && isPrime(a)) {
                            if (visited[a])
                                continue;
                            visited[a] = true;
                            q.add(a);
                        }
                    }
                    n[i] =temp;
                }
            }
        }

    }

    static boolean isRange(int a) {
        return a>999;
    }

    /*
     * 안나가 생각한 아이디어 4자리 수 중에서 천의자리부터 차례대로 0~9까지 변환해보고 소수이면 재귀 이때 받아오는 숫자가 1000보다 작으면
     * 정지 만일 쭉쭉 진행해서 십의자리에서 숫자가 변환되었고 확인했을 때 소수이면 다시 그 소수로 천의자리부터 일의자리까지 0~9까지 변환해보고
     * 소수이면 재귀한다.
     *
     * 이때 발생하는 문제는 만약 1733 에서 천의 자리를 3으로 변환하였을때 3733이면 소수여서 재귀하게 된다. 그리고 3733에서 다시
     * 천의자리를 0~9까지 반복할때 1733은 소수여서 다시 재귀하게 된다. 이처럼 무한 루프에 빠질 수 있다. 이러한 문제를 겪고 답을
     * 봐야겠다고 결심...
     *
     *
     */
    // static void dfs(int cnt, int num) {
    // if (cnt >= min_count)
    // return;
    // if (num < 1000)
    // return;
    // if (num == num2) {
    // min_count = Math.min(min_count, num);
    // return;
    // }
    // int[] n = new int[4];
    // for (int i = 0; i < 4; i++) {
    // n[3-i] = num % 10;
    // num /=10;
    // }
    //
    // for (int i = 0; i < 4; i++) {
    // for (int j = 0; j < 10; j++) {
    // n[i] = j;
    // int a = arrayToint(n);
    // if (isPrime(a)) {
    // if (visited[a])
    // break;
    // visited[a] = true;
    // dfs(cnt + 1, a);
    // }
    // }
    // }
    //
    // }
    /*
     * 찾아본 답에서 제시하는 아이디어 찾아본 아이디어에서는 BFS를 제시하였다. 천의자리부터 0~9까지 변환해가며 소수를 판별하는 것까지는 동일
     * 하였다. 소수이면 재귀가 아닌 큐에 push.
     *
     * 그리고 위와 같은 문제를 만들지 않기 위해 이미 push한 숫자는 더이상 들어가지 않도록 visited 배열로 관리하였다. 그리고 큐가
     * 빌때까지 num1이 num2와 같아지지 않으면 불가능 하다고 출력.
     *
     */

    static int arrayToint(int[] n) {
        int a = 0;
        for (int i = 0; i < n.length; i++) {
            a *= 10;
            a += n[i];
        }
        return a;
    }

    static boolean isPrime(int a) {
        for (int i = 2; i <= (int) Math.sqrt(a); i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

}