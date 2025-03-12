package Algorithms;

import Algorithms.Helper;

import java.util.List;

public abstract class DfsInterface<T> extends Algorithm<T> {

    private final String TIME_COMPLEXITY = "n";

    public abstract boolean isDestination(T position);

    public abstract List<T> getValidNeighbours(T position);

    public abstract boolean isVisited(T position);

    public abstract void markVisited(T position);

    public abstract void print();

    public abstract void markFound();

    public abstract boolean isFound();

    @Override
    public final void algo() {
        this.dfs(getSource());
    }

    @Override
    public final String getTimeComplexity(){
        return TIME_COMPLEXITY;
    }

    private void dfs(T source){

        if(isFound()){
            return;
        }

        Helper.sleep(getSleepN());

        markVisited(source);
        markPath(source);

        printMap();

        List<T> neighbours = getValidNeighbours(source);

        for(T pos : neighbours){
            if(isDestination(pos)){
                markFound();
                markPath(pos);
                return;
            }
        }

        for(T pos : neighbours){
            dfs(pos);
            if (isFound()){
                return;
            }
        }

    }

}
