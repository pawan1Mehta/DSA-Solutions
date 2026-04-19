class Solution {
    
    private int findMaxIndex(int[] nums, int targetNum, int i) {
        int n = nums.length;

        int l = 0, r = n - 1;
        int j = i;

        while(l <= r) {
            int mid = (l + r)/2;

            if(nums[mid] >= targetNum) {
                j = Math.max(j, mid);
                l = mid + 1;
            } else if(mid < i) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return j;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int maxDist = -1;

        for(int i = 0; i < n; i++) {
            int j = findMaxIndex(nums2, nums1[i], i);
            maxDist = Math.max(maxDist, j - i);
        }

        return maxDist;
    }
}