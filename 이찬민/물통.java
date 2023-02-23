import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A;
    static int B;
    static int C;
    static HashSet<Integer> ans;
    static Set<List<Integer>> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        A = Integer.parseInt(temp[0]);
        B = Integer.parseInt(temp[1]);
        C = Integer.parseInt(temp[2]);
        ans = new HashSet<>();

        visited = new HashSet<>();
        bfs(0, 0, C);

        ArrayList<Integer> answer = new ArrayList<>(ans);
        Collections.sort(answer);
        StringBuilder sb  = new StringBuilder();
        for (int a : answer) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs(int a, int b, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a,b,c});

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            // 차있는 물의 양
            int nA = temp[0];
            int nB = temp[1];
            int nC = temp[2];
            // 비어있는 양
            int diffA = A - nA;
            int diffB = B - nB;
            int diffC = C - nC;

            if (nA == 0) {
                ans.add(nC);
            }


            if(visited.contains(Arrays.asList(nA, nB, nC))) {
                continue;
            }

            visited.add(Arrays.asList(nA, nB, nC));

            if (nA != 0) {
                // A에서 B로
                if (nA > diffB) {
                    q.add(new int[]{nA - diffB, B, nC});

                } else {
                    q.add(new int[]{0, nB + nA, nC});

                }

                // A에서 C로
                if (nA > diffC) {
                    q.add(new int[]{nA - diffC, nB, C});

                } else {
                    q.add(new int[]{0, nB, nC + nA});

                }
            }


            if (nB != 0) {
                // B에서 A로
                if (nB > diffA) {
                    q.add(new int[]{A, nB - diffA, nC});

                } else {
                    q.add(new int[]{nA + nB, 0, nC});

                }

                //B에서 C로
                if (nB > diffC) {
                    q.add(new int[]{nA, nB - diffC, C});

                } else {
                    q.add(new int[]{nA, 0, nC + nB});

                }
            }

            if (nC != 0) {
                // C에서 A로
                if (nC > diffA) {
                    q.add(new int[]{A, nB, nC - diffA});

                } else {
                    q.add(new int[]{nA + nC, nB, 0});

                }

                //C에서 B로
                if (nC > diffB) {
                    q.add(new int[]{nA, B, nC - diffB});

                } else {
                    q.add(new int[]{nA, nB + nC, 0});

                }
            }


            cnt++;



        }
    }
}
