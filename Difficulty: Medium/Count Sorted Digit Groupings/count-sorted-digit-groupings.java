class Solution {
    
    int[][] memo;
    
    private int validGroupsUtil(int index, int prevSum, int[] nums) {
        if(index == nums.length) {
            return 1;
        }
        
        if(memo[index][prevSum] != -1) {
            return memo[index][prevSum];
        }
        
        int count = 0;
        int currSum = 0;
        
        for(int i = index; i < nums.length; i++) {
            currSum += nums[i];
            
            if(prevSum <= currSum) {
                count += validGroupsUtil(i + 1, currSum, nums);
            }
        }
        
        return memo[index][prevSum] = count;
    }
    
    public int validGroups(String s) {
        int n = s.length();
        
        memo = new int[n][1001];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        int[] nums = new int[n];
        
        for(int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - '0'; 
        }
        
        return validGroupsUtil(0, 0, nums);
    }
}