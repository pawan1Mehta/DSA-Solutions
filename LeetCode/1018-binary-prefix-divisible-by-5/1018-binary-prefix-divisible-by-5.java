class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();

        long currNum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                currNum = currNum | 1;
            }
    
            if(currNum % 5 == 0){
                res.add(true);
            } else {
                res.add(false);
            }

            currNum = currNum%5;

            currNum = currNum << 1;
        }

        return res;
    }
}