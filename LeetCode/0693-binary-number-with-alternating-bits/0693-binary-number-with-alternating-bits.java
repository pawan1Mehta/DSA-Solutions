class Solution {
    private int getMostSignificantBitPost(int num) {
        int msbPost = 0;

        for(int i = 0; i < 32; i++) {
            int mask = 1 << i;
            if((num & mask) != 0) {
                msbPost = i + 1;
            }
        }

        return msbPost;
    }

    public boolean hasAlternatingBits(int n) {
        int msbPost = getMostSignificantBitPost(n);

        for(int i = 0; i < msbPost; i++) {
            int mask1 = 1 << i;
            int mask2 = 1 << (i + 1);

            int val1 = (n & mask1) != 0 ? 1 : 0;
            int val2 = (n & mask2) != 0 ? 1 : 0;

            if(val1 == val2) {
                return false;
            }
        }

        return true;
    }
}