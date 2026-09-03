class Solution {
    private int getMinAbsVal(int[] arr) {
        int n = arr.length;

        if(n == 1) {
            return 0;
        }
        
        Arrays.sort(arr);

        int minAbsVal = Integer.MAX_VALUE;

        for(int i = 1; i < n; i++) {
            if(arr[i - 1] != arr[i]) {
                minAbsVal = Math.min(minAbsVal, Math.abs(arr[i] - arr[i - 1]));
            }
        }

        return minAbsVal == Integer.MAX_VALUE ? 0 : minAbsVal;
    }

    public int[][] minAbsDiff(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] res = new int[(n - k + 1)][(m - k + 1)];

        int r = 0, c = 0;

        for(int row = 0; row <= (n - k); row++) {
            for(int col = 0; col <= (m - k); col++) {
                int[] list = new int[k * k];
                int idx = 0;

                for(int i = row; i < (row + k); i++) {
                    for(int j = col; j < (col + k); j++) {
                        list[idx++] = grid[i][j];
                    }
                }

                int minAbsVal = getMinAbsVal(list);

                res[r][c] = minAbsVal;
                c++;
            }
            r++; c = 0;
        }

        return res;
    }
}