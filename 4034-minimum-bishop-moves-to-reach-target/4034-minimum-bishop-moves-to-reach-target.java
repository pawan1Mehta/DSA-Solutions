class Solution {
    public int minBishopMoves(int[] source, int[] target) {
        Queue<int[]> bfs = new LinkedList<>();

        char[][] chessboard = new char[9][9];
        chessboard[source[0]][source[1]] = '#';

        bfs.add(new int[]{source[0], source[1], 0});
        
        while(!bfs.isEmpty()) {
            int n = bfs.size();
            while(n-- > 0) {
                int[] curr = bfs.poll();
                int row = curr[0];
                int col = curr[1];
                int steps = curr[2];

                if(row == target[0] && col == target[1]) {
                    return steps;
                }

                // up left
                for(int nextRow = row - 1, nextCol = col - 1; nextRow >= 1 && nextCol >= 1; nextRow--, nextCol--) {
                    if(chessboard[nextRow][nextCol] == '#') continue;
                    bfs.add(new int[]{nextRow, nextCol, steps + 1});
                    chessboard[nextRow][nextCol] = '#';
                }
                // up right
                for(int nextRow = row - 1, nextCol = col + 1; nextRow >= 1 && nextCol <= 8; nextRow--, nextCol++) {
                    if(chessboard[nextRow][nextCol] == '#') continue;
                    bfs.add(new int[]{nextRow, nextCol, steps + 1});
                    chessboard[nextRow][nextCol] = '#';
                }
                // dow left
                for(int nextRow = row + 1, nextCol = col - 1; nextRow <= 8 && nextCol >= 1; nextRow++, nextCol--) {
                    if(chessboard[nextRow][nextCol] == '#') continue;
                    bfs.add(new int[]{nextRow, nextCol, steps + 1});
                    chessboard[nextRow][nextCol] = '#';
                }
                // dow right
                for(int nextRow = row + 1, nextCol = col + 1; nextRow <= 8 && nextCol <= 8; nextRow++, nextCol++) {
                    if(chessboard[nextRow][nextCol] == '#') continue;
                    bfs.add(new int[]{nextRow, nextCol, steps + 1});
                    chessboard[nextRow][nextCol] = '#';
                }
            }
        }

        return -1;
    }
}