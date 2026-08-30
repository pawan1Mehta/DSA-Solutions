class Solution {
    int maxSubstring(String s) {
        int n = s.length();
        
        int[] nums = new int[n];
        
        for(int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) == '1' ? -1 : 1;
        }
        
        int max_sum = 0;
        int sum_so_far = 0;
        
        for(int num : nums) {
            sum_so_far += num;
            
            if(sum_so_far < 0) {
                sum_so_far = 0;
            }
            
            if(sum_so_far > max_sum) {
                max_sum = sum_so_far;
            }
        }
        
        return max_sum == 0 ? -1 : max_sum;
    }
}