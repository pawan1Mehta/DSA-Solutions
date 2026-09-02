class Solution {

    private int comp(int a, int b) {
        return (a > b ? 1 : 0) - (a < b ? 1 : 0);
    }

    public int longestAlternating(int[] nums) {
        int n = nums.length;

        int[] left = new int[n];
        Arrays.fill(left, 1);

        for(int i = 1; i < n; i++) {
            int diff = comp(nums[i], nums[i - 1]);
            if(diff != 0) {
                if(i > 1 && comp(nums[i - 1], nums[i - 2]) == -diff) {
                    left[i] = left[i - 1] + 1;
                } else {
                    left[i] = 2;
                }
            }
        }

        int[] right = new int[n];
        Arrays.fill(right, 1);

        for(int i = n -2; i >= 0; i--) {
            int diff = comp(nums[i + 1], nums[i]);
            if(diff != 0) {
                if(i < n - 2 && comp(nums[i + 2], nums[i + 1]) == -diff) {
                    right[i] = right[i + 1] + 1;
                } else {
                    right[i] = 2;
                }
            }
        }

        int maxLen = 0;
        for(int len : left) {
            maxLen = Math.max(maxLen, len);
        }

        for(int i = 1; i < n - 1; i++) {
            int diff = comp(nums[i + 1], nums[i - 1]);
            if(diff != 0) {
                int L = (i > 1 && comp(nums[i - 1], nums[i - 2]) == -diff) ? left[i - 1] : 1;
                int R = (i < n - 2 && comp(nums[i + 2], nums[i + 1]) == -diff) ? right[i + 1] : 1;
                maxLen = Math.max(maxLen, L + R);
            }
        }

        return maxLen;
    }
}