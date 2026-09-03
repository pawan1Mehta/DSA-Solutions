class Solution {
    public char findKthBit(int n, int k) {
        if(n == 1) {
            return '0';
        }

        int snLen = (1 << n) - 1;

        int mid = (snLen / 2) + 1;

        if(mid == k) {
            return '1';
        }

        if(k < mid) {
            return findKthBit(n - 1, k);
        }

        return findKthBit(n - 1, (snLen - k) + 1) == '0' ? '1' : '0';
    }
}