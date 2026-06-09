/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int res = Integer.MIN_VALUE;

    private int maxPathSumUtil(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = Math.max(maxPathSumUtil(root.left), 0);
        int right = Math.max(maxPathSumUtil(root.right), 0);

        res = Math.max(res, root.val + left + right);

        return Math.max(root.val + left, root.val + right);
    }

    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return res;
    }
}