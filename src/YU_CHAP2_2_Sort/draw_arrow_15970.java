package YU_CHAP2_2_Sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 문제 onenote 참고
 
 */
public class draw_arrow_15970 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] a;

    static void input() {
        N = scan.nextInt();
        a = new ArrayList[N + 1];
        for (int color = 1; color <= N; color++) {
            a[color] = new ArrayList<Integer>();
        }
        for (int i = 1; i <= N; i++) {
            int coord, color;
            coord = scan.nextInt();
            color = scan.nextInt();
            a[color].add(coord);
        }
    }

    static int toLeft(int color, int idx) {
        if (idx == 0)  // 왼쪽에 더 이상 점이 없는 상태	
            return Integer.MAX_VALUE;
        return a[color].get(idx) - a[color].get(idx - 1);
        // TODO
        // 색깔이 color 인 점의 idx 번째에 있는 점이 왼쪽으로 화살표를 그린다면
        // 화살표의 길이를 return 하는 함수. 왼쪽에 점이 없다면 무한대를 return.
    }

    static int toRight(int color, int idx) {
        if (idx + 1 == a[color].size())  // 오른쪽에 더 이상 점이 없는 상태
            return Integer.MAX_VALUE;
        return a[color].get(idx + 1) - a[color].get(idx);
        // TODO
        // 색깔이 color 인 점의 idx 번째에 있는 점이 오른쪽으로 화살표를 그린다면
        // 화살표의 길이를 return 하는 함수. 오른쪽에 점이 없다면 무한대를 return.
    }

    static void pro() {
    	// TODO: 색깔별로 정렬하기
    	
        for (int color = 1; color <= N; color++)
            Collections.sort(a[color]);
        // TODO: 색깔 별로, 각 점마다 가장 가까운 점 찾아주기
        int ans = 0;
        for (int color = 1; color <= N; color++) { //색깔 마다 순회 
            for (int i = 0; i < a[color].size(); i++) { //각 색깔의 점마다 순회
                int left_distance = toLeft(color, i);
                int right_distance = toRight(color, i);
                ans += Math.min(left_distance, right_distance);
                // 정답 출력하기
            }
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