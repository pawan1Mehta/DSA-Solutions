class Solution {
    public int reverseBits(int n) {
        int revNum = 0;
        
        for(int i = 0, j = 31; j >= 0; i++, j--) {
            int mask = 1 << i;
            if((n & mask) != 0) {
                revNum = revNum | (1 << j);
            }
        }

        return revNum;
    }
}