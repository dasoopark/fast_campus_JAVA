package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {
    public boolean searchFunc(ArrayList<Integer> dataList, Integer searchItem) {
    	//예외 처리
        if (dataList.size() == 1 && searchItem == dataList.get(0)) { //사이즈가 1개인데 아이템이 0번일때
            return true;
        }
        if (dataList.size() == 1 && searchItem != dataList.get(0)) { 
            return false;
        }
        if (dataList.size() == 0) {
            return false;
        }

        int medium = dataList.size() / 2;

        if (searchItem == dataList.get(medium)) {
            return true;
        } else {
            if (searchItem < dataList.get(medium)) { //SearchItem이 더 작으면 미디움 왼쪽에서 다시 이러한 작업을 반복함.
                return searchFunc(new ArrayList<Integer>(dataList.subList(0, medium)), searchItem); //재귀적 용법으로 이를 처리
                //subList 문법 활용 -> 원노트

            } else {
                return searchFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size())), searchItem);
                														//오른쪽으로 넘어가는 부분이니 medium부터 시작
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }
        Collections.sort(testData);

        System.out.println(testData);
        BinarySearch bSearch = new BinarySearch();
        System.out.println(bSearch.searchFunc(testData, 2)); // 해당 값이 없으면 false, 있으면 true 를 리턴함
    }
}