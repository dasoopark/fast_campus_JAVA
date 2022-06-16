package Chapter_2_Algorithm;

/*
 1. 재귀 용법 (recursive call, 재귀 호출)
	• 함수 안에서 동일한 함수를 호출하는 형태
	여러 알고리즘 작성시 사용되므로, 익숙해져야 함

 */
public class Recursive {
	
	public int factorialFunc(int n) {
		if(n>1) {
			return  n*this.factorialFunc(n-1);
		}else {
			return 1;
		}
	} 
	
	  /*
     * 연습해보기3
	- 정수 4를 1, 2, 3의 조합으로 나타내는 방법은 다음과 같이 총 7가지가 있음 - 정수 n이 입력으로 주어졌을 때, n을 1, 2, 3의 합으로 나타낼 수 있는 방법의 수를 구하시오
		1+1+1+1
		1+1+2
		1+2+1
		2+1+1
		2+2
		1+3
		3+1
		힌트: 정수 n을 만들 수 있는 경우의 수를 리턴하는 함수를 f(n) 이라고 하면,
		f(n)은 f(n-1) + f(n-2) + f(n-3) 과 동일하다는 패턴 찾기
	   */
	
	public int factorialFunc2(int data) {
		if(data == 1) {
			return 1;
		}else if(data == 2) {
			return 2;
		}else if(data == 3) { //111 21 12 3 4가지
			return 4;
		}
		return this.factorialFunc2(data-1)+this.factorialFunc2(data-2)+this.factorialFunc(data-3);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recursive factorial = new Recursive();
		System.out.println(factorial.factorialFunc(5));
		System.out.println(factorial.factorialFunc2(6));
		

	}

}
