package domains.farmer;

import framework.graph.Graph;
import framework.graph.Vertex;

/*
Ryan Lenz
CS 2521
*/

public class FarmerGraph extends Graph {
    
    public FarmerGraph() {
        
        v1 = new Vertex(new FarmerState("West", "West", "West", "West"));
        v2 = new Vertex(new FarmerState("West", "East", "West", "East"));
        v3 = new Vertex(new FarmerState("West", "East", "West", "West"));
        v4 = new Vertex(new FarmerState("West", "West", "East", "West"));
        v5 = new Vertex(new FarmerState("West", "West", "West", "East"));
        v6 = new Vertex(new FarmerState("East", "East", "East", "East"));
        v7 = new Vertex(new FarmerState("East", "West", "East", "West"));
        v8 = new Vertex(new FarmerState("East", "West", "East", "East"));
        v9 = new Vertex(new FarmerState("East", "East", "West", "East"));
        v10 = new Vertex(new FarmerState("East", "East", "East", "West"));
        
        super.addEdge(v1, v7);
        super.addEdge(v7, v1);
        super.addEdge(v7, v4);
        super.addEdge(v4, v7);
        super.addEdge(v4, v10);
        super.addEdge(v4, v8);
        super.addEdge(v10, v4);
        super.addEdge(v8, v4);
        super.addEdge(v10 ,v3);
        super.addEdge(v3, v10);
        super.addEdge(v8, v5);
        super.addEdge(v5, v8);
        super.addEdge(v3, v9);
        super.addEdge(v9, v3);
        super.addEdge(v5, v9);
        super.addEdge(v9, v5);
        super.addEdge(v9, v2);
        super.addEdge(v2, v9);
        super.addEdge(v2, v6);
        super.addEdge(v6, v2);
        
        START = v1;
        END = v6;
    }
    
    public Vertex getStart() {

        return START;
    }
    
    public Vertex getEnd() {
        
        return END;
    }
    
    /* private fields and methods follow */
    private final Vertex START, END;
    private final Vertex v1, v2, v3, v4, v5, v6 ,v7 ,v8 ,v9, v10;
}