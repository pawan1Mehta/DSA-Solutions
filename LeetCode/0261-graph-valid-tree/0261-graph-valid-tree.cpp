class Solution {
public:
    
    bool isCycle(vector<int> adjList[],int currNode,int parent,vector<bool>& visited)
    {
        visited[currNode]=true;
        
        for(auto& adjEdge : adjList[currNode])
        {
            if(visited[adjEdge] == false)
            {
                if(isCycle(adjList,adjEdge,currNode,visited))
                    return true;
            }
            else if(parent != adjEdge)
            {
                return true;
            }
        }
        
        return false;
    }
    
    void dfs(vector<int> adjList[],int currNode,vector<bool>& visited)
    {
        if(visited[currNode])
            return;
        
        visited[currNode]=true;
        
        for(auto& adjEdge : adjList[currNode])
        {
            if(!visited[adjEdge])
            {
                dfs(adjList,adjEdge,visited);
            }
        }
    }
    
    int countComponent(vector<int> adjList[],int n)
    {
        vector<bool> visited(n,false);
        
        int countComp=0;
        
        for(int i=0; i<n; i++)
        {
            if(!visited[i])
            {
                dfs(adjList,i,visited);
                countComp++;
            }
        }
        return countComp;
    }
    
    bool validTree(int n, vector<vector<int>>& edges) {
        
        if(n == 0 || n == 1)
            return true;
        
        vector<int> adjList[n];
        
        for(auto& edge : edges)
        {
            adjList[edge[0]].push_back(edge[1]);
            adjList[edge[1]].push_back(edge[0]);
        }
        vector<bool> visited(n,false);
        
        if(isCycle(adjList,0,-1,visited))
            return false;
       
        return countComponent(adjList,n) == 1;
    }
};