import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Bfs extends AlgoViz{

    static final int CODE = 1;
    static final String ALGO = "BFS";

    private int nodesVisited = 0;
    public void bfs(int[] source) {

        HashSet<String> visited = new HashSet<>();

        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {

            int[] xy = queue.remove();

            if (isVisited(visited,xy)){
                continue;
            }

            sleep();

            if (isDestination(xy)) {
                break;
            }

            markPath(xy);
            markVisited(visited, xy);
            nodesVisited++;

            for (int i = 0; i < 4; i++) {
                int[] new_xy = new int[]{xy[0] + x[i], xy[1] + y[i]};
                if (isValid(new_xy) && !isVisited(visited, new_xy)) {
                    queue.add(new_xy);
                }
            }

            printMap();

        }

    }

    @Override
    void start(int[] source) {
        this.bfs(source);
        System.out.println();
        System.out.println( "Nodes Visited : " + nodesVisited);
    }

    @Override
    void end() {
        System.out.println();
        System.out.println("Nodes Visited : " + nodesVisited);
    }

    @Override
    String getAlgo() {
        return ALGO;
    }

}
