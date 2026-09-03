class Solution {
    public int smallestRepunitDivByK(int k) {
        if(k%2 == 0) {
            return -1;
        }

        if(k == 1) {
            return 1;
        }

        Set<Integer> seen = new HashSet<>();

        int len = 1;
        int remainder = 1;

        seen.add(remainder);
        
        while(true) {
            len++;

            int num = remainder * 10 + 1;
            int currRemainder = num % k;

            if(currRemainder == 0) {
                return len;
            }

            if(len > 2 && seen.contains(currRemainder)) {
                return -1;
            }

            seen.add(currRemainder);
            remainder = currRemainder;
        }
    }
}