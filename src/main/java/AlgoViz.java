import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class AlgoViz {

    protected char[][] map;
    final char DESTINATION = '$', SOURCE = '@', PATH = '*', SPACE = ' ', FINAL_PATH = '+', WALL = '#', CURSOR = '=';
    final String DESTINATION_COLOR = Color.RED_BOLD, SOURCE_COLOR = Color.BLUE_BOLD, PATH_COLOR = Color.PURPLE, MAP_COLOR = Color.WHITE_BOLD, FINAL_PATH_COLOR = Color.BLUE_BOLD, WALL_COLOR = Color.WHITE_BOLD, CURSOR_COLOR = Color.BLUE, SPACE_COLOR = Color.WHITE;
    final String ALGO_COLOR = Color.WHITE_BOLD;
    String POPULATE;
    int N;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AlgoViz algoViz;

        // *********************************************** Preparing the Map  ***********************************************

        // print algo menu
        printAlgoMenu();

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
        algoViz.printMap(algoViz.map);

        // source
        int[] source = new int[2];
        if (!algoViz.getSource(source)) {
            return;
        }
        algoViz.printMap(algoViz.map);

        // dest
        int[] destination = new int[2];
        if (!algoViz.getDestination(destination)) {
            return;
        }
        algoViz.printMap(algoViz.map);

        // walls ?
        System.out.println("Would you like to place walls ? (1 for yes, any other key for no");
        int walls = sc.nextInt();

        if (walls == 1) {
            algoViz.getWallInput();
        }

        // *********************************************** Running the Algorithm  ***********************************************

        // start the process
        algoViz.start(source);

        // mark final path
        algoViz.markFinalPath();

        // end the process
        algoViz.end();

    }

    // Map Manipulation
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

    private void getWallInput() {

        System.out.println("Press q at any moment to quit placing walls");

        try {

            KeyDetection kd = new KeyDetection(this);

            kd.start(N / 2, N / 2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void markPath(int[] coordinate) {
        if (map[coordinate[0]][coordinate[1]] != SOURCE && map[coordinate[0]][coordinate[1]] != DESTINATION && map[coordinate[0]][coordinate[1]] != WALL) {
            map[coordinate[0]][coordinate[1]] = PATH;
        }
    }

    public void markSpace(int[] coordinate) {
        if (map[coordinate[0]][coordinate[1]] != SOURCE && map[coordinate[0]][coordinate[1]] != DESTINATION) {
            map[coordinate[0]][coordinate[1]] = SPACE;
        }
    }

    public void markCursor(int[] coordinate) {
        if (map[coordinate[0]][coordinate[1]] != SOURCE && map[coordinate[0]][coordinate[1]] != DESTINATION && map[coordinate[0]][coordinate[1]] != WALL) {
            map[coordinate[0]][coordinate[1]] = CURSOR;
        }
    }

    public void markWall(int[] coordinate) {
        if (map[coordinate[0]][coordinate[1]] != SOURCE && map[coordinate[0]][coordinate[1]] != DESTINATION) {
            map[coordinate[0]][coordinate[1]] = WALL;
        }
    }

    public void markFinalPath(int[] coordinate) {
        if (map[coordinate[0]][coordinate[1]] != SOURCE && map[coordinate[0]][coordinate[1]] != DESTINATION && map[coordinate[0]][coordinate[1]] != WALL) {
            map[coordinate[0]][coordinate[1]] = FINAL_PATH;
        }
    }

    // checks
    public boolean isDestination(int[] coordinate) {
        return isValid(coordinate) && map[coordinate[0]][coordinate[1]] == DESTINATION;
    }

    public boolean isValid(int[] coordinate) {
        return coordinate[0] < N && coordinate[0] >= 0 && coordinate[1] < N && coordinate[1] >= 0 && map[coordinate[0]][coordinate[1]] != WALL;
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

    // set and get
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

    public char getCharAt(int[] coordinate) {
        return map[coordinate[0]][coordinate[1]];
    }

    // Algo related
    abstract String getAlgo();

    abstract void start(int[] source);

    abstract void markFinalPath();

    abstract void end();

    // Helpers
    public void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printAlgoMenu() {
        System.out.println("Please select algorithm");
        System.out.println("""
                Algorithms :
                1. Breadth First Search (BFS)
                2. Depth First Search (DFS)
                """);
    }

    public void printMap(char[][] map) {

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
                    System.out.print(DESTINATION_COLOR + tem);
                } else if (tem == SOURCE) {
                    System.out.print(SOURCE_COLOR + tem);
                } else if (tem == PATH) {
                    System.out.print(PATH_COLOR + tem);
                } else if (tem == FINAL_PATH) {
                    System.out.print(FINAL_PATH_COLOR + tem);
                } else if (tem == CURSOR) {
                    System.out.print(CURSOR_COLOR + tem);
                } else if (tem == WALL) {
                    System.out.print(WALL_COLOR + tem);
                } else if (tem == SPACE) {
                    System.out.print(SPACE_COLOR + tem);
                }
                System.out.print(" " + Color.RESET);
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

    public boolean isVisited(HashSet<String> visited, int[] coordinate) {
        return visited.contains(coordinate[0] + "" + coordinate[1]);
    }

    public void markVisited(HashSet<String> visited, int[] coordinate) {
        visited.add(coordinate[0] + "" + coordinate[1]);
    }

}