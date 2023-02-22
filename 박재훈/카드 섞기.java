import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
  //원본(1,2,...N)    /   2번 섞은 결과
    static int[] original, result;
  //정답 K 담을 배열
    static int[] nums;
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        original = new int[N];
        result = new int[N];
        nums = new int[2];
      
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            original[i] = i+1;
            result[i] = Integer.parseInt(input[i]);
        }

      //k 1부터 2^k<N일 때까지 완전탐색
        for (int k = 1; 1<<k < N; k++) {
          //카드 배열이 탐색때마다 바뀌면 안돼서 temp배열에 복사 후 인자로 넘기기
            int[] temp = Arrays.copyOf(original,N);
            shuffle(0,k,temp);
        }
    }
    public static void shuffle(int cnt, int k, int[] card){
        if(cnt == 2){
          //두번 섞기 완료 후 결과 배열과 다르면 탈락, 같으면 정답 출력 후 끝
            for (int i = 0; i < N; i++) {
                if(card[i] != result[i]){
                    return;
                }
            }
            System.out.println(nums[0] +" "+ nums[1]);
            System.exit(0);
        }
        nums[cnt] = k;

      //2 ~ k+1단계에서 사용할 배열
        int[] stage2 = new int[1<<k];
      //현 카드 밑에서 2^k개 만큼 올리기 -> stage2 배열에 복사
        System.arraycopy(card, N-(1<<k), stage2, 0, 1<<k);
      //남은 카드들 뒤로 당겨놓기(1단계)
        System.arraycopy(card, 0, card, 1<<k, N-(1<<k));
        for (int i = k-1; i >= 0; i--) {
          // 2단계부턴 밑에서부터 반을 위로 올리고 그 중에 또 반 위로 올리고...반복
            int[] temp = new int[1<<i];
          
            System.arraycopy(stage2, 1<<i, temp, 0, 1<<i);
            System.arraycopy(stage2, 0, stage2, 1<<i, 1<<i);
            System.arraycopy(temp, 0, stage2, 0, 1<<i);
        }
      
      //2단계 연산 결과를 앞에다 복사
        System.arraycopy(stage2,0, card, 0, 1<<k);

        for (int i = 1; 1<<i < N; i++) {
          //첫번째 섞기 종료, 두번째 섞기의 K 찾기
            int[] temp = Arrays.copyOf(card,N);
            shuffle(cnt+1, i, temp);
        }
    }
}
