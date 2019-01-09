package framework.solution;

import framework.problem.Problem;
import framework.graph.Vertex;
import framework.problem.State;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;


/**
 * This class represents an A* solver by extending the StateSpaceSolver
 * class.
 * @author Your name and section here
 */
public class AStarSolver extends StateSpaceSolver {
    
    /**
     * Creates an A* solver.
     * This constructor should set the queue to a priority queue (PQ)
     * and set the statistics header.
     * @param problem 
     */
    public AStarSolver(Problem problem) {
        super(problem, false);
        super.setQueue(new PriorityQueue(getComparator()));
        super.getStatistics().setHeader("A* Search Statistics");
    }
    
    /**
     * Adds a vertex to the PQ.
     * @param v the vertex to be added
     */
    @Override
    public void add(Vertex v) {
        ((PriorityQueue)getQueue()).add(v);
    }
    
    /**
     * Creates a comparator object that compares vertices for ordering
     * in a PQ during A* search.
     * This should be used when creating the PQ.
     * @return the comparator object
     */
    public final Comparator<Vertex> getComparator() { 
         Comparator<Vertex> comp = (Vertex one, Vertex two) -> {
             State s1 = (State)one.getData();
             State s2 = (State)one.getData();
             
             if(s1.getHeuristic(super.getProblem().getFinalState()) > s2.getHeuristic(super.getProblem().getFinalState())){
                 return 1;
             }
             if(s1.getHeuristic(super.getProblem().getFinalState()) < s2.getHeuristic(super.getProblem().getFinalState())){
                 return -1;
             }
             else{
                 return 0;
             }
         };
         return comp;
    }
}
