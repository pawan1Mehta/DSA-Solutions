class Solution {

    private int minOperationToMakeAllElementNum(int[] arr, int num, int x) {
        int n = arr.length;

        int operations = 0;
        
        for(int a : arr) {
            if(a == num) {
                continue;
            }

            int diff = Math.abs(a - num);
            
            if(diff%x != 0) {
                return -1;
            }

            operations += (diff/x);
        }

        return operations;
    }

    private int minOperationToMakeAllEqual(int[] arr, int x) {
        int n = arr.length;

        if(n%2 != 0) {
            return minOperationToMakeAllElementNum(arr, arr[n/2], x);
        }
        
        int res = minOperationToMakeAllElementNum(arr, arr[n/2], x);
        if((n/2 - 1) >= 0) {
            res = Math.min(res,  minOperationToMakeAllElementNum(arr, arr[(n/2) - 1], x));
        }
        if((n/2 + 1) < n) {
            res = Math.min(res, minOperationToMakeAllElementNum(arr, arr[(n/2) + 1], x));
        }

        return res;
    }

    public int minOperations(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;

        int[] arr = new int[n  * m];
        int idx = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[idx++] = grid[i][j];
            }
        }

        Arrays.sort(arr);

        return minOperationToMakeAllEqual(arr, x);
    }
}
