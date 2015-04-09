package org.intelligentjava.algos.trees.main;

import java.util.Random;

import org.intelligentjava.algos.trees.RedBlackTree;

public class Main {
    
    private static final int SIZE = 10000;

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        long currentTimeMillis = System.currentTimeMillis();
        RedBlackTree tree = new RedBlackTree();
        for (int i = 1; i < SIZE; i++) {
            tree.insert(random.nextInt(SIZE));
        }
        for (int i = 1; i < SIZE / 2; i++) {
            tree.delete(random.nextInt(SIZE));
        }
        
        System.out.println(System.currentTimeMillis() - currentTimeMillis + " ms");
    }

}
