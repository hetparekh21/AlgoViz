import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Dfs extends AlgoViz{

    static final int CODE = 2;
    static final String ALGO = "DFS";

    private int nodesVisited = 0;
    private boolean FOUND = false;
    private HashSet<String> visited = new HashSet<>();

    public void dfs(int[] source) {

        if (FOUND){
            return;
        }

        if(this.isDestination(source)){
            FOUND = true;
            return;
        }

        sleep();

        markVisited(visited,source);
        nodesVisited++;
        markPath(source);
        printMap();

        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};

        for(int i = 0 ; i < 4 ; i++){
            int[] xy = new int[]{source[0] + x[i], source[1] + y[i]};
            if(isValid(xy) && !isVisited(visited,xy)){
                dfs(xy);
            }
            if(FOUND){
                return;
            }
        }

    }

    @Override
    void start(int[] source) {
        this.dfs(source);
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
