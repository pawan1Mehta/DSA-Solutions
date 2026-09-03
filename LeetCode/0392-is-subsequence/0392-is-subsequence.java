class Solution {
    Boolean[][] memo;

    private boolean solve(int i, int j, String s, String t) {
        if(i == s.length()) {
            return true;
        }
        if(j == t.length()) {
            return false;
        }

        if(memo[i][j] != null) {
            return memo[i][j];
        }

        boolean opt1 = false, opt2 = false;

        if(s.charAt(i) == t.charAt(j)) {
            opt1 = solve(i + 1, j + 1, s, t);
        }
        opt2 = solve(i, j + 1, s, t);

        return memo[i][j] = opt1 || opt2;
    }

    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();

        memo = new Boolean[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], null);
        }

        return solve(0, 0, s, t);
    }
}