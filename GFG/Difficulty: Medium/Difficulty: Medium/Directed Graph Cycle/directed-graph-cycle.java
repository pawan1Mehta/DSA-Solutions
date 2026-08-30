class Solution {
    
    private boolean isCyclePresent(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, boolean[] dfsStack) {
        if(!visited[node]) {
           visited[node] = true;
           dfsStack[node] = true;
           
           for(int adjNode : adjList.get(node)) {
               if(!visited[adjNode]) {
                   if(isCyclePresent(adjNode, adjList, visited, dfsStack)) {
                       return true;
                   }
               } else if(dfsStack[adjNode]) {
                   return true;
               }
           }
        }
        
        dfsStack[node] = false;
        
        return false;
    }
    
    public boolean isCyclic(int V, int[][] edges) {
       ArrayList<ArrayList<Integer>> adjList = constructAdjList(V, edges);
       
       boolean[] dfsStack = new boolean[V];
       boolean[] visited = new boolean[V];
       
       for(int node = 0; node < V; node++) {
           if(!visited[node]) {
               if(isCyclePresent(node, adjList, visited, dfsStack)) {
                   return true;
               }
           }
       }
       
       return false;
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
