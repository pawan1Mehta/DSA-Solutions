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
    private int depth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    private TreeNode findSubtreeWithDeepestNode(TreeNode root, int depth, int maxDepth) {
        if(root == null) {
            return null;
        }

        if(depth == maxDepth) {
            return root;
        }

        TreeNode left = findSubtreeWithDeepestNode(root.left, depth + 1, maxDepth);
        TreeNode right = findSubtreeWithDeepestNode(root.right, depth + 1, maxDepth);
        
        if(left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int maxDepth = depth(root);
        return findSubtreeWithDeepestNode(root, 1, maxDepth);
    }   
}