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
    public static int MOD = 1_000_000_007;

    private long maxProductSum;

    private long sum(TreeNode root) {
        if(root == null) {
            return 0;
        }    

        long leftSum = sum(root.left);
        long rightSum = sum(root.right);

        return leftSum + rightSum + root.val;
    }

    private long calculateMaxProductSum(TreeNode root, long totalSum) {
        if(root == null) {
            return 0;
        }

        long leftSum = calculateMaxProductSum(root.left, totalSum);
        long rightSum = calculateMaxProductSum(root.right, totalSum);

        long currRootSum = leftSum + rightSum + root.val;

        maxProductSum = Math.max(maxProductSum, (currRootSum * Math.abs(totalSum - currRootSum))) ;

        return currRootSum;
    }

    public int maxProduct(TreeNode root) {
        long totalSum = sum(root);
        
        maxProductSum = 0;
        
        calculateMaxProductSum(root, totalSum);

        return (int) (maxProductSum % MOD);
    }
}