class Solution {
    
    public final static int MOD = 1_000_000_007;
    
    public ArrayList<Integer> findWays(int[][] grid) {
        int n = grid.length;
        
        int[] nextWays = new int[n];
        int[] nextAdventure = new int[n];
        
        for(int i = n - 1; i >= 0; i--) {
            int[] currWays = new int[n];
            int[] currAdventure = new int[n];
            Arrays.fill(currAdventure, -1);
        
            for(int j = n - 1; j >= 0; j--) {
                if(i == n - 1 && j == n - 1) {
                    currWays[j] = 1;
                    currAdventure[j] = grid[i][j];
                    continue;
                }
                
                long totalWays = 0;
                int maxAdventure = -1;
                int cellValue = grid[i][j];
                
                if(cellValue == 1 || cellValue == 3) {
                    if(j+1 < n && currAdventure[j + 1] != -1) {
                        maxAdventure = Math.max(maxAdventure, grid[i][j] + currAdventure[j + 1]);
                        totalWays = (totalWays + currWays[j + 1])%MOD;
                    }
                }
                
                if(cellValue == 2 || cellValue == 3) {
                    if(i+1 < n && nextAdventure[j] != -1) {
                        maxAdventure = Math.max(maxAdventure, grid[i][j] + nextAdventure[j]);
                        totalWays = (totalWays + nextWays[j])%MOD;
                    }
                }
                
                currWays[j] = (int) totalWays;
                currAdventure[j] = maxAdventure;
            }
            
            nextWays = currWays;
            nextAdventure = currAdventure;
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        res.add(nextWays[0]);
        res.add(nextAdventure[0] == -1 ? 0 : nextAdventure[0]);
        
        return res;
    }
}