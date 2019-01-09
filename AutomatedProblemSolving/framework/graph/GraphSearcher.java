package framework.graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class provides methods for searching graphs and displaying
 * the results of searching.
 * @author tcolburn
 */
public class GraphSearcher {  
    
    /**
     * Creates a graph searcher.
     * @param graph the graph to be searched
     */
    public GraphSearcher(Graph graph) {
        this.graph = graph;
        vertices = graph.getVertices();
    } 
    
    /*
      A functional object used for searching.
    */
    private Consumer<Vertex> adder;
    
    /**
     * Performs a breadth-first search on this graph.
     * @param start the start vertex for the search
     */
    public void breadthFirstSearch(Vertex start) {
        adder = (vertex) -> deque.addLast(vertex);
        search(start);
    }
    
    /**
     * Performs a depth-first search on this graph.
     * @param start the start vertex for the search
     */
    public void depthFirstSearch(Vertex start) {
        adder = (vertex) -> deque.addFirst(vertex);
        search(start);
    }

    /**
     * The core search operation for this graph.
     * It uses a double-ended queue so that either breadth-first or
     * depth-first search can be performed depending on to which end of the
     * queue newly discovered vertices are added.
     * @param start The start vertex for the search
     */
    private void search(Vertex start) {  
        if (!vertices.containsKey(start)) {
            throw new RuntimeException("Error in search. No such vertex: " + start);
        }
        searchInit();
        Vertex s = vertices.get(start);
        s.setOpen(false);
        s.setDistance(0);
        deque = new LinkedList<>(); 
        
        add(s);
        while ( !deque.isEmpty() ) {             
            Vertex v = remove();               
            List<Vertex> adjList = graph.getAdjacencyList(v);   
            if ( adjList != null ) {
                for (Vertex successor : adjList) {
                    if ( successor.isOpen() ) {
                        successor.setOpen(false);
                        successor.setDistance(v.getDistance()+1);
                        successor.setPredecessor(v);
                        add(successor);  
                    }
                }
            }
        }
    }
    
    /**
     * Adds a vertex to the double-ended queue.
     * If the adder adds to the end, BFS results; if the adder adds to the
     * beginning, DFS results.
     * @param v 
     */
    private void add(Vertex v) {
        adder.accept(v);
    }
    
    /**
     * Removes a vertex from the front of the double-ended queue.
     * @return the removed vertex
     */
    private Vertex remove() {
        Vertex v = deque.remove();
        return v;
    }

    /**
     * An initializing operation for the core search operation.
     * All vertices are set to open with null predecessors and
     * a distance of inifinity from the start.
     */
    private void searchInit() {
        Iterator<Vertex> iterator = vertices.keySet().iterator();
        while ( iterator.hasNext() ) {
            Vertex vertex = (Vertex) iterator.next();
            vertexInit(vertex);
            List<Vertex> adjList = graph.getAdjacencyList(vertex); 
            for (Vertex successor : adjList) {
                vertexInit(successor);
            }
        }
    }
    
    /**
     * An initializing operation for individual vertices in preparation
     * for a search.
     * @param v the vertex to be initialized
     */
    private void vertexInit(Vertex v) {
        v.setOpen(true);
        v.setDistance(INFINITY);
        v.setPredecessor(null);
    }
    
    private final Graph graph;
    private final HashMap<Vertex, Vertex> vertices;
    
    private Deque<Vertex> deque;

    /**
     * A large number used to represent infinity for the search operations.
     * It is public just so test classes can use it.
     */
    public static final int INFINITY = 999999999;
}