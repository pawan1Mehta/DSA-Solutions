// FUNCTION CODE
/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
   Node(int item)    {
        data = item;
        left = right = null;
    }
} */

/* Should return minimum distance between a and b
   in a tree with given root*/
class GfG {
    
    Node lca(Node root, int n1, int n2) {
        if(root == null) {
            return null;
        }
        
        if(root.data == n1 || root.data == n2) {
            return root;
        }
        
        Node leftNode = lca(root.left, n1, n2);
        Node rightNode = lca(root.right, n1, n2);
        
        if(leftNode != null && rightNode != null) {
            return root;
        }
        
        return leftNode != null ? leftNode : rightNode;
    }
    
    int depth(Node root, int node) {
        if(root == null) {
            return 0;
        }
        
        if(root.data == node) {
            return 1;
        }
        
        int leftDepth = depth(root.left, node);
        int rightDepth = depth(root.right, node);
        
        if(leftDepth == 0 && rightDepth == 0) {
            return 0;
        }
        
        return 1 + leftDepth + rightDepth;
    }
    
    int findDist(Node root, int a, int b) {
        Node lca = lca(root, a, b);
        
        int nodeaDistanceFromLCA = depth(lca, a);
        int nodebDistanceFromLCA = depth(lca, b);
        
        return (nodeaDistanceFromLCA + nodebDistanceFromLCA) - 2;
    }
}