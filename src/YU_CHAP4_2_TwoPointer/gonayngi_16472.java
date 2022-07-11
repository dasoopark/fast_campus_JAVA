package YU_CHAP4_2_TwoPointer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 고냥이
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	512 MB	2226	897	659	39.087%
문제
고양이는 너무 귀엽다. 사람들은 고양이를 너무 귀여워했고, 결국 고양이와 더욱 가까워지고 싶어 고양이와의 소통을 위한 고양이 말 번역기를 발명하기로 했다.
 이 번역기는 사람의 언어를 고양이의 언어로, 고양이의 언어를 사람의 언어로 바꾸어 주는 희대의 발명품이 될 것이다.
현재 고양이말 번역기의 베타버전이 나왔다. 그러나 이 베타버전은 완전 엉망진창이다.
 베타버전의 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다. 
 굉장히 별로지만 그나마 이게 최선이라고 사람들은 생각했다. 그리고 문자열이 주어졌을 때 이 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인지가 궁금해졌다.

고양이와 소통할 수 있도록 우리도 함께 노력해보자.

입력
첫째 줄에는 인식할 수 있는 알파벳의 종류의 최대 개수 N이 입력된다. (1 < N ≤ 26)

둘째 줄에는 문자열이 주어진다. (1 ≤ 문자열의 길이 ≤ 100,000) 단, 문자열에는 알파벳 소문자만이 포함된다.

출력
첫째 줄에 번역기가 인식할 수 있는 문자열의 최대길이를 출력한다. 

예제 입력 1 
2
abbcaccba

예제 출력 1 
4
힌트
abbcaccba에서 답은 cacc가 된다. 번역기가 인식할 수 있는 알파벳의 종류는 최대 2개이고, 
알파벳의 종류를 최대 2개만 가지면서 가장 긴 연속된 문자열이 cacc이다. 따라서 답은 cacc의 길이인 4가 된다. 
 */
public class gonayngi_16472 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    
    static int N, kind; //kind => 알파벳 종류
    static String A;
    static int[] cnt;

    static void input() {
        N = scan.nextInt();
        A = scan.nextLine();
        cnt = new int[26];
    }

    static void add(char x) {  // x 라는 알파벳 추가d
        cnt[x - 'a']++; //아스키코드로 소문자 a를 빼야 해당 알파벳에 대한 값이 나옴 
        if (cnt[x - 'a'] == 1)  // 새롭게 나타난 알파벳이라는 뜻
            kind++; 
    }

          
    static void erase(char x) {  // x 라는 알파벳 제거
        cnt[x - 'a']--;
        if (cnt[x - 'a'] == 0)  // 인식해야 하는 알파벳에서 빠지는 순간;
            kind--;
    }
  
    static void pro() {
        int len = A	.length(), ans = 0;
        for (int R = 0, L = 0; R < len; R++) {
            // R 번째 문자를 오른쪽에 추가
            add(A.charAt(R));
            
            // 불가능하면, 가능할 때까지 L을 이동x
            while (kind > N) {
                erase(A.charAt(L++));
            }

            // 정답 갱신
            ans = Math.max(ans, R - L + 1); 
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
