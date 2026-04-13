class Solution {
    
    public static long binaryExponentiation(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if((b & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b = b >> 1;
        }
        return res;
    }
    
    public int maxValue(int[] nums1, int[] nums0) {
        int MOD = 1_000_000_007;

        int n = nums1.length;
        
        ArrayList<int[]> list = new ArrayList<>();

        int lastOne = 0;

        for(int i = 0; i < n; i++) {
            if(nums0[i] == 0) {
                lastOne += nums1[i];
            } else {
                list.add(new int[]{nums1[i], nums0[i]});
            }
        }

        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(b[0], a[0]);
            }
        });

        long num = 0;
        int bitPos = 0;
        
        for(int i = list.size() - 1; i >= 0; i--) {
            bitPos += list.get(i)[1];
            for(int k = 1; k <= list.get(i)[0]; k++) {
                long twoPower = binaryExponentiation(2, bitPos, MOD);
                num = (num + twoPower) % MOD;
                bitPos++;
            }
        }

        for(int k = 1; k <= lastOne; k++) {
            long twoPower = binaryExponentiation(2, bitPos, MOD);
            num = (num + twoPower) % MOD;
            bitPos++;
        }

        return (int)num;
    }
}