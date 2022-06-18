package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 1. BFS 와 DFS 란?
	• 대표적인 그래프 탐색 알고리즘
		○ 너비 우선 탐색 (Breadth First Search): 정점들과 같은 레벨에 있는 노드들 (형제 노드들)을 먼저 탐색하는 방식
		○ 깊이 우선 탐색 (Depth First Search): 정점의 자식들을 먼저 탐색하는 방식
		
	BFS/DFS 방식 이해를 위한 예제
	• BFS 방식: A - B - C - D - G - H - I - E - F - J
	한 단계씩 내려가면서, 해당 노드와 같은 레벨에 있는 노드들 (형제 노드들)을 먼저 순회함
 */
public class BFSSearch {
														//노드들을 받을거라서 ArratList
    public ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> graph, String startNode) {
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> needVisit = new ArrayList<String>();

        needVisit.add(startNode);

        while (needVisit.size() > 0) { //방문해야할 노드가 있음
            String node = needVisit.remove(0);
            if (!visited.contains(node)) { //방문한 노드가 없을시(!)이므로 =>! contains 활용 //해당노드 방문
                visited.add(node);
                needVisit.addAll(graph.get(node)); //visited에 등록된 새 노드와 연결된 어레이리스트 등록
            }
        }
        return visited;	
    }

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
        graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
        graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
        graph.put("E", new ArrayList<String>(Arrays.asList("D")));
        graph.put("F", new ArrayList<String>(Arrays.asList("D")));
        graph.put("G", new ArrayList<String>(Arrays.asList("C")));
        graph.put("H", new ArrayList<String>(Arrays.asList("C")));
        graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
        graph.put("J", new ArrayList<String>(Arrays.asList("I")));

        BFSSearch bObject = new BFSSearch();
        System.out.println(bObject.bfsFunc(graph, "A"));
    }
}