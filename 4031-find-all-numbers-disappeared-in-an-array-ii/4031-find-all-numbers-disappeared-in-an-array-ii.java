class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        int n = nums.length;

        Arrays.sort(nums);

        int left = 0, right = n - 1;
        while(left < n && lower > nums[left]) {
            left++;
        }
        while(right >= 0 && upper < nums[right]) {
            right--;
        }

        List<List<Integer>> res = new ArrayList<>();

        for(int i = left; i <= right; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            if(lower != nums[i]) {
                res.add(Arrays.asList(lower, nums[i] - 1));
            }
            lower = nums[i] + 1;
        }

        if(lower <= upper) {
            res.add(Arrays.asList(lower, upper));
        } 

        return res;
    }
}