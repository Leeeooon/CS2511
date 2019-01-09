package domains.dummy;

import framework.problem.Mover;
import framework.problem.State;

public class DummyMover extends Mover {

        public static final String BEAM = "Beam Me Up";
        public static final String CAB = "Call a Cab";
        public static final String UBER = "Order Uber";
        public static final String PUNT = "Punt";
        public static final String MARY = "Hail Mary";
    
        public DummyMover() {
            super.addMove(BEAM, s -> tryBeam(s));
            super.addMove(CAB, s -> tryCab(s));
            super.addMove(UBER, s -> tryUber(s));
            super.addMove(PUNT, s -> tryPunt(s));
            super.addMove(MARY, s -> illegalMove(s));
        }

        private State tryBeam(State state) {
            return new DummyState("State after/beaming up");
        }

        private State tryCab(State state) {
            return new DummyState("State/after/calling cab");
        }

        private State tryUber(State state) {
            return new DummyState("What happens/when/you call/Uber");
        }

        private State tryPunt(State state) {
            return new DummyState("Punt/   Punt/   Punt");
        }

        private State illegalMove(State state) {
            return null;
        }
    }