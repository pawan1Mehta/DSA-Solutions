class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] gridColor = new int[n][m];
        int[][] gridTime = new int[n][m];

        for(int i = 0; i < n; i++) {
            Arrays.fill(gridColor[i], -1);
            Arrays.fill(gridTime[i], -1);
        }

        Queue<int[]> bfs = new LinkedList<>();

        int r, c, color, time;
        for(int[] source : sources) {
            r = source[0];
            c = source[1];
            color = source[2];

            gridColor[r][c] = color;
            gridTime[r][c] = 0;
            bfs.add(new int[]{r, c});
        }

        int[] dr = new int[]{0, 0, 1, -1};
        int[] dc = new int[]{1, -1, 0, 0};

        while(!bfs.isEmpty()) {
            int size = bfs.size();
            while(size-- > 0) {
                int[] curr = bfs.poll();
                r = curr[0];
                c = curr[1];
                color = gridColor[r][c];
                time = gridTime[r][c];

                for(int k = 0; k < dr.length; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if(isValidMove(nr, nc, n, m)) {
                        if(gridColor[nr][nc] == -1) {
                            gridColor[nr][nc] = color;
                            gridTime[nr][nc] = time + 1;
                            bfs.add(new int[]{nr, nc});
                        } else if(gridTime[nr][nc] == (time + 1)) {
                            gridColor[nr][nc] = Math.max(gridColor[nr][nc], color);
                        }
                    }
                }
            }
        }
        
        return gridColor;
    }

    private boolean isValidMove(int r, int c, int n, int m) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }
}