class Solution {

    private int findStartIndex(int[] nums) {
        int n = nums.length;

        int low = 0, high = n - 1;

        while(low <= high) {
            int mid = (low + high)/2;

            int left = ((mid - 1) + n)%n;
            int right = (mid + 1)%n;

            if(nums[left] > nums[mid] && nums[mid] < nums[right]) {
                return mid;
            }

            if(nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        while(low <= high) {
            int mid = (low + high)/2;

            if(nums[mid] == target) {
                return mid;
            }

            if(nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;

        if(n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int startIndex = findStartIndex(nums);
        int idx = binarySearch(nums, 0, startIndex - 1, target);
        if(idx == -1) {
            idx = binarySearch(nums, startIndex, n - 1, target);
        }

        return idx;
    }
}