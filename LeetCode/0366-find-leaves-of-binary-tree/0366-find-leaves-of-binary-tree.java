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

    private void addLeaveNodes(TreeNode root, TreeNode parentNode, int isParentLeft, List<Integer> list) {
        if(root == null) {
            return;
        }

        if(parentNode != null && root.left == null && root.right == null) {
            list.add(root.val);

            if(isParentLeft == 1) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        }

        addLeaveNodes(root.left, root, 1, list);
        addLeaveNodes(root.right, root, 2, list);
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        while(root != null && (root.left != null || root.right != null)) {
            List<Integer> list = new ArrayList<>();
            addLeaveNodes(root, null, -1, list);
            res.add(list);
        }

        res.add(Arrays.asList(root.val));

        return res;
    }
}