class Solution {
    public int countNegatives(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        for(int[] row : grid) {
            int currIndex = m - 1;
            while(currIndex >= 0 && row[currIndex] < 0) {
                currIndex--;
            }

            count += (m - currIndex - 1);
        }

        return count;
    }
}