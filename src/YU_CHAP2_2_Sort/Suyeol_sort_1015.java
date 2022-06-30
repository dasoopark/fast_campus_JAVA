package YU_CHAP2_2_Sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
