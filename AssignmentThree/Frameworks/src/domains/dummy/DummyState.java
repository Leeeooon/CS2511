package domains.dummy;

import framework.problem.State;
import java.util.stream.Stream;

public class DummyState implements State {
    
        public DummyState(String contents) {
            this.contents = contents;
        }

        @Override
        public boolean equals(Object other) {
            DummyState otherDummy = (DummyState) other;
            return this.contents.equals(otherDummy.contents);
        }

        @Override
        public String toString() {
            String[] lines = contents.split("/");
            Stream<Integer> lengths = Stream.of(lines).map(String::length);
            int maxLength = lengths.max(Integer::compare).get();

            StringBuilder sb = new StringBuilder();
            addDashes(sb, maxLength + 2);
            sb.append(NEW_LINE);
            for (String line : lines) {
                sb.append("| ");
                sb.append(String.format("%1$-" + maxLength + "s", line));
                sb.append(" |");
                sb.append(NEW_LINE);
            }
            addDashes(sb, maxLength + 2);
            return sb.toString();
        }

        private void addDashes(StringBuilder sb, int n) {
            sb.append("+");
            for (int i = 0; i < n; i++) {
                sb.append("-");
            }
            sb.append("+");
        }

        private final String contents;

        private static final String NEW_LINE = "\n";
    }