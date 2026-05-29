/* Structure of binary tree node
class Node{
public:
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
    
    private int min;
    private int max;
    
    private void traverse(Node root, int level, Map<Integer, Integer> sum) {
        if(root == null) {
            return;
        }
        
        min = Math.min(min, level);
        max = Math.max(max, level);
        
        sum.put(level, sum.getOrDefault(level, 0) + root.data);
        
        traverse(root.left, level - 1, sum);
        traverse(root.right, level + 1, sum);
    }
    
    public ArrayList<Integer> verticalSum(Node root) {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
        Map<Integer, Integer> sum = new HashMap<>();
        
        traverse(root, 0, sum);
        
        ArrayList<Integer> res = new ArrayList<>();
        
        for(int num = min; num <= max; num++) {
            res.add(sum.get(num));
        }
        
        return res;
    }
}