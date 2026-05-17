class Solution {
    public int countLocalMaximums(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        ArrayList<int[]>[] pos = new ArrayList[201];

        for(int num = 0; num <= 200; num++) {
            pos[num] = new ArrayList<>();
        }

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                pos[matrix[row][col]].add(new int[]{row, col});
            }
        }

        int count = 0;

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                int x = matrix[r][c];

                if(x == 0) continue;

                boolean isValid = true;

                for(int val = x + 1; val <= 200 && isValid; val++) {
                    for(int[] p : pos[val]) {
                        int nr = p[0];
                        int nc = p[1];

                        int dr = Math.abs(r - nr);
                        int dc = Math.abs(c - nc);

                        if(dr <= x && dc <= x) {
                            if(dr == x && dc == x) 
                                continue;
                            isValid = false;
                            break;
                        }
                    }
                }

                if(isValid) {
                    count++;
                }
            }
        }

        return count;
    }
}