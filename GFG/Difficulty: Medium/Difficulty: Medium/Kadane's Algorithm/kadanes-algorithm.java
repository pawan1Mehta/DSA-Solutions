class Solution {
    int maxSubarraySum(int[] arr) {
        int max_sum_so_far = 0;
        int max_sum = Integer.MIN_VALUE;
        
        for(int num : arr) {
            max_sum_so_far += num;
        
            if(max_sum_so_far < num) {
                max_sum_so_far = num;
            }
            
            if(max_sum_so_far > max_sum) {
                max_sum = max_sum_so_far;
            }
        }
        
        return max_sum;
    }
}
