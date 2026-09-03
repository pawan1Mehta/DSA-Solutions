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
    private int[] robUtil(TreeNode root) {
        if(root == null) {
            return new int[]{0, 0};
        }

        int[] left = robUtil(root.left);
        int[] right = robUtil(root.right);

        int robRoot = root.val + left[1] + right[1];
        int robNextChild = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{robRoot,  robNextChild};
    }

    public int rob(TreeNode root) {
        int[] res = robUtil(root);
        return Math.max(res[0], res[1]);
    }
}