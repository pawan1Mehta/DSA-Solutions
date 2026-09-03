class Solution {

    private int maxDotProductUtil(int i, int j, int[] nums1, int[] nums2, int[][] memo) {
        if(i >= nums1.length || j >= nums2.length) {
            return 0;
        }

        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        int opt1 = nums1[i] * nums2[j] + maxDotProductUtil(i + 1, j + 1, nums1, nums2, memo);
        int opt2 = maxDotProductUtil(i + 1, j, nums1, nums2, memo);
        int opt3 = maxDotProductUtil(i, j + 1, nums1, nums2, memo);

        return memo[i][j] = Math.max(opt1, Math.max(opt2, opt3));
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] memo = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int firstMax = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int secondMin = Integer.MAX_VALUE;
        
        for(int num : nums1) {
            firstMax = Math.max(firstMax, num);
            firstMin = Math.min(firstMin, num);
        }

        for(int num : nums2) {
            secondMax = Math.max(secondMax, num);
            secondMin = Math.min(secondMin, num);
        }

        if(firstMax < 0 && secondMin > 0) {
            return firstMax * secondMin;
        }

        if(firstMin > 0 && secondMax < 0) {
            return firstMin * secondMax;
        }

        return maxDotProductUtil(0, 0, nums1, nums2, memo);
    }
}