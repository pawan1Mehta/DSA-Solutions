class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;

        if(n <= 3) {
            return false;
        }

        int leftIndex = 1;
        while(leftIndex < n && nums[leftIndex - 1] < nums[leftIndex]) {
            leftIndex++;
        }

        if(leftIndex  == n - 1 || leftIndex == 1) {
            return false;
        }

        int rightIndex = n - 1;
        while(rightIndex >= 1 && nums[rightIndex - 1] < nums[rightIndex]) {
            rightIndex--;
        }

        if(rightIndex == n - 1 || rightIndex < leftIndex) {
            return false;
        }

        boolean result= true;
        for(int i = leftIndex; i <= rightIndex; i++) {
            if(nums[i - 1] <= nums[i]) {
                result = false;
                break;
            }
        }

        return result;
    }
}