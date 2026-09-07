class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();

        int[] count = new int[26]; 
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int MOD = 1_000_000_007;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            dp[i] += sum - count[idx];
            dp[i] = (dp[i] + MOD) % MOD;
            sum = (sum + dp[i]) % MOD;
            count[idx] = (count[idx] + dp[i]) % MOD;
        }

        return (int) sum;
    }
}