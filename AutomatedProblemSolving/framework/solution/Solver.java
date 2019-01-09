package framework.solution;

import framework.graph.Vertex;
import framework.problem.Problem;
import framework.problem.State;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * This abstract class implements a partial problem solver
 * that uses a queue to control its search. 
 * Subclasses must call the "setQueue" method to install the 
 * particular type of queue used.
 * This class defines the template method "search" that uses
 * the methods "add," for adding to the queue, and "expand," 
 * for expanding a node in the state space.
 * "add" and "expand" must be provided by a subclass.
 * This class also keeps and makes available search statistics.
 * 
 * @author tcolburn
 */
public abstract class Solver {
    
    /**
     * Creates a solver object for a particular problem.
     * @param problem the problem to be solved
     */
    public Solver(Problem problem) {
        this.problem = problem;
        statistics = new Statistics();
        statistics.initStat(LENGTH);
        statistics.initStat(TIME);
        statistics.initStat(NUM_OPS);
        statistics.initStat(QUEUE_SIZE);
    }

    /**
     * Setter for this solver's queue.
     * This must be called by a subclass.
     * @param queue the queue to be used in this solver
     */
    public void setQueue(Queue<Vertex> queue) {
        this.queue = queue;
    }

    /**
     * Getter for this solver's queue.
     * @return this solver's queue
     */
    public Queue<Vertex> getQueue() {
        return queue;
    }
    
    /**
     * Expands a vertex in the state space search tree created by 
     * this solver.
     * This method is abstract and must be overridden by a subclass.
     * @param v the vertex to be expanded
     * @return a list of vertices that are direct children of v in
     * the search tree
     */
    public abstract List<Vertex> expand(Vertex v);
    
    /**
     * Adds a vertex to the queue used by this solver.
     * @param v the vertex to be enqueued
     */
    public abstract void add(Vertex v);
    
    /**
     * Removes a vertex from the queue used by this solver.
     * @return the dequeued vertex
     * @throws RuntimeException if the queue is empty
     */
    public Vertex remove() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Cannot remove from empty queue");
        }
        return queue.remove();
    }
    
    /**
     * Attempts to solve the problem from the current state.
     * If successful, a solution object is constructed and
     * statistics are recorded.
     */
    public void solve() {   
        Vertex current = new Vertex(problem.getCurrentState());
        Date before = new Date();
        Vertex goal = search(current);
        Date after = new Date();
        if (goal == null) {
            throw new RuntimeException("Solution not found");
        }
        solution = new Solution(current, goal);
        statistics.putStat(LENGTH, solution.getLength());
        statistics.putStat(TIME, (int)(after.getTime() - before.getTime()));
    }

    /**
     * Getter for this solver's statistics.
     * @return this solver's statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Getter for this solver's problem.
     * @return this solver's problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Getter for this solver's solution object.
     * @return this solver's solution object
     */
    public Solution getSolution() {
        return solution;
    }
    
    /* private methods and fields follow */
    
    private Vertex search(Vertex start) {   
        start.setDistance(0);
        start.setPredecessor(null);
        queue.clear(); 
        statistics.reset(); 
        
        addVertex(start);
        while ( !queue.isEmpty() ) {            
            Vertex vertex = removeVertex();
            if (success(vertex)) {
                return vertex;
            }
            expand(vertex).stream().forEach(v -> { 
                addVertex(v); 
            });
        }
        return null; // no solution found
    }
    
    private void addVertex(Vertex v) {
        add(v);
        updateStats();
    }
    
    private Vertex removeVertex() {
        Vertex v = remove();
        updateStats();
        return v;
    }
    
    private void updateStats() {
        statistics.incrStat(NUM_OPS);
        statistics.putStat(QUEUE_SIZE, Math.max(statistics.getStat(QUEUE_SIZE), queue.size()));
    }
    
    private boolean success(Vertex v) {
        problem.setCurrentState((State)v.getData());
        return problem.success();
    }
    
    private final Problem problem;
    private Queue<Vertex> queue;
    private Solution solution;
    private final Statistics statistics;
    
    private static final String LENGTH = "Solution length";
    private static final String TIME = "Solution time";
    private static final String NUM_OPS = "Num of queue ops";
    private static final String QUEUE_SIZE = "Max queue size";
}
