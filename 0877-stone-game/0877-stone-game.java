class Solution {
    
    private int[][][] memo;

    private int solve(int i, int j, int turn, int[] piles) {
        if(i > j) {
            return 0;
        }

        if(memo[i][j][turn] != -1) {
            return memo[i][j][turn];
        }

        if(turn == 0) {
            int opt1 = piles[i] + solve(i + 1, j, 1, piles);
            int opt2 = piles[j] + solve(i, j - 1, 1, piles);
            return memo[i][j][turn] = Math.max(opt1, opt2);
        } else {
            int opt1 = solve(i + 1, j, 0, piles);
            int opt2 = solve(i, j - 1, 0, piles);
            return memo[i][j][turn] = Math.min(opt1, opt2);
        }
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;

        memo = new int[n][n][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        int sum = 0;
        for(int pile : piles) {
            sum += pile;
        }

        int aliceStones = solve(0, piles.length - 1, 0, piles);
        int bobStones = Math.abs(sum - aliceStones);

        return aliceStones > bobStones;
    }
}