import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int KLimit;
    static List<Integer> cards;
    static int[] result;
    static Deque<Integer> queue;
    static int first;
    static int last;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            cards.add(i+1);
        }
        
        String[] temp = br.readLine().split(" ");
        result = new int[N];

        for (int i = 0; i < temp.length; i++) {
            result[i] = Integer.parseInt(temp[i]);
        }

        KLimit = (int)(Math.log(N)/Math.log(2));

        loop1: for (int i = 0; i <= KLimit; i++) {
            for (int j = 0; j <= KLimit; j++) {
                queue = new ArrayDeque<>(cards);
                shuffle(i);
                shuffle(j);

                boolean flag = true;

                for (int k = 0; k < N; k++) {
                    if(queue.pollFirst() != result[k]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    first = i;
                    last = j;
                    break loop1;
                }
            }
        }

        System.out.println(first + " " + last);
    }

    //ex) 2^4 -> 2^3 -> 2^2 ->2^1 -> 2^0 이거 다함
    static void shuffle(int cnt) {
        Deque<Integer> t = new ArrayDeque<>();
        int tryCount = 1 << cnt;
        // 배열 옮기기를 위한 값이동
        for (int i = 0; i < tryCount; i++) {
            t.addFirst(queue.pollLast());
        }
        // 바꾸기 메서드
        while (tryCount > 1) {
            tryCount /= 2;

            //바꾸기
            for (int i = 0; i < tryCount; i++) {
                t.addFirst(t.pollLast());
            }
            //원래 큐로 절반씩(why? 뒤쪽 절반은 다시 순서를 바꾸지 않기 때문에)
            for (int i = 0; i < tryCount; i++) {
                queue.addFirst(t.pollLast());
            }
        }
        queue.addFirst(t.poll());

    }
}
