class Solution {
    
    private int kadanesAlgo(int[] arr) {
        int max_sum_so_far = Integer.MIN_VALUE;
        int max_sum_ending_here = 0;
        
        for(int num : arr) {
            max_sum_ending_here += num;
            
            if(max_sum_ending_here < num) {
                max_sum_ending_here = num;
            }
            
            if(max_sum_so_far < max_sum_ending_here) {
                max_sum_so_far = max_sum_ending_here;
            }
        }
        
        return max_sum_so_far;
    }
    
    public int maxCircularSum(int arr[]) {
        int n = arr.length;
        
        int maxSumSubarray = kadanesAlgo(arr);
        
        int[] rightMaxSum = new int[n];
        rightMaxSum[n - 1] = arr[n - 1];
        int rightSum = arr[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightSum += arr[i];
            rightMaxSum[i] = Math.max(rightSum, rightMaxSum[i + 1]);
        }
        
        int prefixMaxSum = arr[0];
        int prefixSum = 0;
        for(int i = 0; i < n; i++) {
            prefixSum += arr[i];
            if(i + 1 < n) {
                prefixMaxSum = Math.max(
                    prefixMaxSum, 
                    prefixSum + rightMaxSum[i + 1]
                );
            }
        }
        
        return Math.max(prefixMaxSum, maxSumSubarray);
    }
}
