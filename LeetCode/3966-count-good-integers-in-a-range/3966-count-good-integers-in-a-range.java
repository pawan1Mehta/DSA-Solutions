class Solution {
    
    private long[][][][] dp;

    private long solve(int i, int prev, int tight, int start, int[] digits, int k) {
        if(i == digits.length) {
            return start;
        }

        if(dp[i][prev + 1][tight][start] != -1) {
            return dp[i][prev + 1][tight][start];
        }

        int limit = tight == 1 ? digits[i] : 9;

        long res = 0;

        for(int num = 0; num <= limit; num++) {
            int newTight = (tight == 1 && num == limit) ? 1 : 0;

            if(prev == -1 && num == 0) {
                res += solve(i + 1, -1, newTight, 0, digits, k);
            } else if(prev == -1 || Math.abs(prev - num) <= k) {
                res += solve(i + 1, num, newTight, 1, digits, k);
            }
        }

        return dp[i][prev + 1][tight][start] = res;
    }

    private long goodIntegersUtil(long num, int k) {
        int[] digits = digits(num);

        int n = digits.length;

        dp = new long[20][12][2][2];
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 12; j++) {
                for(int l = 0; l < 2; l++) {
                    Arrays.fill(dp[i][j][l], -1);
                }
            }
        }

        return solve(0, -1, 1, 0, digits, k);
    }

    private int[] digits(long num) {
        ArrayList<Integer> digits = new ArrayList<>();
        
        while(num > 0) {
            int d = (int) (num % 10);
            digits.add(d);
            num = num/10;
        }
        
        Collections.reverse(digits);
        
        return digits.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
    }

    public long goodIntegers(long l, long r, int k) {
        return goodIntegersUtil(r, k) - goodIntegersUtil(l - 1, k);
    }
}