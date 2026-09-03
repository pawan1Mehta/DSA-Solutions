class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;

        int low = 0, high = n - 1;

        while(low < high) {
            int mid = (low + high)/2;

            int left = (mid - 1 + n) % n;
            int right = (mid + 1) % n;

            if(nums[left] > nums[mid] && nums[mid] < nums[right]) {
                return nums[mid];
            }

            if(nums[mid] < nums[high]) {
                high = mid;
            } else if(nums[mid] > nums[high]){
                low = mid + 1;
            } else {
                high--;
            }
        }

        return nums[high];
    }
}