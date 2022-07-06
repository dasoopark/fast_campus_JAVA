package YU_CHAP2_2_Sort;

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
P[0], P[1], ...., P[N-1]은 0부터 N-1까지(포함)의 수를 한 번씩 포함하고 있는 수열이다. 수열 P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B가 된다
. 적용하는 방법은 B[P[i]] = A[i]이다.
배열 A가 주어졌을 때, 수열 P를 적용한 결과가 비내림차순이 되는 수열을 찾는 프로그램을 작성하시오. 
비내림차순이란, 각각의 원소가 바로 앞에 있는 원소보다 크거나 같을 경우를 말한다. 
만약 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력한다.

입력
첫째 줄에 배열 A의 크기 N이 주어진다. 둘째 줄에는 배열 A의 원소가 0번부터 차례대로 주어진다. N은 50보다 작거나 같은 자연수이고, 배열의 원소는 1,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 비내림차순으로 만드는 수열 P를 출력한다.

예제 입력 1 복사
3
2 3 1
예제 출력 1 복사
1 2 0
예제 입력 2 복사
4
2 1 3 1
예제 출력 2 복사
2 0 3 1
예제 입력 3 복사
8
4 1 6 1 3 6 1 4
예제 출력 3 복사
4 0 6 1 3 7 2 5

 */
public class Suyeol_sort_1015 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class Elem implements Comparable<Elem> {

        public int num, idx;
        /**
         * @param idx A 배열의 idx 위치를 기억하는 변수
         * @param num A[idx]의 원래 값
         */

        @Override
        public int compareTo(Elem other) {
            return num - other.num;	
            // TODO
            // 정렬 조건에 맞게 정렬하기
            // 1. num 의 비내림차순
            // 2. num이 같으면 idx 오름차순
        }
    }

    static int N;
    static int[] P;
    static Elem[] B;

    static void input() {
        N = scan.nextInt();
        B = new Elem[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = new Elem();
         // TODO: Elem 의 정의에 맞게 B[i] 에 값을 넣어주기
            B[i].num = scan.nextInt();
            B[i].idx = i;
        }
    }

    static void pro() {
    	// TODO: B 배열 정렬하기

        // TODO: B 배열의 값을 이용해서 P 배열 채우기

        // TODO: P 배열 출력하기
        Arrays.sort(B);
        for (int i = 0; i < N; i++) {
            P[B[i].idx] = i;
        }
        for (int i = 0; i < N; i++) {
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
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
