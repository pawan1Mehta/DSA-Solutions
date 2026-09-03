class Solution {
    public int binaryGap(int n) {
        int maxGap = 0;
        int lastOnesPos = -1;

        for(int i = 31; i >= 0; i--) {
            int mask = 1 << i;
            
            if((mask & n) != 0) {
                if(lastOnesPos != -1) {
                    maxGap = Math.max(maxGap, lastOnesPos - i);
                }

                lastOnesPos = i;
            }
        }

        return maxGap;
    }
}