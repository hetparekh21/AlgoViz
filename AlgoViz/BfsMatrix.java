package AlgoViz;

import Algorithms.*;
import java.util.*;

public class BfsMatrix extends BfsInterface<int[]> implements MatrixAlgorithm  {

    private final String ALGO = "Matrix BFS";

    private int[] destination;
    private int[] source;
    private int size;

    private final Queue<int[]> queue;
    private final HashSet<String> visited = new HashSet<>();
    private char[][] map;

    private final Scanner sc = new Scanner(System.in);

    public BfsMatrix(){
        queue = new LinkedList<int[]>();
    }

    @Override
    public int getVisitedCount() {
        return visited.size();
    }

    @Override
    public String getAlgoName() {
        return ALGO;
    }

    @Override
    public int getTotalNodes() {
        return size*size;
    }

    @Override
    public void markPath(int[] position) {
        if (map[position[0]][position[1]] != Helper.SOURCE && map[position[0]][position[1]] != Helper.DESTINATION && map[position[0]][position[1]] != Helper.WALL) {
            map[position[0]][position[1]] = Helper.PATH;
        }
    }

    @Override
    public Queue<int[]> getQueue() {
        return queue;
    }

    @Override
    public boolean isDestination(int[] position) {
        return position[0] == destination[0] && position[1] == destination[1];
    }

    @Override
    public List<int[]> getValidNeighbours(int[] position) {

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int[] new_xy = new int[]{position[0] + x[i], position[1] + y[i]};
            if (isValid(new_xy,size) && !isVisited(new_xy)) {
                list.add(new_xy);
            }
        }

        return list;
    }

    @Override
    public boolean isVisited(int[] position) {
        return visited.contains(position[0] + "," + position[1]);
    }

    @Override
    public void markVisited(int[] position) {
        visited.add(position[0]+","+position[1]);
    }

    @Override
    public Integer getSleepN() {
        return null;
    }

    @Override
    public void print() {
        Helper.printMap(map);
    }

    @Override
    public void initialiseMap() {

        System.out.println("Please enter the map size (keep till 10 for better visuals)");
        this.size = sc.nextInt();

        map = new char[size][size];

        for (int i = 0; i < size ; i++){
            for (int j = 0; j < size; j++) {
                map[i][j] = Helper.SPACE;
            }
        }

        System.out.println();

        int x;
        int y;

        while(true){
            System.out.println("Please enter coordinates for source (row & column)");
            x = sc.nextInt();
            y = sc.nextInt();

            if (isValid(new int[]{x,y},size)){
                setSource(new int[]{x,y});
                break;
            }else {
                System.out.println("Please enter valid coordinates");
            }
        }
        System.out.println();
        while(true){
            System.out.println("Please enter coordinates for destination (row & column)");
            x = sc.nextInt();
            y = sc.nextInt();

            if (isValid(new int[]{x,y},size)){
                setDestination(new int[]{x,y});
                break;
            }else {
                System.out.println("Please enter valid coordinates");
            }
        }

    }

    @Override
    public void setDestination(int[] position) {
        this.destination = position;
        map[position[0]][position[1]] = Helper.DESTINATION;
    }

    @Override
    public void setSource(int[] position) {
        this.source = position;
        map[position[0]][position[1]] = Helper.SOURCE;
        queue.add(position);
    }

    @Override
    public int[] getSource() {
        return source;
    }

    @Override
    public boolean isValid(int[] position, int N) {
        return position[0] < N && position[0] >= 0 && position[1] < N && position[1] >= 0 && map[position[0]][position[1]] != Helper.WALL;
    }

    @Override
    public Scanner getScanner(){
        return this.sc;
    }

}
