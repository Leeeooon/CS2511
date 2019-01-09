package calculator;

import util.TokenDispenser;
import console.Console;
import java.util.Stack;

/**
 * This class extends the Console class to implement a simple 
 * shift-reduce calculator.
 * This calculator can evaluate expressions of the form NUM [OP NUM].
 * @author tcolburn
 */
public class SimpleCalculator extends Console {
    
    /**
     * Creates a calculator by configuring the console and initializing
     * an empty stack.
     * @param title a title for the console
     */
    public SimpleCalculator(String title) {
        super(title, 400, 100);
        stack = new Stack<>();
    }

    /**
     * Handles the input to the calculator.
     * The input is evaluated and the result displayed.
     * If the input contains an error, the error is displayed.
     * This method overrides a method of the same name in the Console class.
     * @param input an expression string to be evaluated
     */
    @Override
    public void handleInput(String input) {
        dispenser = new TokenDispenser(input);
        stack.clear();
        errorMessage = "";
        try {
            result = evaluate();
            updateDisplay(Double.toString(result));
        }
        catch(Exception ex) {
            errorMessage = ex.getMessage();  // ex.printStackTrace();
            updateDisplay(errorMessage);
        }
    }

    /**
     * Gets the token dispenser for the current input.
     * This method is provided for subclasses.
     * @return the token dispenser
     */
    public TokenDispenser getDispenser() {
        return dispenser;
    }

    /**
     * Gets the result of evaluating the input.
     * @return the value of the input expression
     */
    public double getResult() {
        return result;
    }

    /**
     * Gets the current error message, if any.
     * @return the current error message (may be empty)
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    
    /**
     * Performs the evaluation of the input expression.
     * Evaluation proceeds by implementing the state machine
     * described by the associated state diagram.
     * Note that the case clauses of the switch statement
     * correspond to state names in the diagram, and that
     * similarly named private methods carry out the required 
     * processing.
     * The state names are values of the enumerated type called State.
     * This method is public so it can be overridden by subclasses.
     * @return the value of the input expression.
     */
    public double evaluate() {
        setState(State.START);
        while (true) {
            switch (getState()) {
                case START: 
                    start(); 
                    break;
                case NUMBER1:
                    number1(); 
                    break;
                case OPERATOR:
                    operator(); 
                    break;
                case NUMBER2:
                    number2(); 
                    break;
                case END:
                    end();
                    return (Double)getStack().pop();
                default: 
                    throw new Error("Something is wrong in SimpleCalculator.evaluate"); // shouldn't happen
            }
        } 
    }
    
    /**
     * Performs the processing in the state named START.
     * A token is parsed, and if it is not a number an error is signaled.
     * Otherwise, the state is changed to NUMBER1.
     */
    private void start() {
        dispenser.advance();
        if (!dispenser.tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER1);
    }
    
    /**
     * Throws a syntax error indicating what the parser was expecting and what it received.
     * @param expected a string indicating what the parser was expecting
     */
    public void syntaxError(String expected) {
        throw new RuntimeException("Error -- " + expected + " expected. Found: " + dispenser.getToken());
    }
    
    /**
     * Performs the processing in the state named NUMBER1.
     * The number is shifted onto the stack and the next token is parsed.
     * If the token is EOF the state is changed to END.
     * If the token is an operator the state is changed to OPERATOR.
     * Otherwise an error is signaled.
     */
    private void number1() {
        shift();
        dispenser.advance();
        if (dispenser.tokenIsEOF()) {
            setState(State.END);
        } else if (dispenser.tokenIsOperator()) {
            setState(State.OPERATOR);
        } else {
            syntaxError(OP_OR_END);
        }
    }
    
    /**
     * Shifts the current token onto the stack.
     * This method is public so that it can be used by subclasses.
     */
    public void shift() {
        stack.push(dispenser.getToken());
    }
    
    /**
     * Performs the processing in the state named OPERATOR.
     * The operator is shifted onto the stack and the next token is parsed.
     * If the next token is not a number an error is signaled.
     * Otherwise the state is changed to NUMBER2.
     */
    private void operator() {
        shift();
        dispenser.advance();
        if (!dispenser.tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER2);
    }
    
    /**
     * Performs the processing in the state named NUMBER2.
     * The number is shifted onto the stack and the next token is parsed.
     * If the next token is not EOF an error is signaled.
     * Otherwise the state is changed to END.
     */
    private void number2() {
        shift();
        dispenser.advance();
        if (!dispenser.tokenIsEOF()) {
            syntaxError(EOI);
        }
        setState(State.END);
    }
    
    /**
     * Performs the processing in the state named END.
     * The stack is reduced from NUM or NUM OP NUM to NUM.
     */
    public void end() {
        reduce();
    }
    
    /**
     * Reduces the stack from either NUM or NUM OP NUM to NUM.
     * This method is public so that it can be used or overridden by subclasses.
     */
    public void reduce() {
        if (numOpNumOnStack()) {
            reduceNumOpNum();
        }
    }
    
    /**
     * Determines whether NUM OP NUM is on the stack.
     * This method is public so that it can be used by subclasses.
     * @return true if NUM OP NUM is on the stack, false otherwise
     */
    public boolean numOpNumOnStack() {  // check if NUM OP NUM is on stack
        if (getStack().size() < 3) {
            return false;
        }
        Object item = getStack().get(getStack().size()-2);
        if (item.getClass() != Character.class) {
            return false;
        }
        char ch = (Character)item;
        return ch == '+' ||
               ch == '-' ||
               ch == '*' ||
               ch == '/';
    }
    
    /**
     * Reduces NUM OP NUM on stack to NUM.
     * This method should only be called when numOpNumOnStack() returns true.
     * This method is public so that it can be used by subclasses.
     */
    public void reduceNumOpNum() {
        double operand2 = (Double)stack.pop();
        char operator = (Character)stack.pop();
        double operand1 = (Double)stack.pop();
        stack.push(operate(operator, operand1, operand2));
    }
    
    /**
     * Private helper method for reduceNumOpNum.
     * Performs the operation indicated by the operator on the operands.
     * @param op the operator
     * @param op1 the first operand
     * @param op2 the second operand
     * @return the value of the result
     */
    private static double operate(char op, double op1, double op2) {
        if (op == '+') return op1+op2;
        else if (op == '-') return op1-op2;
        else if (op == '*') return op1*op2;
        else if (op == '/') return op1/op2;
        else throw new RuntimeException("Bad operator in Calculator.operate: " + op);
    }

    /**
     * An enumeration of possible state name values. 
     * Some of the names are not used in the simple calculator
     * but will be used in enhanced versions of the calculator.
     */
    public enum State {
        START,
        NUMBER1, 
        NUMBER2, 
        OPERATOR,
        NUMBER,
        LEFT_PAREN,
        RIGHT_PAREN,
        END
    };

    /**
     * Getter for the current state of the state machine.
     * @return the current state
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for the current state of the state machine.
     * @param state the current state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter for the expression stack.
     * @return the expression stack
     */
    public Stack<Object> getStack() {
        return stack;
    }
    
    // Constant strings used when generating syntax errors.
    // These are public so they can be used by subclasses.
    public static final String NUM = "number";
    public static final String OP_OR_END = "operator or end of input";
    public static final String EOI = "end of input";
    
    // Private instance fields
    private TokenDispenser dispenser;  // the tokenizing parser
    private final Stack<Object> stack; // the expression stack
    private State state;               // the current state of expression processing
    private double result;             // the result of evaluting the input string
    private String errorMessage;       // the current error message
    
}