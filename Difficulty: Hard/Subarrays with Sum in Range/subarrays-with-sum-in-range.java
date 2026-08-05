class Solution {
    
    private long subarraysLessThanK(int[] nums, long k) {
        int n = nums.length;
        
        long count = 0;
        
        int i = 0, j = 0;
        long sum = 0;
        
        while(j < n) {
            sum += nums[j];
            
            while(sum >= k) {
                sum -= nums[i];
                i++;
            }
            
            count += (j - i) + 1;
            
            j++;
        }
        
        return count;
    }
    
    public int countSubarray(int[] arr, int l, int r) {
        long total = subarraysLessThanK(arr, r + 1);
        long lessThanL = subarraysLessThanK(arr, l);
        return (int) (total - lessThanL);
    }
}