class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int flips = 0;

        while(left < right) {
            left = left >> 1;
            right = right >> 1;
            flips++;
        }
        
        return (left << flips);
    }
}