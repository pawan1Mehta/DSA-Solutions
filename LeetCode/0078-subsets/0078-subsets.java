class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;

        List<List<Integer>> subsetsRes = new ArrayList<>();
        
        for(int num = 0; num < Math.pow(2, n); num++) {

            ArrayList<Integer> subset = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                if((num & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }   
            }

            subsetsRes.add(subset);
        }

        return subsetsRes;
    }
}