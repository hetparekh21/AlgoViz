import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class AlgoViz {

    char[][] map;
    final char DESTINATION = '$', SOURCE = '@', PATH = '*', SPACE = ' ';
    final String DESTINATION_COLOR = Color.RED_BOLD, SOURCE_COLOR = Color.BLUE_BOLD, PATH_COLOR = Color.PURPLE, MAP_COLOR = Color.WHITE_BOLD;
    final String ALGO_COLOR = Color.WHITE_BOLD;
    String POPULATE;
    int N;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AlgoViz algoViz;

        // print algo menu
        printMenu();

        int algo = sc.nextInt();

        switch (algo) {
            case Bfs.CODE:
                algoViz = new Bfs();
                break;
            case Dfs.CODE:
                algoViz = new Dfs();
                break;
            default:
                System.out.println("Please select a valid option");
                return;
        }

        // initial setup
        algoViz.setup();

        // map preview
        algoViz.printMap();

        // source
        int[] source = new int[2];
        if (!algoViz.getSource(source)) {
            return;
        }
        algoViz.printMap();

        // dest
        int[] destination = new int[2];
        if (!algoViz.getDestination(destination)) {
            return;
        }
        algoViz.printMap();

        // start the process
        algoViz.start(source);

        // end the process
        algoViz.end();

    }

    public boolean isVisited(HashSet<String> visited, int[] coordinate) {
        return visited.contains(coordinate[0] + "" + coordinate[1]);
    }

    public void markVisited(HashSet<String> visited, int[] coordinate) {
        visited.add(coordinate[0] + "" + coordinate[1]);
    }

    public void markPath(int[] coordinate) {
        if (map[coordinate[0]][coordinate[1]] != SOURCE) {
            map[coordinate[0]][coordinate[1]] = PATH;
        }
    }

    public boolean isDestination(int[] coordinate) {
        return isValid(coordinate) && map[coordinate[0]][coordinate[1]] == DESTINATION;
    }

    public boolean isValid(int[] coordinate) {
        return coordinate[0] < N && coordinate[0] >= 0 && coordinate[1] < N && coordinate[1] >= 0;
    }

    public void printMap() {

        // clear the terminal before printing
        clearConsole();
        System.out.println("Algo : " + ALGO_COLOR + this.getAlgo() + Color.RESET);
        System.out.println();

        // column
        System.out.print(MAP_COLOR + "    ");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // top border
        System.out.print("    ");
        for (int i = 0; i < map.length; i++) {
            System.out.print("_" + " ");
        }
        System.out.println(Color.RESET);


        for (int i = 0; i < map.length; i++) {

            // left border
            System.out.print(MAP_COLOR + i + " " + '|' + " " + Color.RESET);

            for (int j = 0; j < map[0].length; j++) {
                char tem = map[i][j];
                if (tem == DESTINATION) {
                    System.out.print(DESTINATION_COLOR + tem + " ");
                } else if (tem == SOURCE) {
                    System.out.print(SOURCE_COLOR + tem + " ");
                } else if (tem == PATH) {
                    System.out.print(PATH_COLOR + tem + " ");
                } else {
                    System.out.print(tem + " ");
                }
                System.out.print(Color.RESET);
            }

            // right border
            System.out.println(MAP_COLOR + '|' + Color.RESET);
        }

        // bottom border
        System.out.print("    ");
        for (int i = 0; i < map.length; i++) {
            System.out.print(MAP_COLOR + "_" + " ");
        }
        System.out.println(Color.RESET);
    }

    public void initMap() {

        map = new char[N][N];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = SPACE;
            }
        }

    }

    public void setSource(int[] source) {
        map[source[0]][source[1]] = SOURCE;
    }

    public void setDestination(int[] destination) {
        map[destination[0]][destination[1]] = DESTINATION;
    }

    public void clearConsole() {
        System.out.println(POPULATE);
    }

    public void setup() {
        sc = new Scanner(System.in);
        POPULATE = "\n".repeat(50);
        getMapSize();
        initMap();
    }

    public boolean getSource(int[] source) {
        System.out.println("Please enter the coordinates(row & column) for the source");
        source[0] = sc.nextInt();
        source[1] = sc.nextInt();
        if (!isValid(source)) {
            System.out.println("Please Enter a valid coordinate");
            return false;
        }
        setSource(source);
        return true;
    }

    public boolean getDestination(int[] dest) {
        System.out.println("Please enter the coordinates(row & column) for the destination");
        dest[0] = sc.nextInt();
        dest[1] = sc.nextInt();
        if (!isValid(dest)) {
            System.out.println("Please Enter a valid coordinate");
            return false;
        }
        setDestination(dest);
        return true;
    }

    public void getMapSize() {
        System.out.println("Please enter the map size (keep it till 10 for better visuals)");
        N = sc.nextInt();
    }

    abstract void start(int[] source);

    abstract void end();

    abstract String getAlgo();

    public void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printMenu() {
        System.out.println("Please select algorithm");
        System.out.println("""
                Algorithms :
                1. Breadth First Search (BFS)
                2. Depth First Search (DFS)
                """);
    }

}