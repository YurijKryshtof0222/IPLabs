package Lab1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Перехідний стан системи 4 -> 5");

        TreeSet<Integer> p5 = new TreeSet<>();
        p5.add(5);

        FoldMatrix p4_p3 = new FoldMatrix(Constants.folds1, p5, "P4", "P3", "P5");
        FoldMatrix p2_k2 = new FoldMatrix(Constants.folds2, p4_p3.getFirst(), "P2", "K2", "P4");
        FoldMatrix p1_k3 = new FoldMatrix(Constants.folds3, p4_p3.getSecond(), "P1", "K3", "P3");
        FoldMatrix k4_k3 = new FoldMatrix(Constants.folds4, p2_k2.getFirst(), "K4", "K3", Constants.costs[3], Constants.costs[2],"P2");
        FoldMatrix k2_k1 = new FoldMatrix(Constants.folds5, p1_k3.getFirst(), "K2", "K1", Constants.costs[1], Constants.costs[0], "P1" );

        p1_k3.setFirstInputMatrix(k2_k1.values());
        p1_k3.setSecondInputMatrix(Constants.costs[2]);
        p2_k2.setFirstInputMatrix(k4_k3.values());
        p2_k2.setSecondInputMatrix(Constants.costs[1]);
        p4_p3.setFirstInputMatrix(p2_k2.values());
        p4_p3.setSecondInputMatrix(p1_k3.values());

        p4_p3.printMatrix();
        p2_k2.printMatrix();
        p1_k3.printMatrix();
        k4_k3.printMatrix();
        k2_k1.printMatrix();

        System.out.println();
        k2_k1.printCellAndValueList();
        k4_k3.printCellAndValueList();
        p1_k3.printCellAndValueList();
        p2_k2.printCellAndValueList();
        p4_p3.printCellAndValueList();

        Cell min_p5 = p4_p3.findFirstMinCell();
        Cell min_p4 = p2_k2.findCellAtIndex(min_p5.getI());
        Cell min_p3 = p1_k3.findCellAtIndex(min_p5.getJ());
        Cell min_p2 = k4_k3.findCellAtIndex(min_p4.getI());
        Cell min_p1 = k2_k1.findCellAtIndex(min_p3.getI());
    }

}