package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/*
   퀵 정렬
   	• 정렬 알고리즘의 꽃
	• 기준점(pivot 이라고 부름)을 정해서, 기준점보다 작은 데이터는 왼쪽(left), 큰 데이터는 오른쪽(right) 으로 모으는 함수를 작성함
	• 각 왼쪽(left), 오른쪽(right)은 재귀용법을 사용해서 다시 동일 함수를 호출하여 위 작업을 반복함
	   함수는 왼쪽(left) + 기준점(pivot) + 오른쪽(right) 을 리턴함
 */
public class QuickSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return dataList;
        }
        int pivot = dataList.get(0); //피벗 => 처음부터 시작

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        for (int index = 1; index < dataList.size(); index++) { //피벗을 제외한 인덱스 순회
            if (dataList.get(index) > pivot) {
                rightArr.add(dataList.get(index));
            } else {
                leftArr.add(dataList.get(index));
            }
        }

        // 간단히 ArrayList 생성 후, addAll() 메서드로 순차적으로 배열을 넣어줌
        ArrayList<Integer> mergedArr = new ArrayList<Integer>();
        mergedArr.addAll(sort(leftArr));	//addAll 메서드: ArrayList에 다른 ArrayList의 데이터를 통째로 붙이기 위한 메서드임
        //재귀적 용법 -> 최종적으로는 위로위로 계속 돌아가면서 최종정렬된 배열이 리턴됨.
        mergedArr.addAll(Arrays.asList(pivot)); //Arrays.asList: 일반 배열을 ArrayList로 변환한다.
        mergedArr.addAll(sort(rightArr));

        return mergedArr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }
        QuickSort qSort = new QuickSort();
        System.out.println(qSort.sort(testData));
    }
}