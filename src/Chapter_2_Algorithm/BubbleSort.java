package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Collections;

/*
 1. 삽입 정렬 (insertion sort) 란?
	• 삽입 정렬은 두 번째 인덱스부터 시작
	• 해당 인덱스(key 값) 앞에 있는 데이터(B)부터 비교해서 key 값이 더 작으면, B값을 뒤 인덱스로 복사
이를 key 값이 더 큰 데이터를 만날때까지 반복, 그리고 큰 데이터를 만난 위치 바로 뒤에 key 값을 이동

 */
public class BubbleSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        for (int index = 0; index < dataList.size() - 1; index++) {  
            boolean swap = false;

            for (int index2 = 0; index2 < dataList.size() - index - 1; index2++) {
                if (dataList.get(index2) > dataList.get(index2 + 1)) {
                    Collections.swap(dataList, index2, index2 + 1);
                    swap = true; 
                }
            }

            if (swap == false) {
                break;
            }
        }

        return dataList;
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            testData.add((int)(Math.random() * 100));
        }
        BubbleSort bSort = new BubbleSort();
        System.out.println(bSort.sort(testData));
    }

}