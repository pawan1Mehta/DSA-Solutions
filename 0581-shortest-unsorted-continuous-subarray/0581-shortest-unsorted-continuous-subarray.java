class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        int[] sortedArr = Arrays.copyOf(nums, n);
        Arrays.sort(sortedArr);

        int firstMismatchIdx = -1;
        int lastMismatchIdx = -1;

        for(int i = 0; i < n; i++) {
            if(nums[i] != sortedArr[i]) {
                if(firstMismatchIdx == -1) {
                    firstMismatchIdx = i;
                }
                lastMismatchIdx = i;
            }
        }

        if(firstMismatchIdx == -1 && lastMismatchIdx == -1) {
            return 0;
        }

        return lastMismatchIdx - firstMismatchIdx + 1;
    }
}