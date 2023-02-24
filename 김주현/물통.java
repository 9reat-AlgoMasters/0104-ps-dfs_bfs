import java.io.*;
import java.util.*;

public class Q2251 {
    static final int EMPTY = 0;
    static final int A = 0;
    static final int B = 1;
    static final int C = 2;
    static final int[][] DIR = {{A, B}, {B, A}, {A, C}, {C, A}, {B, C}, {C, B}};
    static final int FROM = 0;
    static final int TO = 1;
    static int[] buckets;
    static int total = 0;
    static Set<Integer> visitedStatus = new HashSet<>();
    static Set<Integer> answers = new HashSet<>();
    static int filter = (1<<8)-1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        buckets = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<3; i++) {
            buckets[i] = Integer.parseInt(st.nextToken());
        }
        total = buckets[C];

        bfs();
        List<Integer> answerList = new ArrayList<>(answers);
        Collections.sort(answerList);
        for (int answer : answerList) {
            sb.append(answer).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        visitedStatus.add(0);
        answers.add(buckets[C]);

        int curStatus; // A, B 물의 양 (하나당 8bit)
        int nextStatus;

        while(!q.isEmpty()) {
            curStatus = q.poll();

            for (int[] d : DIR) {
                nextStatus = moveWater(curStatus, d[FROM] , d[TO]);
                if (!visitedStatus.contains(nextStatus)) {
                    q.add(nextStatus);
                    visitedStatus.add(nextStatus);
                    if (getWater(nextStatus, A) == EMPTY) {
                        answers.add(getWater(nextStatus, C));
                    }
                }
            }
        }
    }

    private static int moveWater(int status, int from, int to) {
        // 1. from 의 물의 양
        // 2. to의 물의 양
        // 3. 물의 이동량 -> Math( from 물의 양 or to 에 더 넣을 수 있는 물의 양 )
        // 4. from -= 물의 이동량 -> set
        // 5. to += 물의 이동량 -> set
        int curBucketFrom = getWater(status, from);
        int curBucketTo = getWater(status, to);
        int waterMoved = Math.min(curBucketFrom, buckets[to] - curBucketTo);
        status = setWater(status, from, curBucketFrom-waterMoved);
        status = setWater(status, to, curBucketTo+waterMoved);
        return status;
    }

    private static int getWater(int status, int bucket) {
        if (bucket == C) {
            return total - getElement(status, A) - getElement(status, B);
        }
        return getElement(status, bucket);
    }

    private static int getElement(int status, int index) {
        return (status >> (index<<3)) & filter;
    }

    private static int setWater(int status, int index, int water) {
        if (index == C) return status;
        return (status & ~(filter << (index<<3))) | (water << (index<<3));
    }

    private static void printWater(int status) {
        for (int i=0; i<3; i++) {
            System.out.printf("%d ", getWater(status, i));
        }
        System.out.println();
    }
}
