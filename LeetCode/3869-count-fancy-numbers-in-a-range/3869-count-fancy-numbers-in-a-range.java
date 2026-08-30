class Solution {

    private boolean[] goodSum;
    private long[][][][][][] dp;
    
    private long digitDP(int pos, int sum, int prevDigit, int tight, int incr, int decr, int[] digits) {
        if(pos == digits.length) {
            if(prevDigit == 10) {
                return 0;
            }
            return (incr == 1 || decr == 1 || goodSum[sum]) ? 1 : 0;
        }

        if(dp[pos][sum][prevDigit][tight][incr][decr] != -1) {
            return dp[pos][sum][prevDigit][tight][incr][decr];
        }

        int limit;
        
        if(tight == 1) {
            limit = digits[pos];
        } else {
            limit = 9;
        }

        long count = 0;
        
        for(int d = 0; d <= limit; d++) {
            int newTight = 0;
                
            if(tight == 1 && d == limit) {
                newTight = 1;
            }
            
            if(prevDigit == 10) {
                if(d == 0) {
                    count += digitDP(pos + 1, 0, 10, newTight, 1, 1, digits);
                } else {
                    count += digitDP(pos + 1, d, d, newTight, 1, 1, digits);
                }
            } else {
                int newIncr = (incr == 1 && d > prevDigit) ? 1 : 0;
                int newDecr = (decr == 1 && d < prevDigit) ? 1 : 0;
                
                count += digitDP(pos + 1, sum + d, d, newTight, newIncr, newDecr, digits);
            }
        }

        return dp[pos][sum][prevDigit][tight][incr][decr] = count;
    }
    
    public long countFancy(long l, long r) {
        
        initializeGoodSum();

        dp = new long[16][136][11][2][2][2];

        int[] lDigits = getDigits(l - 1);
        int[] rDigits = getDigits(r);
        
        reset(dp);
        long count1 = digitDP(0, 0, 10, 1, 1, 1, lDigits);

        reset(dp);
        long count2 = digitDP(0, 0, 10, 1, 1, 1, rDigits);
 
        return count2 - count1;
    }

    private boolean isGood(int num) {
        boolean incr = true, decr = true;
        int prevNum = num % 10;
        
        num = num/10;

        while(num > 0) {
            int d = num % 10;

            if(d <= prevNum) {
                incr = false;
            }
            if(d >= prevNum) {
                decr = false;
            }

            prevNum = d;
            num = num/10;
        }

        return incr || decr;
    }

    private void initializeGoodSum() {
        goodSum = new boolean[136];

        for(int sum = 0; sum <= 135; sum++) {
            goodSum[sum] = isGood(sum);
        }
    }
    
    private void reset(long[][][][][][] dp) {
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 136; j++)
                for(int k = 0; k < 11; k++)
                   for(int l = 0; l < 2; l++)
                      for(int m = 0; m < 2; m++)
                         for(int n = 0; n < 2; n++)
                            dp[i][j][k][l][m][n] = -1;
    }

    private int[] getDigits(long n) {
        ArrayList<Integer> list = new ArrayList<>();

        while(n > 0) {
            list.add((int)(n % 10));
            n = n/10;
        }

        int[] digits = new int[list.size()];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = list.get(list.size() - i - 1);
        }

        return digits;
    }
}