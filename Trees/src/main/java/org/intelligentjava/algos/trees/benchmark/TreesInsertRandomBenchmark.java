package org.intelligentjava.algos.trees.benchmark;

import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.intelligentjava.algos.trees.AVLTree;
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

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 1)
@State(Scope.Thread)
public class TreesInsertRandomBenchmark {
    
    private static final int SIZE = 100000;

    private Integer[] randomInts = new Integer[SIZE];
    
    @Setup(Level.Trial)
    public void setup() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < SIZE; i++) {
            randomInts[i] = random.nextInt(SIZE);
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Benchmark
    public Object timeJDKTreeSet() {
        TreeSet tree = new TreeSet();
        for (int i = 0; i < SIZE; i++) {
            tree.add(randomInts[i]);
        }
        return tree.contains(Integer.MAX_VALUE);
    }

    @Benchmark
    public Object timeRedBlackTree() {
        RedBlackTree tree = new RedBlackTree();
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }

    @Benchmark
    public Object timeAVLTree() {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }

    @Benchmark
    public Object timeSplayTree() {
        SplayTree tree = new SplayTree();
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }

    @Benchmark
    public Object timeTreap() {
        Treap tree = new Treap();
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }

    @Benchmark
    public Object timeScapegoat0_6Tree() {
        ScapegoatTree tree = new ScapegoatTree(0.6);
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }
    
    @Benchmark
    public Object timeScapegoat0_75Tree() {
        ScapegoatTree tree = new ScapegoatTree(0.75);
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }
    
    @Benchmark
    public Object timeScapegoat0_9Tree() {
        ScapegoatTree tree = new ScapegoatTree(0.9);
        for (int i = 0; i < SIZE; i++) {
            tree.insert(randomInts[i]);
        }
        return tree.root;
    }
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(".*" + TreesInsertRandomBenchmark.class.getSimpleName() + ".*").forks(1)
                .build();

        new Runner(opt).run();
    }

}
