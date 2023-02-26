import java.io.*;
import java.util.*;

public class Q1963 {
    static final int[][] digitsForPrime = {
            {1, 3, 5, 7, 9},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
    };
    static final int[] units = {1, 10, 100, 1000, 10000};
    static boolean[] isPrime = new boolean[10000];
    static final int IMPOSSIBLE = -1;
    static int from , to;
    static Set<Integer> usedPrimes = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        makePrimeTable();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            usedPrimes = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            int answer = from == to ? 0 : bfs();
            if (answer == IMPOSSIBLE) {
                sb.append("IMPOSSIBLE");
            } else {
                sb.append(answer);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void makePrimeTable() {
        initPrimeTable();
        isPrime[1] = false;
        for (int i=2; i*i<10000; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < 10000; j += i) {
                isPrime[j] = false;
            }
        }
    }

    private static void initPrimeTable() {
        Arrays.fill(isPrime, true);
    }

    private static int bfs() {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(from);
        usedPrimes.add(from);

        int dist = 0;
        int size;

        while (!q.isEmpty()) {
            dist++;
            size = q.size();

            for (int i=0; i<size; i++) {
                int now = q.poll();

                for (int digit = 0; digit < 4; digit++) {
                    int[] nextPrimes = findNextPrimes(now, digit); // visited 에 없는 걸로만 구성

                    for (int prime : nextPrimes) {
                        if (isImpossiblePrime(prime)) continue;
                        if (prime == to) {
                            return dist;
                        }
                        usedPrimes.add(prime);
                        q.add(prime);
                    }
                }
            }
        }
        return IMPOSSIBLE;
    }

    private static int[] findNextPrimes(int prime, int digit) {
        int len = digitsForPrime[digit].length;
        int[] nextPrimes = new int[len];
        System.arraycopy(digitsForPrime[digit], 0, nextPrimes, 0, len);
        int remain = prime % units[digit + 1];
        prime += -remain + (remain % units[digit]);
        for (int i=0; i<len; i++) {
            nextPrimes[i] *= (int)Math.pow(10, digit);
            nextPrimes[i] += prime;
        }
        return nextPrimes;
    }

    private static boolean isImpossiblePrime(int prime) {
        return !isPrime[prime] || usedPrimes.contains(prime);
    }
}
