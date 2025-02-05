package BST_DFS;

public class FindMaxDepth_2 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3, new TreeNode(9, null, null),
                new TreeNode(20, new TreeNode(15, null, null),
                        new TreeNode(7, null, null)));
        FindMaxDepth_2 solution = new FindMaxDepth_2();
        System.out.println(
                solution.maxDepth(treeNode));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }
}
