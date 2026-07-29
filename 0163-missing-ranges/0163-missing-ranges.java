class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        int currNum = lower;

        for(int i = 0; i < n; i++) {
            if(currNum != nums[i]) {
                res.add(Arrays.asList(currNum, nums[i] - 1));
                currNum = nums[i];
            }
            currNum++;
        }

        if(currNum <= upper) {
            res.add(Arrays.asList(currNum, upper));
        }

        return res;
    }
}