class Solution {
    public int minimumOperations(int[] nums) {
        int minOperations = 0;
        for(int num : nums) {
            int opt1 = Math.abs(3 - num % 3);
            int opt2 = num % 3;
            minOperations += Math.min(opt1, opt2);
        }
        return minOperations;
    }
}