package Algorithms;

import java.util.Scanner;

public abstract class Algorithm<T> {

    final void printDetails() {

        String time = "Time Complexity : " + Helper.ALGO_COLOR
                + "O("
                + getTimeComplexity()
                + ")"
                + Color.RESET;

        System.out.println("Algo : " + Helper.ALGO_COLOR + getAlgoName() + Color.RESET + "\n" + time);
        System.out.println();
    }

    public abstract void print();

    public final void printMap(){
        Helper.clearConsole();
        printDetails();
        print();
    }

    public abstract Integer getSleepN();

    public abstract Scanner getScanner();

    public abstract void initialiseMap();

    public abstract int getVisitedCount();

    public abstract String getAlgoName();

    public abstract String getTimeComplexity();

    public abstract int getTotalNodes();

    public abstract void markPath(T node);

    public abstract void setDestination(T node);

    public abstract void setSource(T node);

    public abstract T getSource();

    public final void start() {
        initialiseMap();
        System.out.println("Press ENTER to start");
        getScanner().nextLine();
        getScanner().nextLine();
        algo();
    }

    public abstract void algo();

    public final void end(){
        printMap();
        System.out.println();
        System.out.println("Total Nodes : " + getTotalNodes());
        System.out.println("Visited Nodes : " + getVisitedCount());
    }

}
