package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 1. 최단 경로 문제란?
	• 최단 경로 문제란 두 노드를 잇는 가장 짧은 경로를 찾는 문제임
	• 가중치 그래프 (Weighted Graph) 에서 간선 (Edge)의 가중치 합이 최소가 되도록 하는 경로를 찾는 것이 목적
최단 경로 문제 종류
	1. 단일 출발 (single-source) 최단 경로 문제
		○ 그래프 내의 특정 노드 u 에서 출발하여, 그래프 내의 모든 다른 노드에 도착하는 가장 짧은 경로를 찾는 문제
	2. 단일 도착 (single-destination) 최단 경로 문제
		○ 모든 노드들로부터 출발해서, 그래프 내의 특정 노드 u 로 도착하는 가장 짧은 경로를 찾는 문제
	3. 단일 쌍(single-pair) 최단 경로 문제
		○ 주어진 노드 u 와 v 간의 최단 경로를 찾는 문제
	4. 전체 쌍(all-pair) 최단 경로: 그래프 내의 모든 노드 쌍 (u, v) 사이에 대한 최단 경로를 찾는 문제


2. 최단 경로 알고리즘 - 다익스트라 알고리즘
	• 다익스트라 알고리즘은 위의 최단 경로 문제 종류 중, 1번에 해당
하나의 정점에서 다른 모든 정점에 도착하는 가장 짧은 거리를 구하는 문제

 */

class Edge implements Comparable<Edge> {
    public int distance;
    public String vertex;

    public Edge(int distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    // System.out.println() 으로 객체 자체 출력시, 다음 메서드를 호출하여, 원하는 attribute등을 출력할 수 있음
    public String toString() {
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }

    @Override
    public int compareTo(Edge edge)
    {
        return this.distance - edge.distance;
    }
}


public class DijkstraPath {
	
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start) {
        Edge edgeNode, adjacentNode;
        ArrayList<Edge> nodeList;
        int currentDistance, weight, distance;
        String currentNode, adjacent;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        HashMap<String, Integer> distances  = new HashMap<String, Integer>();

        for (String key : graph.keySet()) {
            distances.put(key, Integer.MAX_VALUE);	//모든 최단거리 박스 변수 / 무한대 넣어주는 과정
        }
        distances.put(start, 0);

        priorityQueue.add(new Edge(distances.get(start), start)); //우선순위큐에 시작지점 넣어줌

        while (priorityQueue.size() > 0) {
            edgeNode = priorityQueue.poll(); //우선순위에서 꺼내져서 비교할 변수 
            currentDistance = edgeNode.distance;
            currentNode = edgeNode.vertex;

            if (distances.get(currentNode) < currentDistance) { 
                continue; 
            }

            nodeList = graph.get(currentNode);
            for (int index = 0; index < nodeList.size(); index++) {
                adjacentNode = nodeList.get(index); //엣지를 가져옴
                adjacent = adjacentNode.vertex; //인접 엣지 
                weight = adjacentNode.distance; //인접 엣지 거리
                distance = currentDistance + weight; //현재 노드 거리 + 뽑힌 인접 엣지 거리

                if (distance < distances.get(adjacent)) {//해당노드 이름을 adjacent 키값으로 해쉬 활용 
                    distances.put(adjacent, distance); //더 최단거리가 나오면 해당 정보를 업데이트
                    priorityQueue.add(new Edge(distance, adjacent));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));

        DijkstraPath dObject = new DijkstraPath();
        System.out.println(dObject.dijkstraFunc(graph, "A"));
    }
}