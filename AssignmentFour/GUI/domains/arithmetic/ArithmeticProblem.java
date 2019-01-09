package domains.arithmetic;

import framework.problem.Problem;

public class ArithmeticProblem extends Problem
{
  private static final String INTRO = "The starting value is zero. Your options are to add 3, subtract 5, divide by 2, or multiply by 2. Find a sequence of operations that results in the value 17.";

  public ArithmeticProblem()
  {
    super.setName("Arithmetic");
    super.setIntroduction("The starting value is zero. Your options are to add 3, subtract 5, divide by 2, or multiply by 2. Find a sequence of operations that results in the value 17.");
    super.setMover(new ArithmeticMover());
    super.setInitialState(new ArithmeticState(0));
    super.setCurrentState(super.getInitialState());
    super.setFinalState(new ArithmeticState(17));
  }
}