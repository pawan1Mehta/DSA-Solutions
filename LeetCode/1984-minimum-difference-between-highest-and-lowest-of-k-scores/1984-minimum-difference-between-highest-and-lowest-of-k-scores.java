class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;

        int i = 0, j = k - 1;
        while(j < n) {
            int diff = nums[j] - nums[i];
            minDiff = Math.min(minDiff, diff);
            i++;
            j++;
        }

        return minDiff;
    }
}