class Solution {
    public long maxRatings(int[][] units) {
        int n = units.length;
        int m = units[0].length;

        long sum = 0;
        if(m == 1) {
            for(int i = 0; i < n; i++) {
                sum += units[i][0];
            }    
            return sum;
        }
        
        for(int i = 0; i < n; i++) {
            Arrays.sort(units[i]);
        }

        int minEleDevice = Integer.MAX_VALUE, idx = 0;
        for(int i = 0; i < n; i++) {
            if(units[i][1] < minEleDevice) {
                minEleDevice = units[i][1];
                idx = i;
            }
        }

        int minNum = units[idx][0];
        
        for(int i = 0; i < n; i++) {
            if(i == idx) continue;
            sum += units[i][1];
            minNum = Math.min(minNum, units[i][0]);
        }
        sum += minNum;

        return sum;
    }
}