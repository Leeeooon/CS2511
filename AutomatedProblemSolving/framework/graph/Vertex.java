package framework.graph;

import java.util.Objects;

/**
 * This class represents a vertex in a graph.
 * @author tcolburn
 */
public class Vertex {
    
    /**
     * Creates a new vertex.
     * @param d an object represented by this vertex
     */
    public Vertex(Object d) {             
        data = d;
    }
    
    /**
     * The data stored in the vertex
     */
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    /**
     * Whether the vertex is open. Used for graph searching.
     */
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
    
    /**
     * The distance of this vertex from a start vertex.
     * Used for graph searching.
     */
    private int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    /**
     * The predecessor of this vertex in a graph search.
     */
    private Vertex predecessor;

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }
    
    /*
      A vertex's string representation is it's data's string representation.
    */
    @Override
    public String toString() {
        return data.toString();
    }
    
    /*
      Vertices are equal if their data components are equal.
    */
    @Override
    public boolean equals(Object other) {
	if (this == other) return true;
	if (other == null) return false;
	if (getClass() != other.getClass()) return false;
        if (data == null || ((Vertex)other).data == null) return false;
        return data.equals(((Vertex)other).data);
    }

    /*
      A vertex's hash value is computed from its data's hash value.
    */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.data);
        return hash;
    }
}