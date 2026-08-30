/*
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    public Node delNode(Node root, int x) {
        if(root == null) {
            return null;
        }
        
        if(x < root.data) {
            root.left = delNode(root.left, x);
        } else if(x > root.data) {
            root.right = delNode(root.right, x);
        } else {
            if(root.left == null && root.right == null) {
                return null;
            } else if(root.left != null && root.right == null) {
                return root.left;
            } else if(root.left == null && root.right != null) {
                return root.right;
            }
            
            Node nextNode = getMinValueNode(root.right);
            root.data = nextNode.data;
            root.right = delNode(root.right, nextNode.data);
        }
        
        return root;
    }
    
    private Node getMinValueNode(Node root) {
        if(root == null) {
            return null;
        }
        
        while(root.left != null) {
            root = root.left;
        }
        
        return root;
    }
}