package calculator;

public class ParenthesisCalculator extends PrecedenceCalculator {
    public ParenthesisCalculator(String title) {
        super(title);
    }
    
    public ParenthesisCalculator(){
        this("Calculator With Operator Precedence and Paretheses");
    }
    
    @Override
    public double evaluate() {
        setState(State.START);
        while(true){
            switch(getState()){
                case START:
                    start();
                    break;
                case NUMBER:
                    number();
                    break;
                case OPERATOR:
                    operator();
                    break;
                case LEFT_PAREN:
                    leftParen();
                    break;
                case RIGHT_PAREN:
                    rightParen();
                    break;
                case END:
                    if(getStack().size() > 1) parenError();
                    else end();
                    break;
                default:
                    throw new Error("Something is wrong in SimpleCalculator.evaluate"); //shouldn't happen
            }
        }
    }
    
    @Override
    public void reduce(){
        if(null != getState())
          switch (getState()) {
            case OPERATOR:
                if(getDispenser().tokenIsEOF() && numOpNumOnStack()){
                    while(numOpNumOnStack()) reduceNumOpNum();
                }
                else if(stackHasPrecedence() || numOpNumOnStack()){
                } 
                else while(numOpNumOnStack()) reduceNumOpNum();     
                break;
            case END:
                while(numOpNumOnStack()) reduceNumOpNum();
                break;
            case LEFT_PAREN:
                while(numOpNumOnStack()) reduceNumOpNum();
                break;
            case RIGHT_PAREN:
                while(numOpNumOnStack()) reduceNumOpNum();
                getStack().pop();
                while(numOpNumOnStack()) reduceNumOpNum();
                break;
            default:
                break;
        }
    }
    
    private void start() {
        getDispenser().advance();
        if(getDispenser().tokenIsNumber()){
            setState(State.NUMBER);
        }
        else if(getDispenser().tokenIsLeftParen()){
            setState(State.LEFT_PAREN);
        }
        else syntaxError(NUM_OR_LEFT_PAREN);
    }
    
    private void number() {
        shift();
        getDispenser().advance();
        if(getDispenser().tokenIsEOF()) setState(State.END);
        else if(getDispenser().tokenIsOperator()) setState(State.OPERATOR);
        else if(getDispenser().tokenIsRightParen()) setState(State.RIGHT_PAREN);
        else syntaxError(OP_OR_END);
    }
    
    private void operator() {
        reduce();
        shift();
        getDispenser().advance();
        if(getDispenser().tokenIsNumber()) setState(State.NUMBER);
        else if(getDispenser().tokenIsLeftParen()) setState(State.LEFT_PAREN);
        else syntaxError(NUM_OR_LEFT_PAREN);
    }
    
    private void leftParen() {
        reduce();
        shift();
        getDispenser().advance();
        if(getDispenser().tokenIsNumber()) setState(State.LEFT_PAREN);
        else syntaxError(NUM);
    }
    
    private void rightParen(){
        reduce();
        getDispenser().advance();
        if(getDispenser().tokenIsOperator()) setState(State.OPERATOR);
        else if(getDispenser().tokenIsEOF()) setState(State.END);
        else syntaxError(OP);
    }
    
    public void parenError(){
        throw new RuntimeException("Error -- mismatched parentheses");
    }
    
    // Constant strings used when generating syntax errors.
    // These are public so they can be used by subclasses.
    public static final String NUM_OR_LEFT_PAREN = "number or left parenthesis";
    public static final String OP = "operator";
}
