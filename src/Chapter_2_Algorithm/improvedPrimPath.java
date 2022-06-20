package Chapter_2_Algorithm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class imp_Edge implements Comparable<imp_Edge> {
    public String node;
    public int weight;

    public imp_Edge(String node, int weight) {
        this.weight = weight;
        this.node = node;
    }

    // System.out.println() 으로 객체 자체 출력시, 다음 메서드를 호출하여, 원하는 attribute등을 출력할 수 있음
    public String toString() {
        return "(" + this.weight + ", " + this.node + ")";
    }

    @Override
    public int compareTo(imp_Edge imp_Edge)
    {
        return this.weight - imp_Edge.weight;
    }
}

class Path {
    public String node1;
    public String node2;
    public int weight;

    public Path(String node1, String node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    // System.out.println() 으로 객체 자체 출력시, 다음 메서드를 호출하여, 원하는 attribute등을 출력할 수 있음
    public String toString() {
        return "(" + this.node1 + ", " + this.node2 + ", " + this.weight + ")";
    }
}
public class improvedPrimPath {

	 public ArrayList<Path> improvedPrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode) {
	        ArrayList<Path> mst = new ArrayList<Path>();
	        PriorityQueue<imp_Edge> keys = new PriorityQueue<imp_Edge>();
	        HashMap<String, imp_Edge> keysObjects = new HashMap<String, imp_Edge>();
	        HashMap<String, String> pi = new HashMap<String, String>();
	        Integer totalWeight = 0;
	        HashMap<String, Integer> linkedEdges;
	        imp_Edge edgeObject, poppedEdge, linkedEdge;

	        for(String key : graph.keySet()) {
	            if (key == startNode) {
	                edgeObject = new imp_Edge(key, 0);
	                pi.put(key, key);
	            } else {
	                edgeObject = new imp_Edge(key, Integer.MAX_VALUE);
	                pi.put(key, null);
	            }
	            keys.add(edgeObject);
	            keysObjects.put(key, edgeObject);
	        }

	        while(keys.size() > 0) {
	            poppedEdge = keys.poll();
	            keysObjects.remove(poppedEdge.node);

	            mst.add(new Path(pi.get(poppedEdge.node), poppedEdge.node, poppedEdge.weight));
	            totalWeight += poppedEdge.weight;

	            linkedEdges = graph.get(poppedEdge.node);
	            for (String adjacent : linkedEdges.keySet()) {
	                if (keysObjects.containsKey(adjacent)) {
	                    linkedEdge = keysObjects.get(adjacent);

	                    if (linkedEdges.get(adjacent) < linkedEdge.weight) {
	                        linkedEdge.weight = linkedEdges.get(adjacent);
	                        pi.put(adjacent, poppedEdge.node);

	                        // 우선순위 큐에 있는 데이터를 수정할 경우, 우선순위큐를 재조정해야 하므로, 간단히 삭제 후, 추가
	                        keys.remove(linkedEdge);
	                        keys.add(linkedEdge);
	                    }
	                }
	            }
	        }

	        System.out.println(totalWeight);
	        return mst;
	    }

	    public static void main(String[] args) {
	        HashMap<String, HashMap<String, Integer>> mygraph = new HashMap<String, HashMap<String, Integer>>();

	        HashMap<String, Integer> edges;
	        edges = new HashMap<String, Integer>();
	        edges.put("B", 7);
	        edges.put("D", 5);
	        mygraph.put("A", edges);

	        edges = new HashMap<String, Integer>();
	        edges.put("A", 7);
	        edges.put("D", 9);
	        edges.put("C", 8);
	        edges.put("E", 7);
	        mygraph.put("B", edges);

	        edges = new HashMap<String, Integer>();
	        edges.put("B", 8);
	        edges.put("E", 5);
	        mygraph.put("C", edges);

	        edges = new HashMap<String, Integer>();
	        edges.put("A", 5);
	        edges.put("B", 9);
	        edges.put("E", 7);
	        edges.put("F", 6);
	        mygraph.put("D", edges);

	        edges = new HashMap<String, Integer>();
	        edges.put("B", 7);
	        edges.put("C", 5);
	        edges.put("D", 7);
	        edges.put("F", 8);
	        edges.put("G", 9);
	        mygraph.put("E", edges);

	        edges = new HashMap<String, Integer>();
	        edges.put("D", 6);
	        edges.put("E", 8);
	        edges.put("G", 11);
	        mygraph.put("F", edges);

	        edges = new HashMap<String, Integer>();
	        edges.put("E", 9);
	        edges.put("F", 11);
	        mygraph.put("G", edges);

	        improvedPrimPath pObject = new improvedPrimPath();
	        System.out.println(pObject.improvedPrimFunc(mygraph, "A"));
	    }
}
