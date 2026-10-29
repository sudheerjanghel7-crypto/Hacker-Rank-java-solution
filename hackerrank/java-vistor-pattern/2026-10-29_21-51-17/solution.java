import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sum = 0;
    public int getResult() {
      	//implement this
        return sum;
    }

    public void visitNode(TreeNode node) {
      	//implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long product = 1;
    private static final int MOD = 1000000007;
    public int getResult() {
      	//implement this
        return (int) product;
    }

    public void visitNode(TreeNode node) {
      	//implement this
        if (node.getColor() == Color.RED) {
            product = (product * node.getValue()) % MOD;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
        if (leaf.getColor() == Color.RED) {
            product = (product * leaf.getValue()) % MOD;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int evenDepthNonLeafSum = 0;
    private int greenLeafSum = 0;
    public int getResult() {
      	//implement this
        return Math.abs(evenDepthNonLeafSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
    	//implement this
         if (node.getDepth() % 2 == 0) {
            evenDepthNonLeafSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
    	//implement this
         if (leaf.getColor() == Color.GREEN) {
            greenLeafSum += leaf.getValue();
        }
    }
}

public class Solution {
  
    
public static Tree solve() {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int[] values = new int[n];
    int[] colors = new int[n];

    for (int i = 0; i < n; i++) {
        values[i] = sc.nextInt();
    }

    for (int i = 0; i < n; i++) {
        colors[i] = sc.nextInt();
    }

    ArrayList<Integer>[] adj = new ArrayList[n];

    for (int i = 0; i < n; i++) {
        adj[i] = new ArrayList<Integer>();
    }

    for (int i = 0; i < n - 1; i++) {
        int u = sc.nextInt() - 1;
        int v = sc.nextInt() - 1;

        adj[u].add(v);
        adj[v].add(u);
    }

    Tree[] nodes = new Tree[n];
    int[] depth = new int[n];
    int[] parent = new int[n];
    Arrays.fill(parent, -1);

    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(0);
    parent[0] = 0;

    while (!queue.isEmpty()) {
        int u = queue.poll();

        for (int v : adj[u]) {
            if (parent[v] == -1) {
                parent[v] = u;
                depth[v] = depth[u] + 1;
                queue.add(v);
            }
        }
    }

    for (int i = 0; i < n; i++) {
        Color color = colors[i] == 0 ? Color.RED : Color.GREEN;

        if (adj[i].size() == 1 && i != 0) {
            nodes[i] = new TreeLeaf(values[i], color, depth[i]);
        } else {
            nodes[i] = new TreeNode(values[i], color, depth[i]);
        }
    }

    for (int i = 1; i < n; i++) {
        ((TreeNode) nodes[parent[i]]).addChild(nodes[i]);
    }

    return nodes[0];
}

    


    public static void main(String[] args) {
      	Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
	}
}