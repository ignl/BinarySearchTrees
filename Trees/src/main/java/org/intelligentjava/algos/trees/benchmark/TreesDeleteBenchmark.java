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
public class TreesDeleteBenchmark {
    
    private static final int SIZE = 100000;
    
    private Integer[] randomInts = new Integer[SIZE];
  
    @Setup(Level.Trial)
    public void setup() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < SIZE; i++) {
            randomInts[i] = random.nextInt(SIZE);
        }
    }
    
    @Benchmark
    public Object timeJDKTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < SIZE; i++) {
            treeSet.add(randomInts[i]);
        }

        for (int i = 0; i < SIZE; i++) {
            treeSet.remove(randomInts[i]);
        }
        
        return treeSet.contains(randomInts[0]);
    }
    
    @Benchmark
    public Object timeDeleteRedBlackTree() {
        RedBlackTree rbTree = new RedBlackTree();
        for (int i = 0; i < SIZE; i++) {
            rbTree.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            rbTree.delete(randomInts[i]);
        }
        
        return rbTree.root;
    }

    @Benchmark
    public Object timeDeleteAVLTree() {
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < SIZE; i++) {
            avlTree.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            avlTree.delete(randomInts[i]);
        }
        
        return avlTree.root;
    }

    @Benchmark
    public Object timeDeleteSplayTree() {
        SplayTree splayTree = new SplayTree();
        for (int i = 0; i < SIZE; i++) {
            splayTree.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            splayTree.delete(randomInts[i]);
        }
        
        return splayTree.root;
    }

    @Benchmark
    public Object timeDeleteTreap() {
        Treap treap = new Treap();
        for (int i = 0; i < SIZE; i++) {
            treap.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            treap.delete(randomInts[i]);
        }
        
        return treap.root;
    }

    @Benchmark
    public Object timeDeleteScapegoat0_6Tree() {
        ScapegoatTree scapegoatTree0_6 = new ScapegoatTree(0.6);
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_6.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_6.delete(randomInts[i]);
        }
        
        return scapegoatTree0_6.root;
    }

    @Benchmark
    public Object timeDeleteScapegoat0_75Tree() {
        ScapegoatTree scapegoatTree0_75 = new ScapegoatTree(0.75);
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_75.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_75.delete(randomInts[i]);
        }
        
        return scapegoatTree0_75.root;
    }
    
    @Benchmark
    public Object timeDeleteScapegoat0_9Tree() {
        ScapegoatTree scapegoatTree0_9 = new ScapegoatTree(0.9);
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_9.insert(randomInts[i]);
        }
        
        for (int i = 0; i < SIZE; i++) {
            scapegoatTree0_9.delete(randomInts[i]);
        }
        
        return scapegoatTree0_9.root;
    }
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(".*" + TreesDeleteBenchmark.class.getSimpleName() + ".*").forks(1)
                .build();

        new Runner(opt).run();
    }

}
