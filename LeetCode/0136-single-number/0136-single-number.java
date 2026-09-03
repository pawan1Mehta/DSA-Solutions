class Solution {
    public int singleNumber(int[] nums) {
        int resNum = 0;
        
        for(int num : nums) {
            resNum = resNum ^ num;
        }

        return resNum;
    }
}