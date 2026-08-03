class Solution {
    int[][][] memo;

    private int[] solve(int i, int turn, int[] stoneValue) {
        if(i >= stoneValue.length) {
            return new int[]{0, 0};
        }
        
        if(memo[i][turn] != null) {
            return memo[i][turn];
        }

        if(turn == 0) {
            int[] maxScore = null;
            int currSum = 0;

            for(int j = i; j < Math.min(i + 3, stoneValue.length); j++) {
                currSum += stoneValue[j];

                int[] currScore = solve(j + 1, 1, stoneValue);
                int[] can = new int[]{currScore[0]+ currSum, currScore[1]};

                if(maxScore == null || maxScore[0] < can[0]) {
                    maxScore = can;
                }
            }

            return memo[i][turn] = maxScore;
        } else {
            int[] minScore = null;
            int currSum = 0;

            for(int j = i; j < Math.min(i + 3, stoneValue.length); j++) {
                currSum += stoneValue[j];

                int[] currScore = solve(j + 1, 0, stoneValue);
                int[] can = new int[]{currScore[0], currScore[1] + currSum};

                if(minScore == null || minScore[0] > can[0]) {
                    minScore = can;
                }
            }

            return memo[i][turn] = minScore;
        }
    }

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        memo = new int[n][2][];

        int[] score = solve(0, 0, stoneValue);

        int aliceScore = score[0];
        int bobScore = score[1];

        if(aliceScore > bobScore) {
            return "Alice";
        } else if(aliceScore < bobScore) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}