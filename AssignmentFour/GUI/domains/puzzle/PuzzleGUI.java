package domains.puzzle;

import framework.solution.SolvingAssistant;
import framework.ui.ProblemGUI;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 *
 * @author Ryan Lenz
 */
public class PuzzleGUI extends ProblemGUI{
    
    public PuzzleGUI(){
        
        super(new PuzzleProblem(), 500, 200);
        
        rem = Font.font("Dejavu Sans Mono", 24).getSize();
        
        b5 = new Button(super.getMoveNames().get(4));
        b5.setAlignment(Pos.CENTER);
        b5.setFont(Font.font("Dejavu Sans Mono", 14));
        b5.setPadding(new Insets(rem));
        b5.setOnAction(e -> {super.getSolver().tryMove(getMoveNames().get(4));
                             updateDisplay();});
        
        b6 = new Button(super.getMoveNames().get(5));
        b6.setAlignment(Pos.CENTER);
        b6.setFont(Font.font("Dejavu Sans Mono", 14));
        b6.setPadding(new Insets(rem));
        b6.setOnAction(e -> {super.getSolver().tryMove(getMoveNames().get(5));
                       updateDisplay();});
        
        b7 = new Button(super.getMoveNames().get(6));
        b7.setAlignment(Pos.CENTER);
        b7.setFont(Font.font("Dejavu Sans Mono", 14));
        b7.setPadding(new Insets(rem));
        b7.setOnAction(e -> {super.getSolver().tryMove(super.getMoveNames().get(6));
                       updateDisplay();});
        
        b8 = new Button(super.getMoveNames().get(7));
        b8.setAlignment(Pos.CENTER);
        b8.setFont(Font.font("Dejavu Sans Mono", 14));
        b8.setPadding(new Insets(rem));
        b8.setOnAction(e -> {super.getSolver().tryMove(getMoveNames().get(7));
                       updateDisplay();});
        
        super.getButtons().getChildren().addAll(b5, b6, b7, b8);
        
    }
    
    private final Button b5, b6, b7, b8;
    
    private final double rem;
}
