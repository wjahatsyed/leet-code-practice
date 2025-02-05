package BST_DFS;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {
    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(3, new TreeNode(5, new TreeNode(6, null, null),
                new TreeNode(2, new TreeNode(7, null, null), new TreeNode(4, null, null))
        ), new TreeNode(1, new TreeNode(9,null, null), new TreeNode(8, null, null)));

       TreeNode root2 = new TreeNode(3, new TreeNode(5, new TreeNode(6, null, null), new TreeNode(7, null, null)),
               new TreeNode(1, new TreeNode(4, null, null), new TreeNode(2, new TreeNode(9, null, null), new TreeNode(8, null, null))));

       LeafSimilarTrees leafSimilarTrees = new LeafSimilarTrees();
       System.out.println(
       leafSimilarTrees.leafSimilar(root1, root2));
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> seq1 = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();

        generateLeafSequence(root1, seq1);

        generateLeafSequence(root2, seq2);

        // Compare sequences
        if (seq1.size() != seq2.size())
            return false;

        for (int i = 0; i < seq1.size(); i++)
            if (!seq1.get(i).equals(seq2.get(i)))
                return false; // Fixed comparison

        return true;
    }

    private void generateLeafSequence(TreeNode root, List<Integer> sequence) {
        if (root == null)
            return;

        // Check if the current node is a leaf
        if (root.left == null && root.right == null) {
            sequence.add(root.val);
            return;
        }

        // Recursively traverse left and right subtrees
        generateLeafSequence(root.left, sequence);
        generateLeafSequence(root.right, sequence);
    }
}
