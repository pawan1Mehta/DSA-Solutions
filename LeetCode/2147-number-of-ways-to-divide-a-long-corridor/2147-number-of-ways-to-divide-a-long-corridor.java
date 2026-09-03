class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;

        long count = 1;

        int seats = 0;

        Integer previousPairLast = null;

        for (int index = 0; index < corridor.length(); index++) {
            if (corridor.charAt(index) == 'S') {
                seats += 1;

                if (seats == 2) {
                    previousPairLast = index;
                    seats = 0;
                }

                else if (seats == 1 && previousPairLast != null) {
                    count *= (index - previousPairLast);
                    count %= MOD;
                }
            }
        }

        if (seats == 1 || previousPairLast == null) {
            return 0;
        }

        return (int) count;
    }
}