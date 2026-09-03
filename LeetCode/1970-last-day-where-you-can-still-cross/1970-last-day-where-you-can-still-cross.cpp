int dx[4]={0,0,1,-1};
int dy[4]={1,-1,0,0};


class Solution {
public:
    int latestDayToCross(int row, int col, vector<vector<int>>& cells) {
        
        int low=0,heigh=cells.size();
        int ans;
        
        while(low <= heigh)
        {
            int mid=low+(heigh-low)/2;
            
            if(isPossible(cells,row,col,mid))
            {
                ans=mid;
                low=mid+1;
            }
            else
            {
                heigh=mid-1;
            }
        }
        return ans;
    }
    
    
    bool isPossible(vector<vector<int>>& cells,int row,int col,int day)
    {
        
        vector<vector<int>> grid(row,vector<int> (col,0));
        
        for(int i=0; i<day; i++)
        {
            grid[cells[i][0]-1][cells[i][1]-1]=1;
        }
        
        queue<pair<int,int>> q;
        
        for(int i=0; i<col; i++)
        {
            if(grid[0][i]==0)
            {
                q.push({0,i});
                grid[0][i]=1;
            }
        }  
        
       while(!q.empty())
       {
        
           auto p=q.front();
           q.pop();
           
           if(p.first == row-1)
               return true;
           
           for(int k=0; k<4; k++)
           {
               int ii=p.first+dx[k];
               int jj=p.second+dy[k];
               if(ii>=0&&jj>=0&&ii<row&&jj<col&&grid[ii][jj]==0)
               {
                   q.push({ii,jj});
                   grid[ii][jj]=1;
               }
           }
       }        
       return false;
    }
    
};