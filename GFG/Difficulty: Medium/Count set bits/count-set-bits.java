class Solution {
    public static int countSetBits(int n) {
        int count = 0;
        for (int bit = 0; bit < 32; bit++) {
            long period = 1L << (bit + 1);
            long full = n / period;
            count += full * (1L << bit);
            long rem = n % period;
            long threshold = 1L << bit;
            if (rem >= threshold) {
                count += rem - threshold + 1;
            }
        }
        return count; 
    }
}