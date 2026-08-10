class Solution {
    public boolean winnerSquareGame(int n) {
        Map<Integer, Boolean> memo = new HashMap<>();
        memo.put(0, false);
        return solve(n, memo);
    }

    private boolean solve(int remain, Map<Integer, Boolean> memo) {
        if(memo.containsKey(remain)) {
            return memo.get(remain);
        }

        int sqrNum = (int) Math.sqrt(remain);
        for(int num = 1; num <= sqrNum; num++) {
            if(!solve(remain - (num * num), memo)) {
                memo.put(remain, true);
                return true;
            }
        }

        memo.put(remain, false);
        return false;
    }
}