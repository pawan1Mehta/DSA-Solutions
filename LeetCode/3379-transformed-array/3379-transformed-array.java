class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                res[i] = nums[(i + nums[i])%n];
            } else if(nums[i] < 0) {
                int targetIndex = i - (Math.abs(nums[i]) % n);
                if(targetIndex < 0) {
                    targetIndex = n - Math.abs(targetIndex);
                }
                res[i] = nums[targetIndex];
            } else {
                res[i] = nums[i];
            }
        }

        return res;
    }
}
/**

    [3,-2,1,1]
     0  1 2 3
     |
    [1, ]

    nums[(i + nums[i]) % n]
    nums[n - ((i - abs(nums[i])) % n)]


 */