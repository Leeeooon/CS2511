package domains.dummy;

import framework.problem.Problem;

public class DummyProblem extends Problem {
    
        public DummyProblem() {
            super();
            super.setName("Dummy");
            super.setIntroduction(INTRO);
            super.setMover(new DummyMover());
            super.setInitialState(new DummyState("Here is/an initial/state"));
            super.setCurrentState(super.getInitialState());
            super.setFinalState(new DummyState("What happens/when/you call/Uber"));
        }

        private static final String INTRO = 
                "You are stranded on Pluto. You have no fuel or provisions. "
              + "Devise a sequence of actions that will get you home.";

    }