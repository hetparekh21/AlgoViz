package Algorithms;

import AlgoViz.TreeNode;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Helper {

    public final static char DESTINATION = '$', SOURCE = '@', PATH = '*', SPACE = ' ', FINAL_PATH = '+', WALL = '#', CURSOR = '=';
    public final static String DESTINATION_COLOR = Color.RED_BOLD, SOURCE_COLOR = Color.BLUE_BOLD, PATH_COLOR = Color.PURPLE, MAP_COLOR = Color.WHITE_BOLD, FINAL_PATH_COLOR = Color.BLUE_BOLD, WALL_COLOR = Color.WHITE_BOLD, CURSOR_COLOR = Color.BLUE, SPACE_COLOR = Color.WHITE;
    public final static String ALGO_COLOR = Color.WHITE_BOLD;

    final static String POPULATE = "\n".repeat(50);

    public static void clearConsole() {
        System.out.println(POPULATE);
    }

    public static void printMap(char[][] map) {

        // column
        System.out.print(Helper.MAP_COLOR + "    ");
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
            System.out.print(Helper.MAP_COLOR + i + " " + '|' + " " + Color.RESET);

            for (int j = 0; j < map[0].length; j++) {
                char tem = map[i][j];
                if (tem == Helper.DESTINATION) {
                    System.out.print(Helper.DESTINATION_COLOR + tem);
                } else if (tem == Helper.SOURCE) {
                    System.out.print(Helper.SOURCE_COLOR + tem);
                } else if (tem == Helper.PATH) {
                    System.out.print(Helper.PATH_COLOR + tem);
                } else if (tem == Helper.FINAL_PATH) {
                    System.out.print(Helper.FINAL_PATH_COLOR + tem);
                } else if (tem == Helper.CURSOR) {
                    System.out.print(Helper.CURSOR_COLOR + tem);
                } else if (tem == Helper.WALL) {
                    System.out.print(Helper.WALL_COLOR + tem);
                } else if (tem == Helper.SPACE) {
                    System.out.print(Helper.SPACE_COLOR + tem);
                }
                System.out.print(" " + Color.RESET);
            }

            // right border
            System.out.println(Helper.MAP_COLOR + '|' + Color.RESET);
        }

        // bottom border
        System.out.print("    ");
        for (int i = 0; i < map.length; i++) {
            System.out.print(Helper.MAP_COLOR + "_" + " ");
        }
        System.out.println(Color.RESET);
    }

    public static void sleep(Integer n) {
        if (n == null) n = 100;
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printTreeMap(TreeNode root,int depth) {
        if (root == null) return;

        int maxDepth = depth;
        int maxWidth = (int) Math.pow(2, maxDepth) * 2; // Controls spacing dynamically

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<String>> levels = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<String> level = new ArrayList<>();
            List<TreeNode> nextLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    level.add(String.valueOf(node.value));
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                } else {
                    level.add(" ");
                    nextLevel.add(null);
                    nextLevel.add(null);
                }
            }

            System.out.println("************level*************");
            System.out.println(level);
            System.out.println("************level*************");

            levels.add(level);
            if (nextLevel.stream().anyMatch(Objects::nonNull)) {
                queue.addAll(nextLevel);
            }
        }

        printFormattedTree(levels, maxWidth);
    }

    private static void printFormattedTree(List<List<String>> levels, int maxWidth) {
        for (int i = 0; i < levels.size(); i++) {
            List<String> level = levels.get(i);
            int spaceBetween = maxWidth / (level.size() + 1);

            printSpaces(spaceBetween," ");
            for (String node : level) {
                System.out.print(node);
                printSpaces(spaceBetween," ");
            }
            System.out.println();

            if (i < levels.size() - 1) {
                printBranches(level, maxWidth);
            }
        }
    }

    private static void printBranches(List<String> level, int maxWidth) {
        int spaceBetween = maxWidth / (level.size() + 1);
        printSpaces(spaceBetween / 2," ");

        // Print horizontal lines
        for (String node : level) {
            if (!node.equals(" ")) {
                System.out.print("_".repeat(spaceBetween / 2));
                System.out.print("|");
                System.out.print("_".repeat(spaceBetween / 2));
            } else {
                printSpaces(spaceBetween," ");
            }
        }
        System.out.println();

        printSpaces(spaceBetween / 2," ");
        for (String node : level) {
            if (!node.equals(" ")) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
            printSpaces(spaceBetween-1," ");
        }
        System.out.print("|");
        System.out.println();
    }

    private static void printSpaces(int count, String str) {
        System.out.print(str.repeat(count));
    }

}
