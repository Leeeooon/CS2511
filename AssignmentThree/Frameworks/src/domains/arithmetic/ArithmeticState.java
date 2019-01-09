package domains.arithmetic;

import framework.problem.State;

public class ArithmeticState implements State {
    
    public ArithmeticState(int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
}
    
    
    @Override
    public boolean equals(Object obj){
         ArithmeticState otherArithmetic = (ArithmeticState) obj;
            return otherArithmetic.value == this.value;
           
    }

   
    @Override
        public String toString() {
           return "This value is:" + this.value;
        }

        
    
     
   
        private final int value;
   
    
}
