class Solution {
    int distinctSubseq(String str) {
        int n = str.length();
        int mod = 1000000007;

        int[] last = new int[26];

        int res = 1;

        for (int i = 1; i <= n; i++) {
            int cur = (int)(((2L * res) % mod - 
                    last[str.charAt(i - 1) - 'a'] + mod) % mod);

            last[str.charAt(i - 1) - 'a'] = res;
            res = cur;
        }
        
        return res;
    }
}