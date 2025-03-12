package Algorithms;

import java.util.List;
import java.util.Queue;

public abstract class BfsInterface<T> extends Algorithm<T> {

    private final String TIME_COMPLEXITY = "n";

    public abstract Queue<T> getQueue();

    public abstract boolean isDestination(T position);

    public abstract List<T> getValidNeighbours(T position);

    public abstract boolean isVisited(T position);

    public abstract void markVisited(T position);

    @Override
    public final void algo() {
        this.bfs();
    }

    @Override
    public final String getTimeComplexity(){
        return TIME_COMPLEXITY;
    }

    private void bfs() {

        Queue<T> queue = getQueue();

        while (!queue.isEmpty()) {

            T node = queue.remove();

            if (isVisited(node)) {
                continue;
            }

            Helper.sleep(getSleepN());

            markVisited(node);
            markPath(node);

            List<T> neighbours = getValidNeighbours(node);

            for (T pos : neighbours) {
                if (isDestination(pos)) {
                    markPath(pos);
                    markVisited(pos);
                    return;
                }
            }

            queue.addAll(neighbours);

            printMap();

        }

    }

}
