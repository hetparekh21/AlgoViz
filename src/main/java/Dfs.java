import java.util.HashSet;

public class Dfs extends AlgoViz {

    static final int CODE = 2;
    static final String ALGO = "DFS";

//    private HashMap<String, String> parent = new HashMap<>();
//    private String found;
    private int nodesVisited = 0;
    private boolean FOUND = false;
    private HashSet<String> visited = new HashSet<>();

    public void dfs(int[] source) {

        if (FOUND) {
            return;
        }

        sleep();

        markVisited(visited, source);
        nodesVisited++;
        markPath(source);
        printMap(map);

        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};

        String par = source[0] + "," + source[1];

        // check if any neighboring space is the destination
        for (int i = 0; i < 4; i++) {
            int[] xy = new int[]{source[0] + x[i], source[1] + y[i]};
            if (isValid(xy) && !isVisited(visited, xy)) {
                if (isDestination(xy)) {
                    FOUND = true;
//                    found = xy[0] + "," + xy[1];
//                    parent.put(found, par);
                    return;
                }
            }
        }

//        if (FOUND) {
//            return;
//        }

        // visit the neighboring nodes
        for (int i = 0; i < 4; i++) {
            int[] xy = new int[]{source[0] + x[i], source[1] + y[i]};
            if (isValid(xy) && !isVisited(visited, xy)) {
//                parent.put(xy[0] + "," + xy[1],par);
                dfs(xy);
            }
            if (FOUND) {
                return;
            }
        }

    }

    @Override
    void start(int[] source) {
        System.out.println("Press Enter to start");
        sc.nextLine();
        sc.nextLine();
        this.dfs(source);
    }

    @Override
    void markFinalPath() {

//        String par = found;
//
//        while (par != null) {
//            String[] cord = par.split(",");
//            int[] coordinate = new int[]{Integer.parseInt(cord[0]), Integer.parseInt(cord[1])};
//            markFinalPath(coordinate);
//            par = parent.get(par);
//        }

    }

    @Override
    void end() {
        printMap(map);
        System.out.println();
        System.out.println("Nodes Visited : " + nodesVisited);
    }

    @Override
    String getAlgo() {
        return ALGO;
    }
}
