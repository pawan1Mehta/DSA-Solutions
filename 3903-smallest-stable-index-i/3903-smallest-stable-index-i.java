class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] maxLeft = new int[n];
        int[] minRight = new int[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                maxLeft[i] = nums[i];
            } else {
                maxLeft[i] = Math.max(maxLeft[i - 1], nums[i]);
            }
        }
        
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                minRight[i] = nums[i];
            } else {
                minRight[i] = Math.min(minRight[i + 1], nums[i]);
            }
        }

        for(int i = 0; i < n; i++) {
            int max = maxLeft[i];
            int min = minRight[i];

            if((max - min) <= k) {
                return i;
            }
        }
        
        return -1;
    }
}