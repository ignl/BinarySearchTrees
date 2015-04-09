package org.intelligentjava.algos.trees.benchmark;

import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.intelligentjava.algos.trees.AVLTree;
import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.intelligentjava.algos.trees.RedBlackTree;
import org.intelligentjava.algos.trees.ScapegoatTree;
import org.intelligentjava.algos.trees.SplayTree;
import org.intelligentjava.algos.trees.Treap;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("unchecked")
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 1)
@State(Scope.Thread)
public class TreesSearchBenchmark {
    
    private static final int SIZE = 100000;
    @SuppressWarnings("rawtypes")
    private TreeSet treeSet = new TreeSet();
    private RedBlackTree rbTree = new RedBlackTree();
    private AVLTree avlTree = new AVLTree();
    private SplayTree splayTree = new SplayTree();
    private Treap treap = new Treap();
    private ScapegoatTree scapegoatTree0_6 = new ScapegoatTree(0.6);
    private ScapegoatTree scapegoatTree0_75 = new ScapegoatTree(0.75);
    private ScapegoatTree scapegoatTree0_9 = new ScapegoatTree(0.9);
    private Integer[] randomInts = new Integer[SIZE];
    
    @Setup(Level.Trial)
    public void setup() {
        Random random = new Random(System.currentTimeMillis());
        for (Integer i = 0; i < SIZE; i++) {
            randomInts[i] = random.nextInt(SIZE);
        }
        for (int i = 0; i < SIZE; i++) {
            rbTree.insert(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            treeSet.add(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            avlTree.insert(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            splayTree.insert(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            treap.insert(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_6.insert(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_75.insert(randomInts[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_9.insert(randomInts[i]);
        }
    }
    
    
    @Benchmark
    public boolean timeJDKTreeSet() {
        boolean[] booleans = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++) {
            booleans[i] = treeSet.contains(randomInts[i]);
        }
        return booleans[0];
    }
    
    @Benchmark
    public Node timeSearchRedBlackTree() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = rbTree.search(randomInts[i]);
        }
        return nodes[0];
    }

    @Benchmark
    public Node timeSearchAVLTree() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = avlTree.search(randomInts[i]);
        }
        return nodes[0];
    }

    @Benchmark
    public Node timeSearchSplayTree() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = splayTree.search(randomInts[i]);
        }
        return nodes[0];
    }

    @Benchmark
    public Node timeSearchTreap() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = treap.search(randomInts[i]);
        }
        return nodes[0];
    }

    @Benchmark
    public Node timeSearchScapegoat0_6Tree() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = scapegoatTree0_6.search(randomInts[i]);
        }
        return nodes[0];
    }

    @Benchmark
    public Node timeSearchScapegoat0_75Tree() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = scapegoatTree0_75.search(randomInts[i]);
        }
        return nodes[0];
    }
    
    @Benchmark
    public Node timeSearchScapegoat0_9Tree() {
        Node[] nodes = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodes[i] = scapegoatTree0_9.search(randomInts[i]);
        }
        return nodes[0];
    }
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(".*" + TreesDeleteBenchmark.class.getSimpleName() + ".*").forks(1)
                .build();

        new Runner(opt).run();
    }

}
