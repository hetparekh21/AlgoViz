package AlgoViz;
import Algorithms.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DfsMatrix extends DfsInterface<int[]> implements MatrixAlgorithm {

    private final String ALGO = "Matrix DFS";
    private final String TIME_COMPLEXITY = "n";

    private boolean FOUND = false;
    private final HashSet<String> visited = new HashSet<>();
    private char[][] map;

    private int[] destination;
    private int[] source;
    private int size;

    private final Scanner sc = new Scanner(System.in);

    @Override
    public boolean isDestination(int[] position) {
        return position[0] == destination[0] && position[1] == destination[1];
    }

    @Override
    public List<int[]> getValidNeighbours(int[] position) {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int[] new_xy = new int[]{position[0] + x[i], position[1] + y[i]};
            if (isValid(new_xy, size) && !isVisited(new_xy)) {
                list.add(new_xy);
            }
        }

        return list;
    }

    @Override
    public boolean isVisited(int[] position) {
        return visited.contains(position[0]+","+position[1]);
    }

    @Override
    public void markVisited(int[] position) {
        visited.add(position[0]+","+position[1]);
    }

    @Override
    public void print() {
        Helper.printMap(map);
    }

    @Override
    public Integer getSleepN() {
        return null;
    }

    @Override
    public void markFound() {
        FOUND = true;
    }

    @Override
    public boolean isFound() {
        return FOUND;
    }

    @Override
    public Scanner getScanner() {
        return sc;
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
        System.out.println("Please enter coordinates for source (row & column)");
        setSource(new int[]{sc.nextInt(), sc.nextInt()});
        System.out.println();
        System.out.println("Please enter coordinates for destination (row & column)");
        setDestination(new int[]{sc.nextInt(), sc.nextInt()});

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
    public void setDestination(int[] node) {
        this.destination = node;
        map[node[0]][node[1]] = Helper.DESTINATION;
    }

    @Override
    public void setSource(int[] node) {
        this.source = node;
        map[node[0]][node[1]] = Helper.SOURCE;
    }

    @Override
    public int[] getSource() {
        return source;
    }

    @Override
    public boolean isValid(int[] position, int N) {
        return position[0] < N && position[0] >= 0 && position[1] < N && position[1] >= 0 && map[position[0]][position[1]] != Helper.WALL;
    }
}
