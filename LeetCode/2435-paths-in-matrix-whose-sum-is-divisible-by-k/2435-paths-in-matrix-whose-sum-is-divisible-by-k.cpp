int mod=1000000007;

class Solution {
private:
    vector<vector<vector<int>>> memo;
    
    int countPaths(vector<vector<int>>& grid,int i,int j,int s,int k,int n,int m)
    {
        if(i==n || j==m) 
            return 0;
        
        if(i==n-1 && j==m-1)
            return (s+grid[i][j])%k == 0;
        
        if(memo[i][j][s] != -1)
            return memo[i][j][s];
        
        return memo[i][j][s]=(countPaths(grid,i,j+1,(s+grid[i][j])%k,k,n,m) +
                           countPaths(grid,i+1,j,(s+grid[i][j])%k,k,n,m))%mod;
    }
    
public:
    int numberOfPaths(vector<vector<int>>& grid, int k) {
        int n=grid.size();
        int m=grid[0].size();
        memo.resize(n+1,vector<vector<int>>(m+1,vector<int>(k+1,-1)));
        return countPaths(grid,0,0,0,k,n,m);
    }
};