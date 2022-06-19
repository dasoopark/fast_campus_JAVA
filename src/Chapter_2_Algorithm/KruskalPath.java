package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
 4. 크루스칼 알고리즘 (Kruskal's algorithm)
	1. 모든 정점을 독립적인 집합으로 만든다.
	2. 모든 간선을 비용을 기준으로 정렬하고, 비용이 작은 간선부터 양 끝의 두 정점을 비교한다.
	3. 두 정점의 최상위 정점을 확인하고, 서로 다를 경우 두 정점을 연결한다. (최소 신장 트리는 사이클이 없으므로, 사이클이 생기지 않도록 하는 것임)
탐욕 알고리즘을 기초로 하고 있음 (당장 눈 앞의 최소 비용을 선택해서, 결과적으로 최적의 솔루션을 찾음)


 */
class Kruskal_Edge implements Comparable<Kruskal_Edge> {
    public int weight;
    public String nodeV;
    public String nodeU; //다음에 연결될 노드

    public Kruskal_Edge(int weight, String nodeV, String nodeU) {
        this.weight = weight;
        this.nodeV = nodeV;
        this.nodeU = nodeU;
    }

    // System.out.println() 으로 객체 자체 출력시, 다음 메서드를 호출하여, 원하는 attribute등을 출력할 수 있음
    public String toString() {
        return "(" + this.weight + ", " + this.nodeV + ", " + this.nodeU + ")";
    }

    @Override
    public int compareTo(Kruskal_Edge Kruskal_Edge)
    {
        return this.weight - Kruskal_Edge.weight;
    }
}

public class KruskalPath {
	
	

    HashMap<String, String> parent = new HashMap<String, String>();
    HashMap<String, Integer> rank = new HashMap<String, Integer>();

    public String find(String node) {
        // path compression 기법
        if (this.parent.get(node) != node) {
            this.parent.put(node, find(this.parent.get(node)));
        }
        return this.parent.get(node);
    }

    public void union(String nodeV, String nodeU) {
        String root1 = find(nodeV);
        String root2 = find(nodeU);

        // union-by-rank 기법
        if (this.rank.get(root1) > this.rank.get(root2)) {
            this.parent.put(root2, root1);
        } else {
            this.parent.put(root1, root2);
            if (this.rank.get(root1) == this.rank.get(root2)) {
                this.rank.put(root2, this.rank.get(root2) + 1);
            }
        }
    }

    public void makeSet(String node) {
        this.parent.put(node, node);
        this.rank.put(node, 0);
    }

    public ArrayList<Kruskal_Edge> kruskalFunc(ArrayList<String> vertices, ArrayList<Kruskal_Edge> edges) {
        ArrayList<Kruskal_Edge> mst = new ArrayList<Kruskal_Edge>();
        Kruskal_Edge currentNode;

        // 1. 초기화
        for (int index = 0; index < vertices.size(); index++) {
            makeSet(vertices.get(index));
        }

        // 2. 간선 weight 기반 sorting
        // Collections.sort() : ArrayList 정렬 기능
        Collections.sort(edges);

        for (int index = 0; index < edges.size(); index++) {
            currentNode = edges.get(index);
            if (find(currentNode.nodeV) != find(currentNode.nodeU)) {
                union(currentNode.nodeV, currentNode.nodeU);
                mst.add(currentNode);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        ArrayList<String> vertices = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        ArrayList<Kruskal_Edge> edges = new ArrayList<Kruskal_Edge>();
        edges.add(new Kruskal_Edge(7, "A", "B"));
        edges.add(new Kruskal_Edge(5, "A", "D"));
        edges.add(new Kruskal_Edge(7, "B", "A"));	//oneNote 그림참고
        edges.add(new Kruskal_Edge(8, "B", "C"));
        edges.add(new Kruskal_Edge(9, "B", "D"));
        edges.add(new Kruskal_Edge(7, "B", "E"));
        edges.add(new Kruskal_Edge(8, "C", "B"));
        edges.add(new Kruskal_Edge(5, "C", "E"));
        edges.add(new Kruskal_Edge(5, "D", "A"));
        edges.add(new Kruskal_Edge(9, "D", "B"));
        edges.add(new Kruskal_Edge(7, "D", "E"));
        edges.add(new Kruskal_Edge(6, "D", "F"));
        edges.add(new Kruskal_Edge(7, "E", "B"));
        edges.add(new Kruskal_Edge(5, "E", "C"));
        edges.add(new Kruskal_Edge(7, "E", "D"));
        edges.add(new Kruskal_Edge(8, "E", "F"));
        edges.add(new Kruskal_Edge(9, "E", "G"));
        edges.add(new Kruskal_Edge(6, "F", "D"));
        edges.add(new Kruskal_Edge(8, "F", "E"));
        edges.add(new Kruskal_Edge(11, "F", "G"));
        edges.add(new Kruskal_Edge(9, "G", "E"));
        edges.add(new Kruskal_Edge(11, "G", "F"));

        KruskalPath kObject = new KruskalPath();
        System.out.println(kObject.kruskalFunc(vertices, edges));
    }
}