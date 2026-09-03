class Solution {

    int[] dr = new int[]{0, 0, -1, 1};
    int[] dc = new int[]{1,-1,  0, 0};

    private boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();

        Queue<int[]> bfs = new LinkedList<>();
        int[][] manhattanDist = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(manhattanDist[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid.get(i).get(j) == 1) {
                    bfs.add(new int[]{i, j, i, j});
                    manhattanDist[i][j] = 0;
                }
            }
        }

        while(!bfs.isEmpty()) {
            int size = bfs.size();
            while(size-- > 0) {
                int[] curr = bfs.poll();
                int crow = curr[0];
                int ccol = curr[1];

                int row = curr[2];
                int col = curr[3];

                for(int k = 0; k < dr.length; k++) {
                    int nrow = crow + dr[k];
                    int ncol = ccol + dc[k];

                    int currManhattanDist = Math.abs(row - nrow) + Math.abs(col - ncol);

                    if(isValid(nrow, ncol, n, m) && currManhattanDist < manhattanDist[nrow][ncol]) {
                        manhattanDist[nrow][ncol] = currManhattanDist;
                        bfs.add(new int[]{nrow, ncol, row, col});
                    }
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a, b) -> b[2] - a[2]);

        maxHeap.add(new int[]{0, 0, manhattanDist[0][0]});
        visited[0][0] = true;
        
        while(!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int row = curr[0];
            int col = curr[1];
            int mntDist = curr[2];

            if(row == n - 1 && col == m - 1) {
                return mntDist;
            }
            
            for(int k = 0; k < dr.length; k++) {
                int nrow = row + dr[k];
                int ncol = col + dc[k];

                if(isValid(nrow, ncol, n, m) && visited[nrow][ncol] == false) {
                    maxHeap.add(new int[]{nrow, ncol, Math.min(mntDist, manhattanDist[nrow][ncol])});
                    visited[nrow][ncol] = true;
                }
            }
        }

        return 0;
    }
}