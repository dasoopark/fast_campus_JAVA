package Chapter_2_Algorithm;

import java.util.ArrayList;

public class MergeSort {
	/*
	 mergeFunc 메서드 만들기
	• 목표: leftList 와 rightList 의 배열 데이터를 정렬하며 합쳐서, mergedList 라는 이름으로 return 하기
	leftList 와 rightList 는 이미 정렬된 상태 또는 데이터가 하나임
	 */
    public ArrayList<Integer> mergeFunc(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
        ArrayList<Integer> mergedList = new ArrayList<Integer>();
        Integer leftPoint = 0; //각 배열의 인덱스 포인터 변수
        Integer rightPoint = 0;

        // case1 - left/right 둘다 있을때
        while (leftList.size() > leftPoint && rightList.size() > rightPoint) { //포인터 사이즈가 증가해서 배열  초과할때 
            if (leftList.get(leftPoint) > rightList.get(rightPoint)) {
                mergedList.add(rightList.get(rightPoint)); //작은게 앞에 가야하므로 ,(정렬)
                rightPoint += 1;
                
            } else {
                mergedList.add(leftList.get(leftPoint));
                leftPoint += 1;
            }
        }

        // case2 - right 데이터가 없을 때 - 나머지 LeftList에 있는 데이터를 그대로 mergeList 뒤에 넣음
        while (leftList.size() > leftPoint) { 
            mergedList.add(leftList.get(leftPoint));
            leftPoint += 1;
        }

        // case3 - left 데이터가 없을 때 - 나머지 RightList에 있는 데이터를 그대로 mergeList 뒤에 넣음
        while (rightList.size() > rightPoint) {
            mergedList.add(rightList.get(rightPoint));
            rightPoint += 1;
        }

        return mergedList;
    }

    //배열을 앞뒤 두 배열로 짜르는 코드 
    public ArrayList<Integer> mergeSplitFunc(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return dataList;
        }
        
        int medium = dataList.size() / 2;

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        leftArr = this.mergeSplitFunc(new ArrayList<Integer>(dataList.subList(0, medium))); //sublist 함수 oneNote 참고 / 잘라주는 함수
        //0부터 medium-1 인덱스 번호까지 해당 배열 아이템을 서브 배열로 추출
        rightArr = this.mergeSplitFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size())));

        return this.mergeFunc(leftArr, rightArr); //정렬하고 합치는 함수 => mergeFunc
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }
        MergeSort mSort = new MergeSort();
        System.out.println(mSort.mergeSplitFunc(testData));
    }
}
