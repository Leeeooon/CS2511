package domains.farmer;

import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tcolburn
 */
public class FarmerMoverTest {
    
    public FarmerMoverTest() {
        mover = new FarmerMover();
        
        moveNames = mover.getMoveNames();
        goesAlone = moveNames.get(0);
        takesWolf = moveNames.get(1);
        takesGoat = moveNames.get(2);
        takesCabbage = moveNames.get(3);
        
        takesGorilla = "Farmer Takes Gorilla";  // an illegal move name
    }
    
    @Test
    public void testNameLegality() {
        assertTrue(tryName(goesAlone) == Message.NO_EXCEPTION_OCCURRED);
        assertTrue(tryName(takesWolf) == Message.NO_EXCEPTION_OCCURRED);
        assertTrue(tryName(takesGoat) == Message.NO_EXCEPTION_OCCURRED);
        assertTrue(tryName(takesCabbage) == Message.NO_EXCEPTION_OCCURRED);
        
        assertTrue(tryName(takesGorilla) == Message.EXCEPTION_OCCURRED);
    }
    
    
    private Message tryName(String moveName) {
        try {
            next = mover.doMove(moveName, new FarmerState("West", "West", "West", "West"));
        }
        catch(Exception ex) {
            return Message.EXCEPTION_OCCURRED;
        }
        return Message.NO_EXCEPTION_OCCURRED;
    }
    
    
    @Test
    public void testGoesAlone() {
        doMoveIllegal(goesAlone, "West", "West", "West", "West");
        
        doMoveLegal(goesAlone, "East", "East", "West", "East",
                               "West", "East", "West", "East");
    }
    
    @Test
    public void testTakesWolf() {
        doMoveIllegal(takesWolf, "West", "West", "West", "West");
        
        doMoveIllegal(takesWolf, "West", "East", "West", "East");
        
        doMoveLegal(takesWolf, "West", "West", "East", "West",
                               "East", "East", "East", "West");
        
        doMoveLegal(takesWolf, "East", "East", "East", "West",
                               "West", "West", "East", "West");
    }
    
    @Test
    public void testTakesGoat() {
        doMoveIllegal(takesGoat, "West", "West", "East", "West");
        
        doMoveLegal(takesGoat, "West", "West", "West", "West",
                               "East", "West", "East", "West");
        
        doMoveLegal(takesGoat, "East", "West", "East", "West",
                               "West", "West", "West", "West");
    }
    
    @Test
    public void testTakesCabbage() {
        doMoveIllegal(takesCabbage, "West", "West", "West", "West");
        
        doMoveLegal(takesCabbage, "West", "East", "West", "West",
                                  "East", "East", "West", "East");
        
        doMoveLegal(takesCabbage, "West", "West", "East", "West",
                                  "East", "West", "East", "East");
    }
    
    private void doMoveIllegal(String m, String f, String w, String g, String c) {
        next = mover.doMove(m, new FarmerState(f, w, g, c));
        assertTrue(next == null);
    }
    
    private void doMoveLegal(String m, String fCurr, String wCurr, String gCurr, String cCurr,
                                       String fNext, String wNext, String gNext, String cNext) {
        FarmerState curr = new FarmerState(fCurr, wCurr, gCurr, cCurr);
        FarmerState copy = new FarmerState(fCurr, wCurr, gCurr, cCurr);
        next = mover.doMove(m, curr);
        assertTrue(next != null);
        assertTrue(((FarmerState)next).equals(new FarmerState(fNext, wNext, gNext, cNext)));
        assertTrue(curr.equals(copy));
    }
    
    private final FarmerMover mover;
    private final List<String> moveNames;
    private final String goesAlone, takesWolf, takesGoat, takesCabbage, takesGorilla;
    
    private State next;
    
    private enum Message {EXCEPTION_OCCURRED, NO_EXCEPTION_OCCURRED};
    
}