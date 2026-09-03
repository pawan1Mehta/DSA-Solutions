class Solution {
    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b%a, a);
    }

    public int minOperations(int[] nums) {
        int n = nums.length;

        int count1 = 0;
        int tempGCd = 0;

        for(int num : nums) {
            if(num == 1) {
                count1++;
            }
            tempGCd = gcd(tempGCd, num);
        }

        if(count1 > 0) {
            return n - count1;
        }

        if(tempGCd > 1) {
            return -1;
        }

        int minLen = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            tempGCd = 0;
            for(int j = i; j < n; j++) {
                tempGCd = gcd(tempGCd, nums[j]);
                if(tempGCd == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                }
            }
        }

        return minLen + n - 2;
    }
}