import java.util.Scanner;

import AlgoViz.*;
import Algorithms.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Select Map type");
        System.out.println("""
                1) Matrix
                2) Tree
                """);

        int mapChoice = sc.nextInt();

        System.out.println("What Algorithm would you like to visualise ?");
        printMenu();

        int algoChoice = sc.nextInt();

        Algorithm algorithm = null;

        switch (algoChoice){

            case 1:
                if (mapChoice == 1){
                    algorithm = new BfsMatrix();
                }else {
                    algorithm = new BfsTree();
                }
                break;
            case 2:
                if(mapChoice == 1) {
                    algorithm = new DfsMatrix();
                }else {
                    algorithm = new DfsTree();
                }
                break;
            default:
                break;
        }

        assert algorithm != null;
        algorithm.start();
        algorithm.end();

    }

    public static void printMenu(){
        String menu = """
                1) BFS (Breadth First Search)
                2) DFS (Depth First Search)
                """;
        System.out.println(menu);
    }

}
