class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIdx = 0, maxIdx = 0;
        for(int i = 0; i < n; i++) {
            if(nums[minIdx] > nums[i]) {
                minIdx = i;
            }
            if(nums[maxIdx] < nums[i]) {
                maxIdx = i;
            }
        }

        int count = 0;

        if((minIdx + 1) < (n - minIdx)) {
            count += (minIdx + 1);
        } else {
            count += (n - minIdx);
        }

        if((maxIdx + 1) < (n - maxIdx)) {
            count += (maxIdx + 1);
        } else {
            count += (n - maxIdx);
        }

        if(minIdx < maxIdx) {
            count = Math.min(count, maxIdx + 1);
            count = Math.min(count, n - minIdx);
        } else {
            count = Math.min(count, minIdx + 1);
            count = Math.min(count, n - maxIdx);
        }

        return count;
    }
}