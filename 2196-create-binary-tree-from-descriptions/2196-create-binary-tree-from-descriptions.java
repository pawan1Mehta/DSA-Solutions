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
    public TreeNode createBinaryTree(int[][] descriptions) {
        int n = descriptions.length;

        Set<Integer> nodes = new HashSet<>();

        int parent, child, isLeft;
        for(int[] description : descriptions) {
            parent = description[0];
            child = description[1];
            nodes.add(parent);
            nodes.add(child);
        }

        Map<Integer, TreeNode> mp = new HashMap<>();

        for(int[] description : descriptions) {
            parent = description[0];
            child = description[1];
            isLeft = description[2];

            nodes.remove(child);

            TreeNode rootNode = null;
            TreeNode childNode = null;

            if(mp.containsKey(parent)) {
                rootNode = mp.get(parent);
            } else {
                rootNode = new TreeNode(parent);
            }

            if(mp.containsKey(child)) {
                childNode = mp.get(child);
            } else {
                childNode = new TreeNode(child);
            }
            
            if(isLeft == 1) {
                rootNode.left = childNode;
            } else {
                rootNode.right = childNode;
            }

            mp.put(parent, rootNode);
            mp.put(child, childNode);
        }

        return mp.get(nodes.iterator().next());
    }
}