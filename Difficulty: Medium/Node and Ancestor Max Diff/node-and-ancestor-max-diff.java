/* Structure of binary tree node
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    int maxDiff = Integer.MIN_VALUE;
    
    int maxDiffUtil(Node root) {
        if(root == null) {
            return Integer.MAX_VALUE;
        }
        
        int left = maxDiffUtil(root.left);
        int right = maxDiffUtil(root.right);
        
        int minNum = Integer.MAX_VALUE;
        minNum = Math.min(minNum, left);
        minNum = Math.min(minNum, right);
        
        if(minNum == Integer.MAX_VALUE) {
            return root.data;
        }
        
        maxDiff = Math.max(maxDiff, root.data - minNum);
        
        return Math.min(minNum, root.data);
    }
    
    int maxDiff(Node root) {
        maxDiffUtil(root);
        return maxDiff;
    }
}