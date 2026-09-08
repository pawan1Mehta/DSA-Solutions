class Solution {

    private int commas(int n) {
        if(n <= 999) {
            return 0;
        }

        int count = 0;
        int digitsPassed = 0;

        while(n > 0) {
            n = n/10;
            if(digitsPassed == 3) {
                count++;
                digitsPassed = 1;
            } else {
                digitsPassed++;
            }
        }

        return count;
    }
    
    public int countCommas(int n) {
        int count = 0;
        
        for(int num = 1000; num <= n; num++) {
            count += commas(num);
        }

        return count;
    }
}