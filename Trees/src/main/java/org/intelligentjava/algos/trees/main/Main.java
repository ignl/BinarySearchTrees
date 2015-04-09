package org.intelligentjava.algos.trees.main;

import java.util.Random;

import org.intelligentjava.algos.trees.RedBlackTree;

public class Main {

    private static final int SIZE = 100;

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        Integer[] randomInts = new Integer[SIZE];
        for (int i = 0; i < SIZE; i++) {
            randomInts[i] = random.nextInt(SIZE);
        }

        long currentTimeMillis = System.currentTimeMillis();
        RedBlackTree tree = new RedBlackTree();
        for (int i = 1; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }

        for (int i = 1; i < SIZE / 2; i++) {
            tree.delete(randomInts[i]);
        }
        
        tree.printTree();

        System.out.println(System.currentTimeMillis() - currentTimeMillis + " ms");
    }

}
