class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;

        int[] res = new int[n];
        int indx = 0;

        // less than pivot
        for(int i = 0; i < n; i++) {
            if(nums[i] < pivot) {
                res[indx++] = nums[i];
            }
        }

        // equal to pivot
        for(int i = 0; i < n; i++) {
            if(nums[i] == pivot) {
                res[indx++] = nums[i];
            }
        }

        // greater than pivot
        for(int i = 0; i < n; i++) {
            if(nums[i] > pivot) {
                res[indx++] = nums[i];
            }
        }

        return res;
    }
}