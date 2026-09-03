class Solution {

    int[][] memo;

    private int solve(int start, int end, int[] stoneValue) {
        if(start >= end) {
            return 0;
        }

        if(memo[start][end] != -1) {
            return memo[start][end];
        }

        int maxScore = 0;
        for(int k = start; k < end; k++) {
            int leftScore = stoneValue[k] - (start - 1 < 0 ? 0 : stoneValue[start - 1]);
            int rightScore = stoneValue[end] - stoneValue[k];

            if(rightScore > leftScore) {
                int score = solve(start, k, stoneValue);
                maxScore = Math.max(maxScore, leftScore + score);
            } else if(rightScore < leftScore) {
                int score = solve(k + 1, end, stoneValue);
                maxScore = Math.max(maxScore, rightScore + score);
            } else {
                int score = solve(start, k, stoneValue);
                maxScore = Math.max(maxScore, leftScore + score);

                score = solve(k + 1, end, stoneValue);
                maxScore = Math.max(maxScore, rightScore + score);
            }
        }

        return memo[start][end] = maxScore;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        for(int i = 1; i < n; i++) {
            stoneValue[i] += stoneValue[i - 1];
        }

        memo = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return solve(0, n - 1, stoneValue);
    }
}