package Chapter_2_Algorithm;

import java.util.ArrayList;

/*
 1. 순차 탐색 (Sequential Search) 이란?
	• 탐색은 여러 데이터 중에서 원하는 데이터를 찾아내는 것을 의미
	• 데이터가 담겨있는 배열을 앞에서부터 하나씩 비교해서 원하는 데이터를 찾는 방법
 */
public class SequencialSearch {
    public int searchFunc(ArrayList<Integer> dataList, Integer searchItem) {
        for (int index = 0; index < dataList.size(); index++) {
            if (dataList.get(index) == searchItem) {
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SequencialSearch sSearch = new SequencialSearch();
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 10; index++) {
            testData.add((int)(Math.random() * 100));
        }
        System.out.println(testData);
        System.out.println(sSearch.searchFunc(testData, 19)); // 해당 숫자가 없으면, -1, 있으면 1을 리
    }
}