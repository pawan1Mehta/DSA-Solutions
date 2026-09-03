class Solution {
    
    private int getBitwiseMinNum(int num) {
        int targetIndex = 0;
        
        for(int i = 0; i < 32; i++) {
            int mask = 1 << i;
            if((mask & num) == 0) {
                targetIndex = i - 1;
                break;
            }
        }

        if(targetIndex == -1) {
            return -1;
        }

        num = num ^ (1 << targetIndex);

        return num;
    }

    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();

        int[] res = new int[n];

        for(int i = 0; i < n; i++) {
            int nextSmallNum = getBitwiseMinNum(nums.get(i));
            if((nextSmallNum | (nextSmallNum + 1)) == nums.get(i)) {
                res[i] = nextSmallNum;
            } else {
                res[i] = -1;
            }
        }

        return res;
    }
}