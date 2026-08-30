class Solution {
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1};
    
    private boolean minCostPathUtil(int i, int j, int prevVal, int k, int[][] mat, boolean[][] visited, int n, int m) {
        if(i >= 0 && j >= 0 && i < n && j < m && (Math.abs(prevVal - mat[i][j]) <= k) && visited[i][j] == false) {
            if(i == n-1 && j == m-1) {
                return true;
            }
            
            visited[i][j] = true;
        
            int nr, nc;
            for(int num = 0; num < 4; num++) {
                nr = i + dr[num];
                nc = j + dc[num];
                
                if(minCostPathUtil(nr, nc, mat[i][j], k, mat, visited, n, m)) {
                    return true;
                }
            }
            
            return false;
        } else {
            return false;
        }
    }
    
    public int minCostPath(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        int start = 0;
        int end = 100000000;
        
        while(start < end) {
            int mid = (start + end)/2;
            
            boolean[][] visited = new boolean[n][m];
            
            if(minCostPathUtil(0, 0, mat[0][0], mid, mat, visited, n, m)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return start;
    }
}
