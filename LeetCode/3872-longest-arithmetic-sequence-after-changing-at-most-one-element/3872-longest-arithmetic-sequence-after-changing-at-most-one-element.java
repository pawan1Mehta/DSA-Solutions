class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;

        if(n <= 2) {
            return n;
        }

        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left, 2);
        Arrays.fill(right, 2);
        
        for(int i = 2; i < n; i++) {
            if((nums[i - 1] - nums[i - 2]) == (nums[i] - nums[i - 1])) {
                left[i] = left[i - 1] + 1;
            }
        }
        for(int i = n - 3; i >= 0; i--) {
            if((nums[i + 2] - nums[i + 1]) == (nums[i + 1] - nums[i])) {
                right[i] = right[i + 1] + 1;
            }
        }

        int maxSubLength = 2;

        for(int i = 0; i < n; i++) {
            maxSubLength = Math.max(maxSubLength, Math.max(left[i], right[i]));

            if(i == 0) {
                maxSubLength = Math.max(maxSubLength, right[i + 1] + 1);
            } else if(i == n - 1) {
                maxSubLength = Math.max(maxSubLength, left[i - 1] + 1);
            } else {
                maxSubLength = Math.max(maxSubLength, 1 + left[i - 1]);
                maxSubLength = Math.max(maxSubLength, 1 + right[i + 1]);

                int diff = nums[i + 1] - nums[i - 1];
                if(diff%2 == 0) {
                    int reqDiff = diff/2;

                    int leftMax = 1, rightMax = 1;

                    if(i >= 2 && (nums[i - 1] - nums[i - 2]) == reqDiff) {
                        leftMax = left[i - 1];
                    }

                    if(i < n - 2 && (nums[i + 2] - nums[i + 1]) == reqDiff) {
                        rightMax = right[i + 1];
                    }

                    maxSubLength = Math.max(maxSubLength, 1 + leftMax + rightMax);
                }
            }
        }

        return maxSubLength;
    }
}