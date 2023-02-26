import java.io.*;
import java.util.*;

public class Q1963 {
    static final int[][] digitsForPrime = {
            {1, 3, 5, 7, 9},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
    };
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
                
                List<Integer> nextPrimes = findNextPrimes(now); // visited 에 없는 걸로만 구성
                
                for (int prime : nextPrimes) {
                    if (prime == to) {
                        return dist;
                    }
                    usedPrimes.add(prime);
                    q.add(prime);
                }
            }
        }
        return IMPOSSIBLE;
    }
    
    private static List<Integer> findNextPrimes(int prime) {
        List<Integer> nextPrimes = new ArrayList<>();
        for (int digit = 0; digit < 4; digit++) {
            nextPrimes.addAll(findNthDigitChangedPrime(prime, digit));
        }
        return nextPrimes;
    }
    
    // digit -> 일의 자리부터 천의 자리 중 어떤 자리의 수를 바꾸어 볼 것인지 선택하는 파라미터
    private static List<Integer> findNthDigitChangedPrime(int prime, int digit) {
        List<Integer> nextPrimes = new ArrayList<>();
        int candidate = getFirstCandidate(prime, digit);
        
        if (isPossiblePrime(candidate)) {
            usedPrimes.add(candidate);
            nextPrimes.add(candidate);
        }
        
        int unit = digit == 0 ? 2 : 1;
        unit *= (int)Math.pow(10, digit);
        for (int i=1; i<digitsForPrime[digit].length; i++) {
            candidate += unit;
            if (isPossiblePrime(candidate)) {
                usedPrimes.add(candidate);
                nextPrimes.add(candidate);
            }
        }
        return nextPrimes;
    }
    
    // 3779 에서 십의 자리를 바꾸어보기로 했다면 3709를 반환 (각자리에서 가능한 첫번째 숫자를 해당 자리에 넣어서 반환)
    private static int getFirstCandidate(int prime, int digit) {
        int div = (int)Math.pow(10, digit+1);
        int remain = prime % div;
        prime -= remain;
        div /= 10;
        prime += (remain % div) + digitsForPrime[digit][0]*div;
        return prime;
    }
    
    private static boolean isPossiblePrime(int prime) {
        return isPrime[prime] && !usedPrimes.contains(prime);
    }
}
