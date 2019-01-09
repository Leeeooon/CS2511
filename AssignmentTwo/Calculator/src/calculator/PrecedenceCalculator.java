package calculator;


public class PrecedenceCalculator extends NoPrecedenceCalculator {
    
    public PrecedenceCalculator(String title) {
        super(title);
    }
    
    public PrecedenceCalculator() {
        this("Calculator With Operator Precedence");
    }
    
    @Override
    public void reduce() {
        if(getDispenser().tokenIsEOF()&& numOpNumOnStack()){
            while(numOpNumOnStack()) reduceNumOpNum();
        }
        
        if(stackHasPrecedence() && numOpNumOnStack()){
            while(numOpNumOnStack()) reduceNumOpNum();
        }
        }
         
    public boolean stackHasPrecedence(){
        if (getStack().size()>=3){
            Object item = getStack().get(getStack().size()-2);
            char stackOP = (Character)item;
            char tokenOP = getDispenser().getChar();
            switch(stackOP){
                case '/':
                    return true;
                case '*':
                    if(tokenOP == '*' || tokenOP == '+' || tokenOP == '-'){
                        return true;
                    }
                    return false;
                case '+':
                    if(tokenOP =='+' || tokenOP == '-'){
                        return true;
                    }
                    return false;
                case '-':
                    if(tokenOP == '-'){
                        return true;
                    }
                    return false;
                default: 
                    syntaxError (OP_OR_END);
            }
        }
        return false;
    }
}

          
    

 

