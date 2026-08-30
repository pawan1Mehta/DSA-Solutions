/*
class Node {
    int data;
    Node left, right;
    Node(int item){
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    ArrayList<Integer> preOrder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        
        Stack<Node> st = new Stack<>();
        Node currNode = root;
        
        while(currNode != null) {
            while(currNode != null) {
                res.add(currNode.data);

                if(currNode.right != null) {
                    st.push(currNode.right);
                }

                currNode = currNode.left;
            }

            currNode = st.isEmpty() ? null : st.pop();
        }
        
        return res;
    }   
}