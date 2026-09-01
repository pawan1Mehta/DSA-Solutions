class Solution {
    public int minMoves(String[] classroom, int energy) {
        int n = classroom.length;
        int m = classroom[0].length();

        int sr = 0, sc = 0;
        int[][] id = new int[n][m];
        int count = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                char ch = classroom[i].charAt(j);
                if(ch == 'L') {
                    id[i][j] = 1 << count;
                    count++;
                }
                if(ch == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }

        int totalLs = 1 << count;

        int[][][] bestEnergy = new int[n][m][totalLs];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(bestEnergy[i][j], Integer.MIN_VALUE);
            }
        }

        int minMoves = Integer.MAX_VALUE;

        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{sr, sc, 0, energy, 0});
        bestEnergy[sr][sc][0] = energy;

        int[] dr = new int[]{0, 0, 1, -1};
        int[] dc = new int[]{1, -1, 0, 0};

        while(!bfs.isEmpty()) {
            int size = bfs.size();
            while(size-- > 0) {
                int[] curr = bfs.poll();
                
                int row = curr[0], col = curr[1], mask = curr[2], currEnergy = curr[3], steps = curr[4];

                if(mask == totalLs - 1) {
                    minMoves = Math.min(minMoves, steps);
                    continue;
                }

                if(currEnergy == 0) {
                    continue;
                }

                for(int k = 0; k < 4; k++) {
                    int nextRow = row + dr[k];
                    int nextCol = col + dc[k];
                    int nextMask = mask;
                    int nextEnergy = currEnergy - 1;
                    int nextSteps = steps + 1;

                    if(!isValid(nextRow, nextCol, n, m) || classroom[nextRow].charAt(nextCol) == 'X') {
                        continue;
                    }
                
                    nextMask = nextMask | id[nextRow][nextCol];

                    if(classroom[nextRow].charAt(nextCol) == 'R') {
                        nextEnergy = energy;
                    }

                    if(bestEnergy[nextRow][nextCol][nextMask] >= nextEnergy) {
                        continue;
                    }

                    bfs.add(new int[]{nextRow, nextCol, nextMask, nextEnergy, nextSteps});
                    bestEnergy[nextRow][nextCol][nextMask] = nextEnergy;
                }
            }
        }

        return minMoves == Integer.MAX_VALUE? -1: minMoves;
    }

    private boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && col >= 0 && row < n && col < m;
    }
}