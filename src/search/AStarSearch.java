package search;

import core.Ilayout;

import java.util.*;

public class AStarSearch {
    protected Queue<State> abertos;

    private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for (Ilayout e : children) {
            if (n.father == null || !e.equals(n.father.layout)) {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        abertos = new PriorityQueue<>(10,
                (s1, s2) -> (int) Math.signum(s1.getF() - s2.getF()));
        Map<Ilayout, State> fechados = new HashMap<>();
        abertos.add(new State(s, null));
        List<State> sucs;

        while (!abertos.isEmpty()) {
            State atual = abertos.poll();

            if (atual.layout.isGoal(goal)) {
                List<State> path = new ArrayList<>();
                State temp = atual;
                while (temp != null) {
                    path.add(temp);
                    temp = temp.father;
                }
                Collections.reverse(path);
                return path.iterator();
            } else {
                fechados.put(atual.layout, atual);
                sucs = this.sucessores(atual);
                for (State child : sucs) {
                    if (!fechados.containsKey(child.layout) && !abertos.contains(child)) {
                        abertos.add(child);
                    }
                }
            }
        }
        return null;
    }

    public static class State {
        private final Ilayout layout;
        private final double g;
        private final double f;
        public State father;

        public State(Ilayout l, State father) {
            layout = l;
            this.father = father;
            if (father != null) {
                g = father.g + l.getK();
            } else {
                g = 0.0;
            }
            f = g + l.getHeuristic(null);
        }

        public String toString() {
            return layout.toString();
        }

        public double getG() {
            return g;
        }

        public double getF() {
            return f;
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }
}
