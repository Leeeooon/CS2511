package framework.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a graph of vertices.
 * A vertex's neighbors in the graph are represented using an adjacency list.
 * Adjacency lists are looked up using a hash map.
 * @author tcolburn
 */
public class Graph {  
    
    /**
     * Creates a new, empty graph.
     */
    public Graph() {
        adjHash = new HashMap<>(ADJ_CAPACITY);
        vertices = new HashMap<>(GRAPH_CAPACITY);
        numEdges = 0;
    }
    
    /**
     * Adds an edge (v1, v2) to this graph.
     * @param v1 The first vertex in the edge
     * @param v2 The second vertex in the edge
     */
    public void addEdge(Vertex v1, Vertex v2) {
        if (!vertices.containsKey(v1)) {
            vertices.put(v1, v1);
        }
        if (!vertices.containsKey(v2)) {
            vertices.put(v2, v2);
        }
        if (!adjHash.containsKey(v1)) {
            adjHash.put(v1, new LinkedList<>());
        }
        adjHash.get(v1).add(v2);
        numEdges++;
    }

    /**
     * Tests whether a pair of vertices forms an edge in this graph.
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return Whether there is an edge from the first vertex to the second
     */
    public boolean isEdge(Vertex v1, Vertex v2) {
        return adjHash.get(v1).contains(v2);
    }

    /**
     * Creates a string representation of this graph's adjacency list for testing.
     * @return The string representation of the adjacency list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Vertex vertex : adjHash.keySet()) {
            builder.append("\n").append(vertex);
            List<Vertex> adjList = adjHash.get(vertex);
            if ( adjList == null ) continue;
            adjList.stream().forEach((adjVertex) -> {
                builder.append(" -> ").append(adjVertex);
            });
        }
        return builder.toString();
    }
    
    /*
      Vertices are stored on a hash map for fast lookup of copies.
    */
    private final HashMap<Vertex, Vertex> vertices;

    public HashMap<Vertex, Vertex> getVertices() {
        return vertices;
    }
    
    public Vertex find(Vertex vertex) {
        return vertices.get(vertex);
    }

    public int getNumVertices() {
        return vertices.size();
    }
    
    /*
      Adjacency lists are stored on a hash map for fast lookup.
    */
    private final HashMap<Vertex, List<Vertex>> adjHash;

    public List<Vertex> getAdjacencyList(Vertex v) {
        return adjHash.get(v);
    }
    
    /*
      The number of edges in this graph.
    */
    private int numEdges;

    public int getNumEdges() {
        return numEdges;
    }
    
    private final static int GRAPH_CAPACITY = 500000;
    private final static int ADJ_CAPACITY = 500000;
}
