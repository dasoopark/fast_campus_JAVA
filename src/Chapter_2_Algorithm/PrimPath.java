package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 최소 신장 트리의 이해
1. 프림 알고리즘 (Prim's algorithm)
	• 대표적인 최소 신장 트리 알고리즘
		○ Kruskal’s algorithm (크루스칼 알고리즘), Prim's algorithm (프림 알고리즘)
	• 프림 알고리즘
		○ 시작 정점을 선택한 후, 정점에 인접한 간선중 최소 간선으로 연결된 정점을 선택하고, 해당 정점에서 다시 최소 간선으로 연결된 정점을 선택하는 방식으로 최소 신장 트리를 확장해가는 방식
	• Kruskal's algorithm 과 Prim's algorithm 비교
		○ 둘다, 탐욕 알고리즘을 기초로 하고 있음 (당장 눈 앞의 최소 비용을 선택해서, 결과적으로 최적의 솔루션을 찾음)
		○ Kruskal's algorithm은 가장 가중치가 작은 간선부터 선택하면서 MST를 구함
		○ Prim's algorithm은 특정 정점에서 시작, 해당 정점에 연결된 가장 가중치가 작은 간선을 선택, 간선으로 연결된 정점들에 연결된 간선 중에서 가장 가중치가 작은 간선을 택하는 방식으로 MST를 구함
		
2. 그림으로 이해하는 프림 알고리즘
	1. 임의의 정점을 선택, '연결된 노드 집합'에 삽입
	2. 선택된 정점에 연결된 간선들을 간선 리스트에 삽입
	3. 간선 리스트에서 최소 가중치를 가지는 간선부터 추출해서,
		○ 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 이미 들어 있다면, 스킵함(cycle 발생을 막기 위함)
		○ 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 들어 있지 않으면, 해당 간선을 선택하고, 해당 간선 정보를 '최소 신장 트리'에 삽입
	4. 추출한 간선은 간선 리스트에서 제거
간선 리스트에 더 이상의 간선이 없을 때까지 3-4번을 반복

프림 알고리즘 코드 (프로젝트: CH30_PRIM_BASIC)
	모든 간선 정보를 저장 (adjacentEdges)
	임의의 정점을 선택, '연결된 노드 집합(connectedNodes)'에 삽입
	선택된 정점에 연결된 간선들을 간선 리스트(candidateEdgeList)에 삽입
	
	간선 리스트(candidateEdgeList)에서 최소 가중치를 가지는 간선부터 추출해서,
	해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 이미 들어 있다면, 스킵함(cycle 발생을 막기 위함)
	해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 들어 있지 않으면, 해당 간선을 선택하고, 해당 간선 정보를 '최소 신장 트리(mst)'에 삽입
	해당 간선에 연결된 인접 정점의 간선들 중, '연결된 노드 집합(connectedNodes)' 에 없는 노드와 연결된 간선들만 간선 리스트(candidateEdgeList) 에 삽입
	'연결된 노드 집합(connectedNodes)' 에 있는 노드와 연결된 간선들을 간선 리스트에 삽입해도, 해당 간선은 스킵될 것이기 때문임
	어차피 스킵될 간선을 간선 리스트(candidateEdgeList)에 넣지 않으므로 해서, 간선 리스트(candidateEdgeList)에서 최소 가중치를 가지는 간선부터 추출하기 위한 자료구조 유지를 위한 effort를 줄일 수 있음 (예, 최소힙 구조 사용)
	선택된 간선은 간선 리스트에서 제거
	간선 리스트에 더 이상의 간선이 없을 때까지 3-4번을 반복


 */
class Prim_Edge implements Comparable<Prim_Edge> {
    public int weight;
    public String node1;
    public String node2;

    public Prim_Edge(int weight, String node1, String node2) {
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }

    // System.out.println() 으로 객체 자체 출력시, 다음 메서드를 호출하여, 원하는 attribute등을 출력할 수 있음
    public String toString() {
        return "(" + this.weight + ", " + this.node1 + ", " + this.node2 + ")";
    }

    @Override
    public int compareTo(Prim_Edge Prim_Edge) 	//최소 힙을 사용하기 위해
    {
        return this.weight - Prim_Edge.weight;
    } 
}

public class PrimPath {
    public ArrayList<Prim_Edge> primFunc(String startNode, ArrayList<Prim_Edge> edges) {
        Prim_Edge currentNode, poppedEdge, adjacentEdgeNode;
        ArrayList<Prim_Edge> currentEdgeList, candidateEdgeList, adjacentEdgeNodes;
        PriorityQueue<Prim_Edge> priorityQueue;
        ArrayList<String> connectedNodes = new ArrayList<String>();

        ArrayList<Prim_Edge> mst = new ArrayList<Prim_Edge>(); //최소 신장 트리

        HashMap<String, ArrayList<Prim_Edge>> adjacentEdges = new HashMap<String, ArrayList<Prim_Edge>>();

        for (int index = 0; index < edges.size(); index++) {
            currentNode = edges.get(index); //노드들을 알아내기 위해
            if (!adjacentEdges.containsKey(currentNode.node1)) { //중복한게 없다면 버텍스들이 해시맵에 키로 들어감
                adjacentEdges.put(currentNode.node1, new ArrayList<Prim_Edge>());
            }
            if (!adjacentEdges.containsKey(currentNode.node2)) {
                adjacentEdges.put(currentNode.node2, new ArrayList<Prim_Edge>());
            }
        }

        for (int index = 0; index < edges.size(); index++) { //간선 추가 작업
            currentNode = edges.get(index); 
            currentEdgeList = adjacentEdges.get(currentNode.node1);
            currentEdgeList.add(new Prim_Edge(currentNode.weight, currentNode.node1, currentNode.node2));
            currentEdgeList = adjacentEdges.get(currentNode.node2);
            currentEdgeList.add(new Prim_Edge(currentNode.weight, currentNode.node2, currentNode.node1));
            													//node2 이므로 ,해당 정점부터 시작해야 하니깐, node2 부터 나와야함 - 위랑 다른 이유
        }

        connectedNodes.add(startNode); //연결된 간선
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<Prim_Edge>()); //연결된게 없으면 오류가 날수있으니 getOrDefault
        priorityQueue = new PriorityQueue<Prim_Edge>(); //최소힙 우선순위큐
        for (int index = 0; index < candidateEdgeList.size(); index++) {
            priorityQueue.add(candidateEdgeList.get(index));
        }

        while (priorityQueue.size() > 0) {
            poppedEdge = priorityQueue.poll();
            if (!connectedNodes.contains(poppedEdge.node2)) {
            	//안들어 잇다면, mst에 추가
                connectedNodes.add(poppedEdge.node2);
                mst.add(new Prim_Edge(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));

                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<Prim_Edge>());
                for (int index = 0; index < adjacentEdgeNodes.size(); index++) {
                    adjacentEdgeNode = adjacentEdgeNodes.get(index);
                    if (!connectedNodes.contains(adjacentEdgeNode.node2)) {
                        priorityQueue.add(adjacentEdgeNode);
                    }
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        ArrayList<Prim_Edge> myedges = new ArrayList<Prim_Edge>();
        myedges.add(new Prim_Edge(7, "A", "B"));
        myedges.add(new Prim_Edge(5, "A", "D"));
        myedges.add(new Prim_Edge(8, "B", "C"));
        myedges.add(new Prim_Edge(9, "B", "D"));
        myedges.add(new Prim_Edge(7, "D", "E"));
        myedges.add(new Prim_Edge(5, "C", "E"));
        myedges.add(new Prim_Edge(7, "B", "E"));
        myedges.add(new Prim_Edge(6, "D", "F"));
        myedges.add(new Prim_Edge(8, "E", "F"));
        myedges.add(new Prim_Edge(9, "E", "G"));
        myedges.add(new Prim_Edge(11, "F", "G"));

        PrimPath pObject = new PrimPath();
        System.out.println(pObject.primFunc("A", myedges));
    }
}