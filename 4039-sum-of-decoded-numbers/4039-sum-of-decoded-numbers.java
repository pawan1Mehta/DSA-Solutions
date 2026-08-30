class Solution {
    private static final int MOD = 1_000_000_007;

    private long decodedNumber(long num) {
        int width = (int) (num%10);
        long d = num/10;

        StringBuilder xStr = new StringBuilder();
        StringBuilder yStr = new StringBuilder();

        StringBuilder numStr = new StringBuilder().append(d);
        
        for(int i = 0; i < width; i++) {
            xStr.append(numStr.charAt(i));
        }
        for(int i = width; i < numStr.length(); i++) {
            yStr.append(numStr.charAt(i));
        }

        long x = Long.parseLong(xStr.toString());
        long y = Long.parseLong(yStr.toString());
        
       return binaryExponentation(x, y);
    }

    private long binaryExponentation(long a, long b) {
        long res = 1;
        while(b > 0) {
            if((b & 1) != 0) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b = b >> 1;
        }
        return res;
    }

    public int sumDecoded(long[] nums) {
        long res = 0;

        for(long num : nums) {
            res = (res + decodedNumber(num)) % MOD;
        }
        
        return (int) res;
    }
}