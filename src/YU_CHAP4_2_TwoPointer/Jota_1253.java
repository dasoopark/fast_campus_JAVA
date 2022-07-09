package YU_CHAP4_2_TwoPointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 문제
N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.
N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.
수의 위치가 다르면 값이 같아도 다른 수이다.

입력
첫째 줄에는 수의 개수 N(1 ≤ N ≤ 2,000), 두 번째 줄에는 i번째 수를 나타내는 Ai가 N개 주어진다. (|Ai| ≤ 1,000,000,000, Ai는 정수)

출력
좋은 수의 개수를 첫 번째 줄에 출력한다.

예제 입력 1 복사
10
1 2 3 4 5 6 7 8 9 10

예제 출력 1 복사
8

힌트
3,4,5,6,7,8,9,10은 좋다.

 */
public class Jota_1253 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    // target_idx 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
    static boolean func(int target_idx) {
        int L = 1, R = N;
        int target = A[target_idx];
        while (L < R) { 
            if (L == target_idx) L++; //쓰면 안되는 숫자니까 바로 넘겨줘야 함 
            else if (R == target_idx) R--;
            else {
                if (A[L] + A[R] > target) R--; //가장 큰게 오른쪽이니까. 
                else if (A[L] + A[R] == target) return true;
                else L++;
            }
        }
        return false;
    }

    static void pro() {
        // 최소, 최대를 빠르게 알기 위한 정렬
        Arrays.sort(A, 1, N + 1);
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            // i 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
            if (func(i)) ans++;
        }
        System.out.println(ans);
    }
    
     
    public static void main(String[] args) {
        input();
        pro();
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
       
        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
