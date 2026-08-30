class Solution {
    public int maxArea(List<Integer> height) {
        int n = height.size();
        
        int res = 0;
        int i = 0, j = n-1;
        
        while(i < j) {
            int currArea = Math.min(height.get(i), height.get(j)) * (j - i -1);
            res = Math.max(res, currArea);
            
            if(height.get(i) < height.get(j)) {
                i++;
            } else {
                j--;
            }
        }
        
        return res;
    }
}