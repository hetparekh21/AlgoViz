package AlgoViz;

import Algorithms.*;
import java.util.*;

public class BfsTree extends BfsInterface<TreeNode> implements TreeAlgorithm {

    private final String ALGO = "Tree BFS";

    private TreeNode root;
    private TreeNode destination;

    private final HashSet<Integer> visited = new HashSet<>();
    private final Queue<TreeNode> queue;
    private final Scanner sc = new Scanner(System.in);

    private int size;
    private int depth = 0;

    public BfsTree() {
        queue = new LinkedList<>();
    }

    @Override
    public Queue<TreeNode> getQueue() {
        return queue;
    }

    @Override
    public boolean isDestination(TreeNode position) {
        return position.value == destination.value;
    }

    @Override
    public List<TreeNode> getValidNeighbours(TreeNode position) {

        List<TreeNode> list = new ArrayList<>();

        if (position.left != null) list.add(position.left);
        if (position.right != null) list.add(position.right);

        return list;
    }

    @Override
    public boolean isVisited(TreeNode position) {
        return visited.contains(position.value);
    }

    @Override
    public void markVisited(TreeNode position) {
        visited.add(position.value);
    }

    @Override
    public Integer getSleepN() {
        return 500;
    }

    @Override
    public void print() {
        Helper.printTreeMap(getRoot(),depth);
    }

    @Override
    public Scanner getScanner() {
        return sc;
    }

    @Override
    public void initialiseMap() {

        System.out.println("Please enter the map size (keep till 10 for better visuals)");
        this.size = sc.nextInt();

        if (size == 0) {
            throw new RuntimeException("Map size cannot be 0");
        }

        Queue<Integer> nums = new LinkedList<>();

        System.out.println("Would you like to set custom node values ? (1 for yes,0 for no)");
        int choice = sc.nextInt();

        if (choice == 0) {
            for (int i = 0; i < size; i++) {
                nums.add(i + 1);
            }
        } else {
            System.out.println("Enter node values");
            for (int i = 0; i < size; i++) {
                nums.add(sc.nextInt());
            }
        }

        HashSet<Integer> allNums = new HashSet<>(nums);

        TreeNode root = new TreeNode(nums.remove());
        setSource(root);

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nums.isEmpty() && !nodes.isEmpty()) {

            TreeNode temp = nodes.remove();
            depth++;

            TreeNode left = new TreeNode(nums.remove());
            temp.left = left;
            nodes.add(left);

            TreeNode right = new TreeNode();
            if (!nums.isEmpty()) {
                right.value = nums.remove();
                temp.right = right;
                nodes.add(right);
            }


        }

        int destination;
        while (true) {
            System.out.println("Enter destination node : ");
            destination = sc.nextInt();

            if (allNums.contains(destination)) {
                break;
            } else {
                System.out.println("Please enter a valid node");
            }
        }

        setDestination(new TreeNode(destination));

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
        return size;
    }

    @Override
    public void markPath(TreeNode node) {
        node.visited = true;
    }

    @Override
    public void setDestination(TreeNode node) {
        destination = node;
    }

    @Override
    public void setSource(TreeNode node) {
        root = node;
        queue.add(root);
    }

    @Override
    public TreeNode getSource() {
        return root;
    }

    @Override
    public TreeNode getRoot() {
        return this.root;
    }
}