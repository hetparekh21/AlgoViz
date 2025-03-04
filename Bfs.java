import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs extends AlgoViz{

    static final int CODE = 1;
    static final String ALGO = "BFS";

    private HashMap<String,String> parent = new HashMap<>();
    private String found;
    private int nodesVisited = 0;
    public void bfs(int[] source) {

        HashSet<String> visited = new HashSet<>();

        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(source);
        parent.put(source[0]+","+source[1],null);

        while (!queue.isEmpty()) {

            int[] xy = queue.remove();

            if (isVisited(visited,xy)){
                continue;
            }

            sleep();

            markPath(xy);
            markVisited(visited, xy);
            nodesVisited++;

            String par = xy[0] + "," + xy[1];

            for (int i = 0; i < 4; i++) {
                int[] new_xy = new int[]{xy[0] + x[i], xy[1] + y[i]};
                if (isValid(new_xy) && !isVisited(visited, new_xy)) {
                    if(isDestination(new_xy)){
                        found = new_xy[0] + "," + new_xy[1];
                        parent.put(new_xy[0] + "," + new_xy[1],par);
                        return;
                    }
                    parent.put(new_xy[0] + "," + new_xy[1],par);
                    queue.add(new_xy);
                }
            }

            printMap();

        }

    }



    @Override
    public void start(int[] source) {
        System.out.println("Press Enter to start");
        sc.nextLine();
        sc.nextLine();
        this.bfs(source);
    }

    @Override
    public void markFinalPath() {

        String par = found;

        while(par != null){
            String[] cord = par.split(",");
            int[] coordinate = new int[]{Integer.parseInt(cord[0]),Integer.parseInt(cord[1])};
            markFinalPath(coordinate);
            par = parent.get(par);
        }

    }

    @Override
    public void end() {

        printMap();
        System.out.println();
        System.out.println("Nodes Visited : " + nodesVisited);
    }

    @Override
    public String getAlgo() {
        return ALGO;
    }

}
