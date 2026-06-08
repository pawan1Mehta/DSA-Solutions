class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;

        int[] res = new int[n];
        int lessI = 0, greaterI = n - 1;

        for(int i = 0, j = n - 1; i < n; i++, j--) {
            if(nums[i] < pivot) {
                res[lessI++] = nums[i];
            }
            if(nums[j] > pivot) {
                res[greaterI--] = nums[j];
            }
        }

        while(lessI <= greaterI) {
            res[lessI] = pivot;
            lessI++;
        }

        return res;
    }
}