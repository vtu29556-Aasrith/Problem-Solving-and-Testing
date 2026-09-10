import java.io.*;
import java.util.*;

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

abstract class TreeVis {
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);
}

class SumInLeavesVisitor extends TreeVis {
    private int result = 0;

    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
        // Non-leaf nodes ignored
    }

    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long result = 1;
    private final int MOD = 1000000007;

    public int getResult() {
        return (int) result;
    }

    public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            result = (result * node.getValue()) % MOD;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            result = (result * leaf.getValue()) % MOD;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int nonLeafEvenDepthSum = 0;
    private int greenLeavesSum = 0;

    public int getResult() {
        return Math.abs(nonLeafEvenDepthSum - greenLeavesSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            nonLeafEvenDepthSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            greenLeavesSum += leaf.getValue();
        }
    }
}

public class Solution {
    private static int[] values;
    private static Color[] colors;
    private static ArrayList<Integer>[] adj;

    public static Tree solve() {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();

        values = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = scanner.nextInt();
        }

        colors = new Color[n + 1];
        for (int i = 1; i <= n; i++) {
            colors[i] = (scanner.nextInt() == 0) ? Color.RED : Color.GREEN;
        }

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        if (n == 1) {
            return new TreeLeaf(values[1], colors[1], 0);
        }

        return createTree(1, 0, 0);
    }

    private static Tree createTree(int node, int parent, int depth) {
        boolean isLeaf = true;
        ArrayList<Tree> children = new ArrayList<>();

        for (int neighbor : adj[node]) {
            if (neighbor != parent) {
                isLeaf = false;
                children.add(createTree(neighbor, node, depth + 1));
            }
        }

        if (isLeaf) {
            return new TreeLeaf(values[node], colors[node], depth);
        } else {
            TreeNode treeNode = new TreeNode(values[node], colors[node], depth);
            for (Tree child : children) {
                treeNode.addChild(child);
            }
            return treeNode;
        }
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

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024 * 64];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private int readByte() {
            return hasNextByte() ? buffer[ptr++] : -1;
        }

        public int nextInt() {
            int b = readByte();
            while (b <= 32) b = readByte();
            int res = 0;
            while (b > 32) {
                res = res * 10 + b - '0';
                b = readByte();
            }
            return res;
        }
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna