class Solution {
    
    public boolean canFinish(int n, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(n, prerequisites);
        
        int[] indegree = new int[n];
        
        for(int node = 0; node < n; node++) {
            for(int adjNode : adjList.get(node)) {
                indegree[adjNode]++;
            }
        }
        
        Queue<Integer> bfs = new LinkedList<>();
        
        for(int node = 0; node < n; node++) {
            if(indegree[node] == 0) {
                bfs.add(node);
            }
        }
        
        int totalNode = 0;
        
        while(!bfs.isEmpty()) {
            int currNode = bfs.poll();
            totalNode++;
            
            for(int adjNode : adjList.get(currNode)) {
                --indegree[adjNode];
                if(indegree[adjNode] == 0) {
                    bfs.add(adjNode);
                }
            }
        }
        
        return totalNode == n ? true : false;
    }
    
    private ArrayList<ArrayList<Integer>> constructAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            adjList.get(u).add(v);
        }
        
        return adjList;
    }
}