class Solution {
    private int kernighansAlgo(int num) {
        int count = 0;
        while(num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        
        for(int i = 0; i <= n; i++) {
            res[i] = kernighansAlgo(i);    
        }

        return res;
    }
}