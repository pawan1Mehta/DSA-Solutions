// User function Template for Java

/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
*/

class Tree {
    ArrayList<Integer> postOrder(Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        
        Stack<Node> st = new Stack<>();
        Node currNode = node;
        
        while(currNode != null) {
            
            while(currNode != null) {
                res.add(currNode.data);
                if(currNode.left != null) {
                    st.push(currNode.left);
                }
                currNode = currNode.right;
            }
            
            currNode = st.isEmpty() ? null : st.pop();
        }
        
        Collections.reverse(res);
        
        return res;
    }
}