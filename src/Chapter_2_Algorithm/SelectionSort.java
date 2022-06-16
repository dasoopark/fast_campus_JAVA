package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Collections;

//선택 정렬 
/*
 	• 다음과 같은 순서를 반복하며 정렬하는 알고리즘
		1. 주어진 데이터 중, 최소값을 찾음
		2. 해당 최소값을 데이터 맨 앞에 위치한 값과 교체함
		3. 맨 앞의 위치를 뺀 나머지 데이터를 동일한 방법으로 반복함
 */

public class SelectionSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        int lowest; //가장 작은 값 저장할 변수
        for (int stand = 0; stand < dataList.size() - 1; stand++) {
            lowest = stand; //첫 부분은 가장 작은 값 이므로 
            for (int index = stand + 1; index < dataList.size(); index++) { //비교할 반복문
                if (dataList.get(lowest) > dataList.get(index)) {
                    lowest = index;
                }
            } 
            Collections.swap(dataList, lowest, stand);
        }
        return dataList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            testData.add((int)(Math.random() * 100));
        }
        SelectionSort sSort = new SelectionSort();
        System.out.println(sSort.sort(testData));
    }
}