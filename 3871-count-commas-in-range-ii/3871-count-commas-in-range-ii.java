class Solution {

    public long countCommas(long n) {
        long count5 = n - (long) (1e15 - 1);
        long count4 = n - (long) (1e12 - 1);
        long count3 = n - (long) (1e9 - 1);
        long count2 = n - (long) (1e6 - 1);
        long count1 = n - (long) (1e3 - 1);

        if(n >= 1e15) {
            return count5 + count4 + count3 + count2 + count1;
        } else if(n >= 1e12) {
            return count4 + count3 + count2 + count1;
        } else if(n >= 1e9) {
            return count3 + count2 + count1;
        } else if(n >= 1e6) {
            return count2 + count1;
        } else if(n >= 1e3) {
            return count1;
        }

        return 0;
    }
}