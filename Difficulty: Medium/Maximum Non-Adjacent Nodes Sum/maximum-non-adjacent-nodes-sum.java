/*
class Node {
    int data;
    Node left, right;

    Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
*/

class Solution {
    
    private int[] getMaxSumUtil(Node root) {
        if(root == null) {
            return new int[]{0, 0};
        }
        
        int sum1 = 0; // include root node
        int sum2 = 0; // exclude root node
        
        int[] left = getMaxSumUtil(root.left);
        int[] right = getMaxSumUtil(root.right);
        
        sum1 = left[1] + right[1];
        
        sum2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        return new int[]{sum1 + root.data, sum2};
    }
    
    public int getMaxSum(Node root) {
        int[] res = getMaxSumUtil(root);
        return Math.max(res[0], res[1]);
    }
}