package Chapter_2_Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

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
    public int compareTo(Prim_Edge Prim_Edge)
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

        ArrayList<Prim_Edge> mst = new ArrayList<Prim_Edge>();

        HashMap<String, ArrayList<Prim_Edge>> adjacentEdges = new HashMap<String, ArrayList<Prim_Edge>>();

        for (int index = 0; index < edges.size(); index++) {
            currentNode = edges.get(index);
            if (!adjacentEdges.containsKey(currentNode.node1)) {
                adjacentEdges.put(currentNode.node1, new ArrayList<Prim_Edge>());
            }
            if (!adjacentEdges.containsKey(currentNode.node2)) {
                adjacentEdges.put(currentNode.node2, new ArrayList<Prim_Edge>());
            }
        }

        for (int index = 0; index < edges.size(); index++) {
            currentNode = edges.get(index);
            currentEdgeList = adjacentEdges.get(currentNode.node1);
            currentEdgeList.add(new Prim_Edge(currentNode.weight, currentNode.node1, currentNode.node2));
            currentEdgeList = adjacentEdges.get(currentNode.node2);
            currentEdgeList.add(new Prim_Edge(currentNode.weight, currentNode.node2, currentNode.node1));

        }

        connectedNodes.add(startNode);
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<Prim_Edge>());
        priorityQueue = new PriorityQueue<Prim_Edge>();
        for (int index = 0; index < candidateEdgeList.size(); index++) {
            priorityQueue.add(candidateEdgeList.get(index));
        }

        while (priorityQueue.size() > 0) {
            poppedEdge = priorityQueue.poll();
            if (!connectedNodes.contains(poppedEdge.node2)) {
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