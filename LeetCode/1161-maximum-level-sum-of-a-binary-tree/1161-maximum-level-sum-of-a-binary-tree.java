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
    private void maxLevelSumUtil(TreeNode root, int level, Map<Integer, Integer> sum) {
        if(root == null) {
            return;
        }

        sum.put(level, sum.getOrDefault(level, 0) + root.val);

        maxLevelSumUtil(root.left, level + 1, sum);
        maxLevelSumUtil(root.right, level + 1, sum);
    }

    public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> sum = new HashMap<>();

        maxLevelSumUtil(root, 1, sum);

        int maxSum = Integer.MIN_VALUE;
        int maxLevelSum = 0;

        for(Map.Entry<Integer, Integer> data : sum.entrySet()) {
            if(data.getValue() > maxSum) {
                maxSum = data.getValue();
                maxLevelSum = data.getKey();
            }
        }

        return maxLevelSum;
    }
}