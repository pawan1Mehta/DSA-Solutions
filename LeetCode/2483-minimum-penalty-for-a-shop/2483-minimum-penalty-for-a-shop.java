class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();

        int[] leftN = new int[n];
        int[] rightY = new int[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                leftN[i] = customers.charAt(i) == 'N' ? 1 : 0;
            } else {
                leftN[i] = leftN[i - 1] + (customers.charAt(i) == 'N' ? 1 : 0);
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                rightY[i] = customers.charAt(i) == 'Y' ? 1 : 0;
            } else {
                rightY[i] = rightY[i + 1] + (customers.charAt(i) == 'Y' ? 1 : 0);
            }
        }

        int hour = 0;
        int penalty = rightY[0];

        for(int i = 1; i < n; i++) {
            int currPenalty = leftN[i - 1] + rightY[i];

            if(currPenalty < penalty) {
                hour = i;
                penalty = currPenalty;
            }
        }

        if(penalty > leftN[n - 1]) {
            hour = n;
        }

        return hour;
    }
}