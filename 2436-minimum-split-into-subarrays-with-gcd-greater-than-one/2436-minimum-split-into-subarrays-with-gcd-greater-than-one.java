class Solution {

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    public int minimumSplits(int[] nums) {
        int n = nums.length;

        int subArrCount = 0;
        int currGCD = nums[0];

        for(int i = 1; i < n; i++) {
            while(i < n && gcd(currGCD, nums[i]) > 1) {
                currGCD = gcd(currGCD, nums[i]);
                i++;
            }

            subArrCount++;
            
            if(i < n) {
                currGCD = nums[i];
            }
        }

        return currGCD == nums[n - 1] ? 1 + subArrCount : subArrCount;
    }
}