class Solution {
    int subsetXORSum(int arr[]) {
        int n = arr.length;
        int bits = 0;

        for (int i = 0; i < n; ++i)
            bits |= arr[i];

        int ans = (int)(bits * Math.pow(2, n - 1));

        return ans;
    }
}