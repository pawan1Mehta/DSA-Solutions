class Solution {

    private int findStartNumIndex(int[] nums) {
        int n = nums.length;

        int low = 0, high = n - 1;

        while(low <= high) {
            int mid = (low + high)/2;

            int left = (mid - 1 + n) % n;
            int right = (mid + 1) % n;

            if(nums[left] > nums[mid] && nums[mid] < nums[right]) {
                return mid;
            }

            if(nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    public int findMin(int[] nums) {
        int index = findStartNumIndex(nums);

        return nums[index];
    }
}