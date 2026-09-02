class Solution {
    public int smallestNumber(int n) {
        int msbPos = 0;

        for(int i = 31; i >= 0; i--) {
            if((n & (1 << i)) != 0) {
                msbPos = 1 << i;
                break;
            }
        }

        for(int i = 0; i < 32; i++) {
            if((msbPos & (1 << i)) != 0) {
                break;
            }
            msbPos = msbPos | (1 << i);
        }

        return msbPos;
    }
}