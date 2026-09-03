class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        for(int[] guard : guards) {
            grid[guard[0]][guard[1]] = 1;
        }

        for(int[] wall : walls) {
            grid[wall[0]][wall[1]] = -1;
        }

        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for(int[] guard : guards) {
            for(int[] dir: dirs) {
                int r = guard[0] + dir[0];
                int c = guard[1] + dir[1];

                while(r >= 0 && r < m && c >= 0 && c < n) {
                    if(grid[r][c] == -1 || grid[r][c] == 1) {
                        break;
                    }

                    grid[r][c] = 2;

                    r += dir[0];
                    c += dir[1];
                }
            }
        }

        int unoccupiedCells = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    unoccupiedCells++;
                }
            }
        }

        return unoccupiedCells;
    }
}