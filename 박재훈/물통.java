import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main_2251 {
    static class Bottle implements Comparable<Bottle>{
      //세 병의 물 상태를 담은 클래스 
        int a;
        int b;
        int c;

        public Bottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Bottle o) {
          //TreeSet 정렬을 위함
          //A 기준 오름차순 정렬, 같으면 C 기준
            if(this.a == o.a){
                return Integer.compare(this.c, o.c);
            }
            return Integer.compare(this.a, o.a);
        }
    }
    static int A, B, C;
    static TreeSet<Bottle> set = new TreeSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        C = Integer.parseInt(input[2]);

      //초기 상태로 dfs
        Bottle bottle = new Bottle(0, 0, C);
        dfs(bottle);

        for (Bottle b : set) {
            if(b.a == 0){
                sb.append(b.c).append(" ");
            }
            else{
                break;
            }
        }
        System.out.println(sb);
    }
    static void dfs(Bottle b){
      //들어온 Bottle 클래스 높이 정보가 이미 봤던 조합이면 탈락
        if(set.contains(b)){
            return;
        }
        set.add(b);

        for (int i = 0; i < 3; i++) {
          //물이동
            water(b.a,b.b,b.c,i);
        }
    }

    static void water(int a, int b, int c, int src){
      //src에서 이동(0이면:A에서 1:B 2:C)
        int aVal = a, bVal = b, cVal = c;
        if(src == 0){
            //A 물 양 0 아니면 이동가능
            if(a != 0){
                // A->B
                if(a > B - b) {
                    aVal -= B - b;
                    bVal = B;
                }else{
                    bVal += aVal;
                    aVal = 0;
                }
                //이동 후 상태에서 파고 들어가기
                dfs(new Bottle(aVal, bVal, cVal));

                aVal = a;
                bVal = b;
                
                //A->C
                if(a > C - c) {
                    aVal -= C - c;
                    cVal = C;
                }else{
                    cVal += aVal;
                    aVal = 0;
                }
                dfs(new Bottle(aVal, bVal, cVal));
            }
        }else if(src == 1){
            if(b != 0){
                if(b > A - a) {
                    bVal -= A - a;
                    aVal = A;
                }else{
                    aVal += bVal;
                    bVal = 0;
                }
                dfs(new Bottle(aVal, bVal, cVal));

                aVal = a;
                bVal = b;
                if(b > C - c) {
                    bVal -= C - c;
                    cVal = C;
                }else{
                    cVal += bVal;
                    bVal = 0;
                }
                dfs(new Bottle(aVal, bVal, cVal));
            }
        }else{
            if(c != 0){
                if(c > A - a) {
                    cVal -= A - a;
                    aVal = A;
                }else{
                    aVal += cVal;
                    cVal = 0;
                }
                dfs(new Bottle(aVal, bVal, cVal));

                aVal = a;
                cVal = c;
                if(c > B - b) {
                    cVal -= B - b;
                    bVal = B;
                }else{
                    bVal += cVal;
                    cVal = 0;
                }
                dfs(new Bottle(aVal, bVal, cVal));
            }
        }
    }
}
