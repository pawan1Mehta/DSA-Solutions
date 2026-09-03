class UnionFind
{
   public:
    
    vector<int> parent;
    
    UnionFind(int n) : parent(n)
    {
        iota(parent.begin(),parent.end(),0);
    }
    
    int find(int node)
    {
        if(node == parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
    
    bool isConnected(int node1,int node2)
    {
        return (find(node1) == find(node2));
    }
    
    void reset(int node)
    {
        parent[node]=node;
    }
    
    void unionNode(int node1,int node2)
    {
        parent[find(node2)]=find(node1);
    }
};


class Solution {
public:
    
    vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) {
        
        sort(meetings.begin(),meetings.end(),[](auto& a,auto& b){ return a[2]< b[2]; });
        UnionFind unionFindObj(n+1);
        unionFindObj.unionNode(0,firstPerson);
        
        int index=0;
        vector<int> pool;
        
        while(index<meetings.size())
        {
            int time=meetings[index][2];
            pool.clear();            
            while(index<meetings.size() && time==meetings[index][2])
            {
                unionFindObj.unionNode(meetings[index][0],meetings[index][1]);
                pool.push_back(meetings[index][0]);
                pool.push_back(meetings[index][1]);
                index++;
            }
            
            for(int node : pool)
            {
                if(!unionFindObj.isConnected(node,0))
                    unionFindObj.reset(node);
            }
            
        }
        
        vector<int> ans;
        
        for(int i=0; i<n; i++)
        {
            if(unionFindObj.isConnected(i,0))
                ans.push_back(i);
        }
        
        return ans;
    }
};