class Solution {
    
    public int countMinOperations(int nums[]) {
        int n = nums.length;
        
        int totalOperation = 0;
        int maxDoubleValue = 0;
        
        for(int num : nums) {
            int doubleOperation = 0;
            int temp = num;
            
            while(temp > 0) {
                if(temp%2 == 1) {
                    totalOperation++;
                    temp--;
                }
                if(temp > 0) {
                    doubleOperation++;
                    temp = temp/2;
                }
            }
            
            maxDoubleValue = Math.max(maxDoubleValue, doubleOperation);
        }
        
        int totalOperations = totalOperation + maxDoubleValue;
        
        return totalOperations;
    }
}