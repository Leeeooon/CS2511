package framework.graph;

import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import framework.graph.Vertex;
import java.util.List;
import java.util.Stack;
/**
 * @author Ryan Lenz
 */
public class GraphCreator {
    
    public Graph createGraphFor(Problem problem) {
        graph = new Graph();
        graphStack = new Stack();
        start = new Vertex(problem.getInitialState());
        
        graphStack.push(start);
        
        myMover = problem.getMover();
        moves = myMover.getMoveNames();
        
        while(!graphStack.isEmpty()){
            u = (Vertex) graphStack.pop();
            
            for(String x:moves){
                next = myMover.doMove(x, (State) start.getData());
                if(next != null){
                    v = new Vertex(next);
                    if(graph.getVertices().containsKey(v)){
                        v = graph.getVertices().get(v);
                    }
                    else{
                        graphStack.push(v);
                    }
                    graph.addEdge(u,v);
                }
            }
        }
        
        return graph;
    }
    
    private Graph graph = null;
    private List<String> moves;
    private Mover myMover;
    private State next;
    private Stack graphStack;
    private Vertex start, u, v;
   
}
