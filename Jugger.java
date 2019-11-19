package Percolation;

import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Jugger {
	    public boolean canMeasureWater(int x, int y, int z) {
	        if (x + y < z) {
	            return false;
	        }
	        
	        Deque<State> stack = new ArrayDeque<>();
	        Set<State> visited = new HashSet<>();
	        State init = new State(0, 0);
	        stack.push(init);
	        
	        while (!stack.isEmpty()) {
	            State state = stack.pop();
	            if (state.a + state.b == z) {
	                return true;
	            }
	            
	            if (visited.add(state)) {
	                for (State nextState : getNextStates(state.a, state.b, x, y)) {
	                    stack.push(nextState);
	                }
	            }
	        }

	        return false;
	    }
	    
	    private List<State> getNextStates(int a, int b, int x, int y) {
	        List<State> nextStates = new ArrayList<>();
	        // empty jug1
	        nextStates.add(new State(0, b));
	        // fill jug1
	        nextStates.add(new State(x, b));
	        // empty jug2
	        nextStates.add(new State(a, 0));
	        // fill jug2
	        nextStates.add(new State(a, y));
	        // pour from jug1 to jug2
	        nextStates.add(new State(a - Math.min(a, y - b), b + Math.min(a, y - b)));
	        // pour from jug2 to jug1
	        nextStates.add(new State(a + Math.min(x - a, b), b - Math.min(x - a, b)));
	        return nextStates;
	    }
	    
	    class State {
	        public int a, b;

	        public State(int a, int b) {
	            this.a = a;
	            this.b = b;
	        }

	        public int hashCode() {
	            return 31 * a + b;
	        }

	        public boolean equals(Object o) {
	            State other = (State)o;
	            return this.a == other.a && this.b == other.b;
	        }
	    }
	}

