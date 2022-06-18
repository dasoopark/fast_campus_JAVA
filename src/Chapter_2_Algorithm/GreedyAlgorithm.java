package Chapter_2_Algorithm;

import java.util.Arrays;
import java.util.Comparator;

/*
 1. 탐욕 알고리즘 이란?
	• Greedy algorithm 또는 탐욕 알고리즘 이라고 불리움
	• 최적의 해에 가까운 값을 구하기 위해 사용됨
	• 여러 경우 중 하나를 결정해야할 때마다, 매순간 최적이라고 생각되는 경우를 선택하는 방식으로 진행해서, 최종적인 값을 구하는 방식
 */

/*
 * 
 문제2: 부분 배낭 문제 (Fractional Knapsack Problem) (프로젝트: CH27_KNAPSACK)
	• 무게 제한이 k인 배낭에 최대 가치를 가지도록 물건을 넣는 문제
		○ 각 물건은 무게(w)와 가치(v)로 표현될 수 있음
		○ 물건은 쪼갤 수 있으므로 물건의 일부분이 배낭에 넣어질 수 있음, 그래서 Fractional Knapsack Problem 으로 부름
			§ Fractional Knapsack Problem 의 반대로 물건을 쪼개서 넣을 수 없는 배낭 문제도 존재함 (0/1 Knapsack Problem 으로 부름)

 */

public class GreedyAlgorithm {
    public void knapsackFunc(int[][] objectList, double capacity) {
        double totalValue = 0.0;
        double fraction = 0.0; //해당 물건이 몇프로 들어갔는지 나타내는 변수

        // 정렬하기
        Arrays.sort(objectList, new Comparator<int[]>() {
            @Override
            public int compare(int[] objectItem1, int[] objectItem2) {
                return (objectItem2[1] / objectItem2[0]) - (objectItem1[1] / objectItem1[0]);	 //내림차순
                			//무게를 가치로 나눠줘야함
                // 2차원 배열을 1차원 배열로 받으면, 2차원 배열의 첫번째 항목을 불러와서  인덱스{0,1}을 따짐
            }
        });

        for (int index = 0; index < objectList.length; index++) {
            if ( (capacity - (double)objectList[index][0]) >= 0 ) { //수용량이 물건의 무게보다 크거나 같으면 다 들어갈 수 잇다는 뜻.
                capacity -= (double)objectList[index][0];	//수용량의 무게에서 물건의 무게를 빼줌
                totalValue += (double)objectList[index][1];	//가치 더해줌
                System.out.println(objectList[index][0] + ", " + objectList[index][1] + ": " + 1);
            } else {//하나의 물건이 가방에 들어가는데 넘칠때 => 쪼개줘야함
                fraction = capacity / (double)objectList[index][0];
                totalValue += (double)objectList[index][1] * fraction;
                System.out.println(objectList[index][0] + ", " + objectList[index][1] + ": " + fraction);
                break;
            }
        }
        System.out.println("총 담을 수 있는 가치: " + totalValue);
    }

    public static void main(String[] args) {
        GreedyAlgorithm gObject = new GreedyAlgorithm();
        int[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };
        gObject.knapsackFunc(objectList, 30.0);
    }
}